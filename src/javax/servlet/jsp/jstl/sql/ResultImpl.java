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

package javax.servlet.jsp.jstl.sql;

import java.sql.*;
import java.util.*;

/**
 * <p>This class creates a cached version of a <code>ResultSet</code>.
 * It's represented as a <code>Result</code> implementation, capable of 
 * returing an array of <code>Row</code> objects containing a <code>Column</code> 
 * instance for each column in the row. 
 *
 * @author Hans Bergsten
 * @author Justyna Horwat
 */

public class ResultImpl implements Result {
    private List rowMap;
    private List rowByIndex;
    private boolean isLimited;

    /**
     * This constructor reads the ResultSet and saves a cached
     * copy.
     *
     * @param rs an open <code>ResultSet</code>, positioned before the 
     *   first row
     * @param startRow, beginning row to be cached
     * @param maxRows, query maximum rows limit
     * @exception if a database error occurs
     */
    public ResultImpl(ResultSet rs, int startRow, int maxRows) throws SQLException {
	rowMap = new ArrayList();
	rowByIndex = new ArrayList();

	ResultSetMetaData rsmd = rs.getMetaData();
	int noOfColumns = rsmd.getColumnCount();
        int beginRow = 0;

        /*
         * Shift maximum rows depending on starting point
         */
        if ((maxRows > 0) && (startRow > 0)) {
            maxRows = maxRows + startRow;
        }

	while (rs.next()) {
            if ((maxRows < 0) || (beginRow < maxRows)) {
                if (beginRow >= startRow) {
                    Object[] columns = new Object[noOfColumns];
                    Map columnMap = new HashMap();

	            // JDBC uses 1 as the lowest index!
	            for (int i = 1; i <= noOfColumns; i++) {
		        Object value =  rs.getObject(i);
		        if (rs.wasNull()) {
		            value = null;
		        }
                        // 0-based indexing to be consistent w/JSTL 
                        columns[i-1] = value;
                        columnMap.put(rsmd.getColumnName(i), value);
	            }
                rowMap.add(columnMap);
                rowByIndex.add(columns);
                }
            beginRow++;
            }
	}

        if (maxRows > 0) { 
            isLimited = true; 
        } else { 
            isLimited = false; 
        }
    }

    /**
     * Returns an array of Map objects. The Map object key is
     * the ColumnName and the value is the ColumnValue.
     *
     * @return an array of Map, or null if there are no rows
     */
    public Map[] getRows() {
        if (rowMap == null) {
            return null;
        }

        //should just be able to return Map[] object
        return (Map []) rowMap.toArray(new Map[0]);
    }


    /**
     * Returns an array of Object[] objects. The first index
     * designates the Row, the second the Column. The array
     * stores the value at the specified row and column.
     *
     * @return an array of Object[], or null if there are no rows
     */
    public Object[][] getRowsByIndex() {
        if (rowByIndex == null) {
            return null;
        }

        //should just be able to return Object[][] object
        return (Object [][])rowByIndex.toArray(new Object[0][0]);
    }

    /**
     * Returns the number of rows in the cached ResultSet
     *
     * @return the number of cached rows, or -1 if the Result could
     *    not be initialized due to SQLExceptions
     */
    public int getRowCount() {
	if (rowMap == null) {
	    return -1;
	}
	return rowMap.size();
    }

    /**
     * Returns true of the query was limited by a maximum row setting
     *
     * @return true if the query was limited by a MaxRows attribute
     */
    public boolean isLimitedByMaxRows() {
        return isLimited;
    }

}
