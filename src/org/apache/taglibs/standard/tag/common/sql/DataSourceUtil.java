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

package org.apache.taglibs.standard.tag.common.sql;

import javax.sql.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.jstl.core.Config;
import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;
import org.apache.taglibs.standard.resources.Resources;


/**
 * <p>A simple <code>DataSource</code> utility for the standard
 * <code>DriverManager</code> class.
 *
 * TO DO: need to cache DataSource
 * 
 * @author Justyna Horwat
 */
public class DataSourceUtil {

    private static String ESCAPE = "\\";
    private static String TOKEN = ",";
    private DataSource dataSource;
    private boolean hasDataSourceAttribute = false;

    DataSourceUtil() {
        dataSource = new DataSourceWrapper();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Useful for Transaction nesting check
     **/
    public boolean hasDataSourceAttribute() {
        return hasDataSourceAttribute;
    }

    /**
     *
     * If dataSource is a String first do JNDI lookup
     * If lookup fails parse String like it was a set of JDBC parameters
     * Otherwise check to see if dataSource is a DataSource object and use as is
     *
     **/
    public void setDataSource(Object rawDataSource, PageContext pageContext) throws JspException {

        if (rawDataSource == null) {
            rawDataSource = Config.find(pageContext, Config.SQL_DATASOURCE);
            hasDataSourceAttribute = false;
        }
        else {
            hasDataSourceAttribute = true;
        }

        // If the 'dataSource' attribute's value resolves to a String
        // after rtexpr/EL evaluation, use the string as JNDI path to
        // a DataSource
        if (rawDataSource instanceof String) {
            try {
                Context ctx = new InitialContext();
                // relative to standard JNDI root for J2EE app
                Context envCtx = (Context) ctx.lookup("java:comp/env");
                dataSource = (DataSource) envCtx.lookup((String) rawDataSource);
            } catch (NamingException ex) {
                setUsingParams((String) rawDataSource);
            }
        } else if (rawDataSource instanceof DataSource) {
            dataSource = (DataSource) rawDataSource;
        }
    }

    /**
     *
     * Parse JDBC parameters and setup dataSource appropriately
     *
     **/
    private void setUsingParams(String params) throws JspException {
        dataSource = (DataSource) new DataSourceWrapper();

        String[] paramString = new String[4];
        int escCount = 0; 
        int aryCount = 0; 
        int begin = 0;

        for(int index=0; index < params.length(); index++) {
            char nextChar = params.charAt(index);
            if (TOKEN.indexOf(nextChar) != -1) {
                if (escCount == 0) {
                    paramString[aryCount] = params.substring(begin,index);
                    begin = index + 1;
                    if (++aryCount > 4) {
                        throw new JspTagException(
                            Resources.getMessage("JDBC_PARAM_COUNT"));
                    }
                }
            }
            if (ESCAPE.indexOf(nextChar) != -1) {
                escCount++;
            }
            else {
                escCount = 0;
            }
        }
        paramString[aryCount] = params.substring(begin);

	// use the JDBC URL from the parameter string
        ((DataSourceWrapper)dataSource).setJdbcURL(paramString[0]);

	// try to load a driver if it's present
        if (paramString[1] != null) {
            try {
                ((DataSourceWrapper)dataSource).setDriverClassName(paramString[1]);
            } catch (Exception ex) {
                throw new JspTagException(
                    Resources.getMessage("DRIVER_INVALID_CLASS", ex.getMessage()));
            }
	}

	// set the username and password
        ((DataSourceWrapper)dataSource).setUserName(paramString[2]);
        ((DataSourceWrapper)dataSource).setPassword(paramString[3]);
    }

}
