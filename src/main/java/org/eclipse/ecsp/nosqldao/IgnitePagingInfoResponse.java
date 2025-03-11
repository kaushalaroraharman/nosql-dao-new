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

import java.util.List;

/**
 * The Pojo Class Ignite paging info response.
 *
 * @param <E>
 *         the type parameter
 */
public class IgnitePagingInfoResponse<E> {

    /**
     * The data.
     */
    private List<E> data;

    /**
     * The total.
     */
    private long total;

    /**
     * Instantiates a new Ignite paging info response.
     *
     * @param data
     *         the data
     * @param total
     *         the total
     */
    public IgnitePagingInfoResponse(List<E> data, long total) {
        this.data = data;
        this.total = total;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public List<E> getData() {
        return data;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public long getTotal() {
        return total;
    }
}
