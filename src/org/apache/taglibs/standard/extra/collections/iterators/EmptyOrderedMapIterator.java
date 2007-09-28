/*
 *  Copyright 2004 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.apache.taglibs.standard.extra.commons.collections.iterators;

import org.apache.taglibs.standard.extra.commons.collections.OrderedMapIterator;
import org.apache.taglibs.standard.extra.commons.collections.ResettableIterator;

/** 
 * Provides an implementation of an empty ordered map iterator.
 *
 * @since Commons Collections 3.1
 * @version $Revision: 218351 $ $Date: 2004-10-20 17:58:23 -0700 (Wed, 20 Oct 2004) $
 * 
 * @author Stephen Colebourne
 */
public class EmptyOrderedMapIterator extends AbstractEmptyIterator implements OrderedMapIterator, ResettableIterator {

    /**
     * Singleton instance of the iterator.
     * @since Commons Collections 3.1
     */
    public static final OrderedMapIterator INSTANCE = new EmptyOrderedMapIterator();

    /**
     * Constructor.
     */
    protected EmptyOrderedMapIterator() {
        super();
    }

}
