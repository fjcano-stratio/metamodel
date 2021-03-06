/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.metamodel.elasticsearch.common;

import java.util.Date;

import junit.framework.TestCase;

public class ElasticSearchDateConverterTest extends TestCase {

    public void testConvertDateOptionalTime() throws Exception {
        String dateToConvert = "2013-01-04T15:55:51.217+01:00";
        Date date = ElasticSearchDateConverter.tryToConvert(dateToConvert);

        assertNotNull(date);
        assertTrue(date.toString().startsWith("Fri Jan 04"));
    }
}
