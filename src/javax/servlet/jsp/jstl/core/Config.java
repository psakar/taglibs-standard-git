/*
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights 
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

package javax.servlet.jsp.jstl.core;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.jsp.PageContext;
import javax.servlet.http.HttpSession;

/**
 * Class supporting access to configuration data.
 */
public class Config {

    /**
     * I18N/Formatting actions related configuration data
     */
    public static final String FMT_LOCALE
	= "javax.servlet.jsp.jstl.fmt.locale";
    public static final String FMT_FALLBACKLOCALE
	= "javax.servlet.jsp.jstl.fmt.fallbackLocale";
    public static final String FMT_LOCALIZATIONCONTEXT
	= "javax.servlet.jsp.jstl.fmt.localizationContext";
    public static final String FMT_TIMEZONE
	= "javax.servlet.jsp.jstl.fmt.timeZone";

    /**
     * SQL actions related configuration data
     */
    public static final String SQL_DATASOURCE
	= "javax.servlet.jsp.jstl.sql.dataSource";
    public static final String SQL_MAXROWS
	= "javax.servlet.jsp.jstl.sql.maxRows";
	
    /*
     * Private constants
     */
    private static final String PAGE_SCOPE_SUFFIX = ".page";
    private static final String REQUEST_SCOPE_SUFFIX = ".request";
    private static final String SESSION_SCOPE_SUFFIX = ".session";
    private static final String APPLICATION_SCOPE_SUFFIX = ".application";

    /**
     * Looks up a configuration variable in the given scope.
     *
     * <p> The lookup of configuration variables is performed as if each scope
     * had its own name space, that is, the same configuration variable name
     * in one scope does not replace one stored in a different scope.
     *
     * @param pc 
     * @param name Configuration variable name
     * @param scope Configuration scope
     *
     * @return The <tt>java.lang.Object</tt> associated with the configuration
     * variable, or null if it is not defined.
     */
    public static Object get(PageContext pc, String name, int scope) {
	switch (scope) {
	case PageContext.PAGE_SCOPE:
	    return pc.getAttribute(name + PAGE_SCOPE_SUFFIX, scope);
	case PageContext.REQUEST_SCOPE:
	    return pc.getAttribute(name + REQUEST_SCOPE_SUFFIX, scope);
	case PageContext.SESSION_SCOPE:
	    return pc.getAttribute(name + SESSION_SCOPE_SUFFIX, scope);
	case PageContext.APPLICATION_SCOPE:
	    return pc.getAttribute(name + APPLICATION_SCOPE_SUFFIX, scope);
	default:
	    throw new IllegalArgumentException("unknown scope");
	}
    }

    /**
     * Looks up a configuration variable in the "request" scope.
     *
     * <p> The lookup of configuration variables is performed as if each scope
     * had its own name space, that is, the same configuration variable name
     * in one scope does not replace one stored in a different scope.
     *
     * @param request
     * @param name Configuration variable name
     *
     * @return The <tt>java.lang.Object</tt> associated with the configuration
     * variable, or null if it is not defined.
     */
    public static Object get(ServletRequest request, String name) {
	return request.getAttribute(name + REQUEST_SCOPE_SUFFIX);
    }

    /**
     * Looks up a configuration variable in the "session" scope.
     *
     * <p> The lookup of configuration variables is performed as if each scope
     * had its own name space, that is, the same configuration variable name
     * in one scope does not replace one stored in a different scope.
     *
     * @param session
     * @param name Configuration variable name
     *
     * @return The <tt>java.lang.Object</tt> associated with the configuration
     * variable, or null if it is not defined.
     */
    public static Object get(HttpSession session, String name) {
	return session.getAttribute(name + SESSION_SCOPE_SUFFIX);
    }

    /**
     * Looks up a configuration variable in the "application" scope.
     *
     * <p> The lookup of configuration variables is performed as if each scope
     * had its own name space, that is, the same configuration variable name
     * in one scope does not replace one stored in a different scope.
     *
     * @param context
     * @param name Configuration variable name
     *
     * @return The <tt>java.lang.Object</tt> associated with the configuration
     * variable, or null if it is not defined.
     */
    public static Object get(ServletContext context, String name) {
	return context.getAttribute(name + APPLICATION_SCOPE_SUFFIX);
    }

    /**
     * Sets the value of a configuration variable in the given scope.
     *
     * <p> Setting the value of a configuration variable is performed as if
     * each scope had its own namespace, that is, the same configuration
     * variable name in one scope does not replace one stored in a different
     * scope.
     *
     * @param pc
     * @param name Configuration variable name
     * @param value
     * @param scope
     */
    public static void set(PageContext pc, String name, Object value,
			   int scope) {
	switch (scope) {
	case PageContext.PAGE_SCOPE:
	    pc.setAttribute(name + PAGE_SCOPE_SUFFIX, value, scope);
	    break;
	case PageContext.REQUEST_SCOPE:
	    pc.setAttribute(name + REQUEST_SCOPE_SUFFIX, value, scope);
	    break;
	case PageContext.SESSION_SCOPE:
	    pc.setAttribute(name + SESSION_SCOPE_SUFFIX, value, scope);
	    break;
	case PageContext.APPLICATION_SCOPE:
	    pc.setAttribute(name + APPLICATION_SCOPE_SUFFIX, value, scope);
	    break;
	default:
	    throw new IllegalArgumentException("unknown scope");
	}
    }

