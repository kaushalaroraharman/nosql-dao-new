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

package org.eclipse.ecsp.nosqldao;

import java.util.Optional;
import java.util.Properties;

/**
 * This interface provides methods for Query Translator.
 */
public interface Translator<F, T> {

    /**
     * Initialize the translator.
     *
     * @param properties the properties
     */
    public default void init(Properties properties) {
    }

    /**
     * Translate the given object.
     *
     * @param from the object to be translated
     * @param collectionName the collection name
     * @return the translated object
     */
    public T translate(F from, Optional<String> collectionName);
}
