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

import org.apache.metamodel.schema.ColumnType;

public class ElasticSearchCommonUtils {

    public static final String FIELD_ID = "_id";
    public static final String SYSTEM_PROPERTY_STRIP_INVALID_FIELD_CHARS = "metamodel.elasticsearch.strip_invalid_field_chars";

    /**
     * Field name special characters are:
     * <p>
     * . (used for navigation between name components)
     * <p>
     * # (for delimiting name components in _uid, should work, but is
     * discouraged)
     * <p>
     * * (for matching names)
     *
     * @param fieldName
     * @return
     */
    public static String getValidatedFieldName(String fieldName) {
        if (fieldName == null || fieldName.isEmpty()) {
            throw new IllegalArgumentException("Field name cannot be null or empty");
        }
        if (fieldName.contains(".") || fieldName.contains("#") || fieldName.contains("*")) {
            if ("true".equalsIgnoreCase(System.getProperty(SYSTEM_PROPERTY_STRIP_INVALID_FIELD_CHARS, "true"))) {
                fieldName = fieldName.replace('.', '_').replace('#', '_').replace('*', '_');
            } else {
                throw new IllegalArgumentException("Field name '" + fieldName + "' contains illegal character (.#*)");
            }
        }
        return fieldName;
    }

    public static ColumnType getColumnTypeFromElasticSearchType(final String metaDataFieldType) {
        final ColumnType columnType;
        if (metaDataFieldType.startsWith("date")) {
            columnType = ColumnType.DATE;
        } else if (metaDataFieldType.equals("long")) {
            columnType = ColumnType.BIGINT;
        } else if (metaDataFieldType.equals("string")) {
            columnType = ColumnType.STRING;
        } else if (metaDataFieldType.equals("float")) {
            columnType = ColumnType.FLOAT;
        } else if (metaDataFieldType.equals("boolean")) {
            columnType = ColumnType.BOOLEAN;
        } else if (metaDataFieldType.equals("double")) {
            columnType = ColumnType.DOUBLE;
        } else {
            columnType = ColumnType.STRING;
        }
        return columnType;
    }
}
