/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999-2003 The Apache Software Foundation.  All rights 
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer. 
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowlegement:  
 *       "This product includes software developed by the 
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowlegement may appear in the software itself,
 *    if and wherever such third-party acknowlegements normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written 
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */ 

package org.apache.taglibs.standard.tag.common.fmt;

import java.util.*;
import java.text.*;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.jstl.core.Config;
import javax.servlet.jsp.jstl.fmt.LocalizationContext;
import org.apache.taglibs.standard.tag.common.core.Util;
import org.apache.taglibs.standard.resources.Resources;

/**
 * Support for tag handlers for &lt;message&gt;, the message formatting tag
 * in JSTL 1.0.
 *
 * @author Jan Luehe
 */

public abstract class MessageSupport extends BodyTagSupport {

    //*********************************************************************
    // Public constants

    public static final String UNDEFINED_KEY = "???";


    //*********************************************************************
    // Protected state

    protected String key;                         // 'key' attribute
    protected boolean keySpecified;	          // status
    protected LocalizationContext locCtxt;        // 'bundle' attribute
    protected boolean bundleSpecified;   // 'bundle' attribute specified?


    //*********************************************************************
    // Private state

    private String var;                           // 'var' attribute
    private int scope;                            // 'scope' attribute
    private List params;


    //*********************************************************************
    // Constructor and initialization

    public MessageSupport() {
	super();
	params = new ArrayList();
	init();
    }

    private void init() {
	key = var = null;
	keySpecified = false;
	locCtxt = null;
	scope = PageContext.PAGE_SCOPE;
    }


    //*********************************************************************
    // Tag attributes known at translation time

    public void setVar(String var) {
        this.var = var;
    }

    public void setScope(String scope) {
	this.scope = Util.getScope(scope);
    }


    //*********************************************************************
    // Collaboration with subtags

    /**
     * Adds an argument (for parametric replacement) to this tag's message.
     *
     * @see ParamSupport
     */
    public void addParam(Object arg) {
	params.add(arg);
    }


    //*********************************************************************
    // Tag logic

    public int doStartTag() throws JspException {
        if (!bundleSpecified) {
            // must reset the locCtxt to null so it can be reevaluated
            // (necessary because the tag may be reused and localization
            // context used for the tag may have changed).
            locCtxt = null;
        }
	params.clear();
	return EVAL_BODY_BUFFERED;
    }

    public int doEndTag() throws JspException {

        String keyInput = null;

        // determine the message key by...
        if (keySpecified) {
	    // ... reading 'key' attribute
	    keyInput = key;
	} else {
	    // ... retrieving and trimming our body
	    if (bodyContent != null && bodyContent.getString() != null)
	        keyInput = bodyContent.getString().trim();
	}

	if ((keyInput == null) || keyInput.equals("")) {
	    try {
		pageContext.getOut().print("??????");
	    } catch (IOException ioe) {
		throw new JspTagException(ioe.toString(), ioe);
	    }
	    return EVAL_PAGE;
	}

	String prefix = null;
	if (locCtxt == null) {
	    Tag t = findAncestorWithClass(this, BundleSupport.class);
	    if (t != null) {
		// use resource bundle from parent <bundle> tag
		BundleSupport parent = (BundleSupport) t;
		locCtxt = parent.getLocalizationContext();
		prefix = parent.getPrefix();
	    } else {
		locCtxt = BundleSupport.getLocalizationContext(pageContext);
	    }
	} else {
	    // localization context taken from 'bundle' attribute
	    if (locCtxt.getLocale() != null) {
		SetLocaleSupport.setResponseLocale(pageContext,
						   locCtxt.getLocale());
	    }
	}
        
 	String message = UNDEFINED_KEY + keyInput + UNDEFINED_KEY;
	if (locCtxt != null) {
	    ResourceBundle bundle = locCtxt.getResourceBundle();
	    if (bundle != null) {
		try {
		    // prepend 'prefix' attribute from parent bundle
		    if (prefix != null)
			keyInput = prefix + keyInput;
		    message = bundle.getString(keyInput);
		    // Perform parametric replacement if required
		    if (!params.isEmpty()) {
			Object[] messageArgs = params.toArray();
			MessageFormat formatter = new MessageFormat(""); // empty pattern, default Locale
			if (locCtxt.getLocale() != null) {
			    formatter.setLocale(locCtxt.getLocale());
			} else {
                            // For consistency with the <fmt:formatXXX> actions,
                            // we try to get a locale that matches the user's preferences
                            // as well as the locales supported by 'date' and 'number'.
                            //System.out.println("LOCALE-LESS LOCCTXT: GETTING FORMATTING LOCALE");
                            Locale locale = SetLocaleSupport.getFormattingLocale(pageContext);
                            //System.out.println("LOCALE: " + locale);
                            if (locale != null) {
                                formatter.setLocale(locale);
                            }
                        }
			formatter.applyPattern(message);
			message = formatter.format(messageArgs);
		    }
		} catch (MissingResourceException mre) {
		    message = UNDEFINED_KEY + keyInput + UNDEFINED_KEY;
		}
	    }
	}

	if (var != null) {
	    pageContext.setAttribute(var, message, scope);	
	} else {
	    try {
		pageContext.getOut().print(message);
	    } catch (IOException ioe) {
		throw new JspTagException(ioe.toString(), ioe);
	    }
	}

	return EVAL_PAGE;
    }

    // Releases any resources we may have (or inherit)
    public void release() {
	init();
    }
}
