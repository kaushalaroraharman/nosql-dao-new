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

import com.mongodb.ReadPreference;
import org.eclipse.ecsp.nosqldao.utils.IgniteQueryUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This Class provides methods for Ignite Queries extending IgniteQueryUtils.
 */
public class IgniteQuery extends IgniteQueryUtils {

    /**
     * The Ignite criteria groups.
     */
    private List<IgniteCriteriaGroup> igniteCriteriaGroups = new ArrayList<>();

    /**
     * The Ignite query operators list.
     */
    private List<LogicalOperator> igniteQueryOperatorsList = new ArrayList<>();

    /**
     * The order bys.
     */
    private List<IgniteOrderBy> orderBys = new ArrayList<>();

    /**
     * The page size.
     */
    private int pageSize;

    /**
     * The page number.
     */
    private int pageNumber;

    /**
     * The field names.
     */
    private String[] fieldNames;

    /**
     * The read preference.
     */
    private ReadPreference readPreference;

    /**
     * Default constructor for IgniteQuery.
     */
    public IgniteQuery() {
    }

    /**
     * Instantiates a new Ignite query.
     *
     * @param cg
     *         the cg
     */
    public IgniteQuery(IgniteCriteriaGroup cg) {
        igniteCriteriaGroups.add(cg);
    }

    /**
     * And ignite query.
     *
     * @param cg
     *         the cg
     * @return the ignite query
     */
    public IgniteQuery and(IgniteCriteriaGroup cg) {
        igniteCriteriaGroups.add(cg);
        igniteQueryOperatorsList.add(LogicalOperator.AND);
        resolveLopContent(LogicalOperator.AND);
        return this;
    }

    /**
     * Or ignite query.
     *
     * @param cg
     *         the cg
     * @return the ignite query
     */
    public IgniteQuery or(IgniteCriteriaGroup cg) {
        igniteCriteriaGroups.add(cg);
        igniteQueryOperatorsList.add(LogicalOperator.OR);
        resolveLopContent(LogicalOperator.OR);
        return this;
    }

    /**
     * Gets criteria groups.
     *
     * @return the criteria groups
     */
    public List<IgniteCriteriaGroup> getCriteriaGroups() {
        return igniteCriteriaGroups;
    }

    /**
     * Gets ignite query operators list.
     *
     * @return the ignite query operators list
     */
    public List<LogicalOperator> getIgniteQueryOperatorsList() {
        return igniteQueryOperatorsList;
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
     * Order by ignite query.
     *
     * @param orderBy
     *         the order by
     * @return the ignite query
     */
    public IgniteQuery orderBy(IgniteOrderBy orderBy) {
        orderBys.add(orderBy);
        return this;
    }

    /**
     * Gets order bys.
     *
     * @return the order bys
     */
    public List<IgniteOrderBy> getOrderBys() {
        return orderBys;
    }

    /**
     * Gets page number.
     *
     * @return the page number
     */
    public int getPageNumber() {
        return this.pageNumber;
    }

    /**
     * Sets the page number.
     *
     * @param pageNumber the page number to set, must be greater than 0
     * @throws IllegalArgumentException if the page number is less than or equal to 0
     */
    public void setPageNumber(int pageNumber) {
        if (pageNumber <= 0) {
            throw new IllegalArgumentException("Page number should be greater than 0.");
        }
        this.pageNumber = pageNumber;
    }

    /**
     * Gets page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return this.pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize the page size to set, must be greater than 0
     * @throws IllegalArgumentException if the page size is less than or equal to 0
     */
    public void setPageSize(int pageSize) {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than 0.");
        }
        this.pageSize = pageSize;
    }

    /**
     * Get field names string [ ].
     *
     * @return the string [ ]
     */
    public String[] getFieldNames() {
        if (fieldNames != null) {
            return fieldNames.clone();
        }
        return new String[] {};
    }

    /**
     * Sets field names.
     *
     * @param fieldNames
     *         the field names
     */
    public void setFieldNames(String[] fieldNames) {
        if (fieldNames != null) {
            this.fieldNames = fieldNames.clone();
        }
    }

    /**
     * Gets read preference.
     *
     * @return the read preference
     */
    public ReadPreference getReadPreference() {
        return readPreference;
    }

    /**
     * Sets read preference.
     *
     * @param readPreference
     *         the read preference
     */
    public void setReadPreference(ReadPreference readPreference) {
        this.readPreference = readPreference;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();
        buffer.append("(");
        int size = igniteCriteriaGroups.size();
        for (int i = 0; i < size; i++) {
            buffer.append(igniteCriteriaGroups.get(i));
            if (i != igniteCriteriaGroups.size() - 1) {
                buffer.append(igniteQueryOperatorsList.get(i));
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
        int size = igniteCriteriaGroups.size();
        for (int i = 0; i < size; i++) {
            buffer.append(igniteCriteriaGroups.get(i).toTemplatedQueryString());
            if (i != igniteCriteriaGroups.size() - 1) {
                buffer.append(igniteQueryOperatorsList.get(i));
            }
        }
        buffer.append(")");
        return buffer.toString();
    }
}