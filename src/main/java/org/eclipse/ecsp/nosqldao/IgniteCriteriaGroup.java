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

import org.eclipse.ecsp.nosqldao.utils.IgniteQueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Ignite criteria group.
 */
public class IgniteCriteriaGroup extends IgniteQueryUtils {

    /**
     * The Ignite criterias.
     */
    private List<IgniteCriteria> igniteCriterias = new ArrayList<>();

    /**
     * List of ignite criteria group operators.
     */
    private List<LogicalOperator> igniteCriteriaGroupOperatorsList = new ArrayList<>();

    /**
     * The default constructor.
     */
    public IgniteCriteriaGroup() {
    }

    /**
     * Instantiates a new Ignite criteria group.
     *
     * @param c
     *         the c
     */
    public IgniteCriteriaGroup(IgniteCriteria c) {
        igniteCriterias.add(c);
    }

    /**
     * And ignite criteria group.
     *
     * @param c
     *         the c
     * @return the ignite criteria group
     */
    public IgniteCriteriaGroup and(IgniteCriteria c) {
        igniteCriterias.add(c);
        igniteCriteriaGroupOperatorsList.add(LogicalOperator.AND);
        resolveLopContent(LogicalOperator.AND);
        return this;
    }

    /**
     * Or ignite criteria group.
     *
     * @param c
     *         the c
     * @return the ignite criteria group
     */
    public IgniteCriteriaGroup or(IgniteCriteria c) {
        igniteCriterias.add(c);
        igniteCriteriaGroupOperatorsList.add(LogicalOperator.OR);
        resolveLopContent(LogicalOperator.OR);
        return this;
    }

    /**
     * Gets criterias.
     *
     * @return the criterias
     */
    public List<IgniteCriteria> getCriterias() {
        return igniteCriterias;
    }

    /**
     * Gets ignite criteria group operators list.
     *
     * @return the ignite criteria group operators list
     */
    public List<LogicalOperator> getIgniteCriteriaGroupOperatorsList() {
        return igniteCriteriaGroupOperatorsList;
    }

    /**
     * Gets lop content.
     *
     * @return the lop content
     */
    public LopContent getLopContent() {
        return lopContent;
    }

    /**
     * ToString method.

     * @return String
     */
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("(");
        int size = igniteCriterias.size();
        for (int i = 0; i < size; i++) {
            buffer.append(igniteCriterias.get(i));
            if (i != igniteCriterias.size() - 1) {
                buffer.append(igniteCriteriaGroupOperatorsList.get(i));
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

    /**
     * To templated query string string.
     *
     * @return the string
     */
    public String toTemplatedQueryString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("(");
        int size = igniteCriterias.size();
        for (int i = 0; i < size; i++) {
            buffer.append(igniteCriterias.get(i).toTemplatedQueryString());
            if (i != igniteCriterias.size() - 1) {
                buffer.append(igniteCriteriaGroupOperatorsList.get(i));
            }
        }
        buffer.append(")");
        return buffer.toString();
    }

}
