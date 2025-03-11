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

import dev.morphia.query.FindOptions;

import java.util.Optional;

/**
 * This interface methods for QueryTranslator extending Translator.

 * @param <T> : Generic
 */
public interface QueryTranslator<T> extends Translator<IgniteQuery, T> {

    /**
     * Translate.
     *
     * @param from the from
     * @param collectionName the collection name
     * @return the t
     */
    public T translate(IgniteQuery from, Optional<String> collectionName);

    /**
     * Gets find options.
     *
     * @return the find options
     */
    FindOptions getFindOptions();
}
