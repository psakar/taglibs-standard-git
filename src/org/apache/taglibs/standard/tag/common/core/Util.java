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

package org.apache.taglibs.standard.tag.common.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;

import org.apache.taglibs.standard.resources.Resources;

/**
 * <p>Utilities in support of tag-handler classes.</p>
 *
 * @author Jan Luehe
 */
public class Util {

    private static final String REQUEST = "request";   
    private static final String SESSION = "session";   
    private static final String APPLICATION = "application"; 
    private static final String DEFAULT = "default";
    private static final String SHORT = "short";
    private static final String MEDIUM = "medium";
    private static final String LONG = "long";
    private static final String FULL = "full";

    /*
     * Converts the given string description of a scope to the corresponding
     * PageContext constant.
     *
     * The validity of the given scope has already been checked by the
     * appropriate TLV.
     *
     * @param scope String description of scope
     *
     * @return PageContext constant corresponding to given scope description
     */
    public static int getScope(String scope) {
	int ret = PageContext.PAGE_SCOPE; // default

	if (REQUEST.equalsIgnoreCase(scope))
	    ret = PageContext.REQUEST_SCOPE;
	else if (SESSION.equalsIgnoreCase(scope))
	    ret = PageContext.SESSION_SCOPE;
	else if (APPLICATION.equalsIgnoreCase(scope))
	    ret = PageContext.APPLICATION_SCOPE;

	return ret;
    }

    /*
     * Converts the given string description of a formatting style for
     * dates and times to the corresponding java.util.DateFormat constant.
     *
     * @param style String description of formatting style for dates and times
     * @param errCode Error code to throw if given style is invalid
     *
     * @return java.util.DateFormat constant corresponding to given style
     *
     * @throws JspException if the given style is invalid
     */
    public static int getStyle(String style, String errCode)
	                throws JspException {
	int ret = DateFormat.DEFAULT;

	if (style != null) {
	    if (DEFAULT.equalsIgnoreCase(style)) {
		ret = DateFormat.DEFAULT;
	    } else if (SHORT.equalsIgnoreCase(style)) {
		ret = DateFormat.SHORT;
	    } else if (MEDIUM.equalsIgnoreCase(style)) {
		ret = DateFormat.MEDIUM;
	    } else if (LONG.equalsIgnoreCase(style)) {
		ret = DateFormat.LONG;
	    } else if (FULL.equalsIgnoreCase(style)) {
		ret = DateFormat.FULL;
	    } else {
		throw new JspException(Resources.getMessage(errCode, style));
	    }
	}

	return ret;
    }
    
    /**
     * Performs the following substring replacements 
     * (to facilitate output to XML/HTML pages):
     *
     *    & -> &amp;
     *    < -> &lt;
     *    > -> &gt;
     *    " -> &#034;
     *    ' -> &#039;
     *
     * See also OutSupport.out().
     */
    public static String escapeXml(String input) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '&')
                sb.append("&amp;");
            else if (c == '<')
                sb.append("&lt;");
            else if (c == '>')
                sb.append("&gt;");
            else if (c == '"')
                sb.append("&#034;");
            else if (c == '\'')
                sb.append("&#039;");
            else
                sb.append(c);
        }
        return sb.toString();
    }  
    
    /**
     * Get the value associated with a content-type attribute.
     * Syntax defined in RFC 2045, section 5.1.
     */
    public static String getContentTypeAttribute(String input, String name) {
	int begin;
	int end;
        int index = input.toUpperCase().indexOf(name.toUpperCase());
        if (index == -1) return null;
        index = index + name.length(); // positioned after the attribute name
        index = input.indexOf('=', index); // positioned at the '='
        if (index == -1) return null;
        index += 1; // positioned after the '='
        input = input.substring(index).trim();
        
        if (input.charAt(0) == '"') {
            // attribute value is a quoted string
            begin = 1;
            end = input.indexOf('"', begin);
            if (end == -1) return null;
        } else {
            begin = 0;
            end = input.indexOf(';');
            if (end == -1) end = input.indexOf(' ');
            if (end == -1) end = input.length();
        }
        return input.substring(begin, end).trim();
    }
    
    /**
     * URL encodes a string, based on the supplied character encoding.
     * This performs the same function as java.next.URLEncode.encode
     * in J2SDK1.4, and should be removed if the only platform supported
     * is 1.4 or higher.
     * @param s The String to be URL encoded.
     * @param enc The character encoding 
     * @return The URL encoded String
     * [taken from jakarta-tomcat-jasper/jasper2
     *  org.apache.jasper.runtime.JspRuntimeLibrary.java]
     */
    public static String URLEncode(String s, String enc) {

	if (s == null) {
	    return "null";
	}

	if (enc == null) {
	    enc = "UTF-8";	// Is this right?
	}

	StringBuffer out = new StringBuffer(s.length());
	ByteArrayOutputStream buf = new ByteArrayOutputStream();
	OutputStreamWriter writer = null;
	try {
	    writer = new OutputStreamWriter(buf, enc);
	} catch (java.io.UnsupportedEncodingException ex) {
	    // Use the default encoding?
	    writer = new OutputStreamWriter(buf);
	}
	
	for (int i = 0; i < s.length(); i++) {
	    int c = s.charAt(i);
	    if (c == ' ') {
		out.append('+');
	    } else if (isSafeChar(c)) {
		out.append((char)c);
	    } else {
		// convert to external encoding before hex conversion
		try {
		    writer.write(c);
		    writer.flush();
		} catch(IOException e) {
		    buf.reset();
		    continue;
		}
		byte[] ba = buf.toByteArray();
		for (int j = 0; j < ba.length; j++) {
		    out.append('%');
		    // Converting each byte in the buffer
		    out.append(Character.forDigit((ba[j]>>4) & 0xf, 16));
		    out.append(Character.forDigit(ba[j] & 0xf, 16));
		}
		buf.reset();
	    }
	}
	return out.toString();
    }

    private static boolean isSafeChar(int c) {
	if (c >= 'a' && c <= 'z') {
	    return true;
	}
	if (c >= 'A' && c <= 'Z') {
	    return true;
	}
	if (c >= '0' && c <= '9') {
	    return true;
	}
	if (c == '-' || c == '_' || c == '.' || c == '!' ||
	    c == '~' || c == '*' || c == '\'' || c == '(' || c == ')') {
	    return true;
	}
	return false;
    }    
    
    /**
     * HttpServletRequest.getLocales() returns the server's default locale 
     * if the request did not specify a preferred language.
     * We do not want this behavior, because it prevents us from using
     * the fallback locale. 
     * We therefore need to return an empty Enumeration if no preferred 
     * locale has been specified. This way, the logic for the fallback 
     * locale will be able to kick in.
     */
    public static Enumeration getRequestLocales(HttpServletRequest request) {        
        Enumeration values = request.getHeaders("accept-language");
        if (values.hasMoreElements()) {
            // At least one "accept-language". Simply return
            // the enumeration returned by request.getLocales().
            // System.out.println("At least one accept-language");
            return request.getLocales();
        } else {
            // No header for "accept-language". Simply return
            // the empty enumeration.
            // System.out.println("No accept-language");
            return values;
        }
    }
}
