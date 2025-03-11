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
 * The enum Operator.
 */
public enum Operator {

    /**
     * Equals operator.
     */
    EQ("="),
    /**
     * Equals ignore case operator.
     */
    EQI("equalsIgnoreCase"),
    /**
     * Less than operator.
     */
    LT("<"),
    /**
     * Less than or equal to operator.
     */
    LTE("<="),
    /**
     * Greater than operator.
     */
    GT(">"),
    /**
     * Greater than or equal to operator.
     */
    GTE(">="),
    /**
     * Not equal operator.
     */
    NEQ("!="),
    /**
     * And operator.
     */
    AND("and"),
    /**
     * Or operator.
     */
    OR("or"),
    /**
     * Contains operator.
     */
    CONTAINS("contains"),
    /**
     * Contains ignore case operator.
     */
    CONTAINS_IGNORE_CASE("containsIgnoreCase"),
    /**
     * In operator.
     */
    IN("in"),
    /**
     * Not in operator.
     */
    NOT_IN("notIn"),
    /**
     * Element match operator.
     */
    ELEMENT_MATCH("elementMatch"),
    /**
     * Near operator.
     */
    NEAR("near");

    /**
     * The Op.
     */
    private String op;

    /**
     * Instantiates a new Operator.
     *
     * @param op the op
     */
    private Operator(String op) {
        this.op = op;
    }

    @Override
    public String toString() {
        return op;
    }
}
