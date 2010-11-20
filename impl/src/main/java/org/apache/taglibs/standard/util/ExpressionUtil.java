/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.taglibs.standard.util;

import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.servlet.jsp.JspApplicationContext;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/**
 * Helper functions for working with EL expressions.
 */
public class ExpressionUtil {

    /**
     * Create a value expression.
     *
     * @param pageContext  the context in which the expression will be parsed
     * @param expression   the expression
     * @param expectedType the expected type of result
     * @return a parsed expression
     */
    public static ValueExpression createValueExpression(PageContext pageContext, String expression, Class<?> expectedType) {
        ExpressionFactory factory = getExpressionFactory(pageContext);
        return factory.createValueExpression(pageContext.getELContext(), expression, expectedType);
    }

    /**
     * Return the JSP's ExpressionFactory.
     *
     * @param pageContext the context for the JSP
     * @return the ExpressionFactory to use for EL expressions in that JSP
     */
    public static ExpressionFactory getExpressionFactory(PageContext pageContext) {
        JspApplicationContext appContext = JspFactory.getDefaultFactory().getJspApplicationContext(pageContext.getServletContext());
        return appContext.getExpressionFactory();
    }
}