    /**
     * Sets the value of a configuration variable in the "request" scope.
     *
     * <p> Setting the value of a configuration variable is performed as if
     * each scope had its own namespace, that is, the same configuration
     * variable name in one scope does not replace one stored in a different
     * scope.
     *
     * @param request
     * @param name Configuration variable name
     * @paran value
     */
    public static void set(ServletRequest request, String name, Object value) {
	request.setAttribute(name + REQUEST_SCOPE_SUFFIX, value);
    }

    /**
     * Sets the value of a configuration variable in the "session" scope.
     *
     * <p> Setting the value of a configuration variable is performed as if
     * each scope had its own namespace, that is, the same configuration
     * variable name in one scope does not replace one stored in a different
     * scope.
     *
     * @param session
     * @param name Configuration variable name
     * @param value
     */
    public static void set(HttpSession session, String name, Object value) {
	session.setAttribute(name + SESSION_SCOPE_SUFFIX, value);
    }

    /**
     * Sets the value of a configuration variable in the "application" scope.
     *
     * <p> Setting the value of a configuration variable is performed as if
     * each scope had its own namespace, that is, the same configuration
     * variable name in one scope does not replace one stored in a different
     * scope.
     *
     * @param context
     * @param name Configuration variable name
     * @param value
     */
    public static void set(ServletContext context, String name, Object value) {
	context.setAttribute(name + APPLICATION_SCOPE_SUFFIX, value);
    }
 
    /**
     * Removes a configuration variable from the given scope.
     *
     * <p> Removing a configuration variable is performed as if each scope had
     * its own namespace, that is, the same configuration variable name in one
     * scope does not impact one stored in a different scope.
     *
     * @param pc
     * @param name Configuration variable name
     * @param scope
     */
    public static void remove(PageContext pc, String name, int scope) {
	switch (scope) {
	case PageContext.PAGE_SCOPE:
	    pc.removeAttribute(name + PAGE_SCOPE_SUFFIX, scope);
	    break;
	case PageContext.REQUEST_SCOPE:
	    pc.removeAttribute(name + REQUEST_SCOPE_SUFFIX, scope);
	    break;
	case PageContext.SESSION_SCOPE:
	    pc.removeAttribute(name + SESSION_SCOPE_SUFFIX, scope);
	    break;
	case PageContext.APPLICATION_SCOPE:
	    pc.removeAttribute(name + APPLICATION_SCOPE_SUFFIX, scope);
	    break;
	default:
	    throw new IllegalArgumentException("unknown scope");
	}
    }

    /**
     * Removes a configuration variable from the "request" scope.
     *
     * <p> Removing a configuration variable is performed as if each scope had
     * its own namespace, that is, the same configuration variable name in one
     * scope does not impact one stored in a different scope.
     * 
     * @param request
     * @param name Configuration variable name
     */
    public static void remove(ServletRequest request, String name) {
	request.removeAttribute(name + REQUEST_SCOPE_SUFFIX);
    }

    /**
     * Removes a configuration variable from the "session" scope.
     *
     * <p> Removing a configuration variable is performed as if each scope had
     * its own namespace, that is, the same configuration variable name in one
     * scope does not impact one stored in a different scope.
     *
     * @param session
     * @param name Configuration variable name
     */
    public static void remove(HttpSession session, String name) {
	session.removeAttribute(name + SESSION_SCOPE_SUFFIX);
    }

    /**
     * Removes a configuration variable from the "application" scope.
     *
     * <p> Removing a configuration variable is performed as if each scope had
     * its own namespace, that is, the same configuration variable name in one
     * scope does not impact one stored in a different scope.
     *
     * @param context
     * @param name Configuration variable name
     */
    public static void remove(ServletContext context, String name) {
	context.removeAttribute(name + APPLICATION_SCOPE_SUFFIX);
    }
 
    /**
     * Finds the value associated with a specific configuration setting
     * identified by its context initialization parameter name (argument
     * <tt>name</tt>).
     *
     * <p> For each of the JSP scopes (page, request, session, application),
     * get the value of the configuration variable identified by <tt>name</tt>
     * using method <tt>get()</tt>. Return as soon as a non-null value is
     * found. If no value is found, get the value of the context initialization
     * parameter identified by <tt>name</tt>.
     *
     * @param pc
     * @param name Configuration variable name
     * 
     * @return The <tt>java.lang.Object</tt> associated with the configuration
     * setting identified by <tt>name</tt>, or null if it is not defined.
     */
    public static Object find(PageContext pc, String name) {
	Object ret = get(pc, name, PageContext.PAGE_SCOPE);
	if (ret == null) {
	    ret = get(pc, name, PageContext.REQUEST_SCOPE);
	    if (ret == null) {
		ret = get(pc, name, PageContext.SESSION_SCOPE);
		if (ret == null) {
		    ret = get(pc, name, PageContext.APPLICATION_SCOPE);
		    if (ret == null) {
			ret = pc.getServletContext().getInitParameter(name);
		    }
		}
	    }
	}

	return ret;
    }
}
