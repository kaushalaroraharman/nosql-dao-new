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

/**
 * The Pojo Class for Ignite order by.
 */
public class IgniteOrderBy {

    /**
     * The name of field.
     */
    private String fieldName;

    /**
     * The order.
     */
    private Order order;

    /**
     * Default constructor.
     * Initializes the order to ascending.
     */
    public IgniteOrderBy() {
        order = Order.ASC;
    }

    /**
     * Gets the field name.
     *
     * @return the field name
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Gets the order.
     *
     * @return the order
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the field name to order by.
     *
     * @param fieldName the field name to set
     * @return the updated IgniteOrderBy object
     */
    public IgniteOrderBy byfield(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    /**
     * Sets the order to ascend.
     *
     * @return the updated IgniteOrderBy object
     */
    public IgniteOrderBy asc() {
        this.order = Order.ASC;
        return this;
    }

    /**
     * Sets the order to descend.
     *
     * @return the updated IgniteOrderBy object
     */
    public IgniteOrderBy desc() {
        this.order = Order.DESC;
        return this;
    }
}