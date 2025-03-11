/*
 *
 *
 *   *******************************************************************************
 *
 *     Copyright (c) 2023-24 Harman International
 *
 *
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *
 *     you may not use this file except in compliance with the License.
 *
 *     You may obtain a copy of the License at
 *
 *
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *
 *
 *     Unless required by applicable law or agreed to in writing, software
 *
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *
 *     See the License for the specific language governing permissions and
 *
 *     limitations under the License.
 *
 *
 *
 *     SPDX-License-Identifier: Apache-2.0
 *
 *    *******************************************************************************
 *
 *
 */

package org.eclipse.ecsp.nosqldao.mongodb;

/**
 * Exception class for unsupported database type selected.
 */
public class UnsupportedDatabaseTypeException extends RuntimeException {

    /**
     * The serial version UID for this class.
     */
    private static final long serialVersionUID = 4999018857659415L;

    /**
     * Constructs a new UnsupportedDatabaseTypeException with the specified detail message.
     *
     * @param s the detail message
     */
    public UnsupportedDatabaseTypeException(String s) {
        super(s);
    }
}
