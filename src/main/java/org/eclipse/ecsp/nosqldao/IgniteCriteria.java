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
 * This Class provides Ignite Criteria PoJo.
 */
public class IgniteCriteria {
    private String field;
    private Operator op;
    private Object val;

    /**
     * Default constructor.
     */
    public IgniteCriteria() {
    }

    /**
     * Class Constructor.
     *
     * @param field the field to be queried
     * @param op the operator to be used in the query
     * @param val the value to be compared in the query
     */
    public IgniteCriteria(String field, Operator op, Object val) {
        this.field = field;
        this.op = op;
        this.val = val;
    }

    /**
     * Returns a string representation of the IgniteCriteria object.
     *
     * @return a string representation of the IgniteCriteria object
     */
    @Override
    public String toString() {
        return "(" + field + op + val + ")";
    }

    /**
     * Method to create Template to Query String.
     *
     * @return a templated query string
     */
    public String toTemplatedQueryString() {
        return new StringBuilder()
                .append("(")
                .append(field)
                .append(op)
                .append("<v>")
                .append(")")
                .toString();
    }

    /**
     * Gets the field.
     *
     * @return the field
     */
    public String getField() {
        return field;
    }

    /**
     * Sets the field.
     *
     * @param field the field to set
     * @return the updated IgniteCriteria object
     */
    public IgniteCriteria field(String field) {
        this.field = field;
        return this;
    }

    /**
     * Gets the operator.
     *
     * @return the operator
     */
    public Operator getOp() {
        return op;
    }

    /**
     * Sets the operator.
     *
     * @param op the operator to set
     * @return the updated IgniteCriteria object
     */
    public IgniteCriteria op(Operator op) {
        this.op = op;
        return this;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public Object getVal() {
        return val;
    }

    /**
     * Sets the value.
     *
     * @param val the value to set
     * @return the updated IgniteCriteria object
     */
    public IgniteCriteria val(Object val) {
        this.val = val;
        return this;
    }
}
