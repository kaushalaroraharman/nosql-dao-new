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

import org.junit.Assert;
import org.junit.Test;

/**
 * CriteriaTest class.
 */
public class CriteriaTest {
    @Test
    public void testBuild() {
        final String expected = "(((a=b)and(c>d))and((a=b)or(c>d)))";
        IgniteCriteria c1 = new IgniteCriteria();
        c1.field("a").op(Operator.EQ).val("b");
        IgniteCriteria c2 = new IgniteCriteria();
        c2.field("c").op(Operator.GT).val("d");
        IgniteCriteriaGroup cg1 = new IgniteCriteriaGroup(c1);
        cg1.and(c2);
        IgniteCriteriaGroup cg2 = new IgniteCriteriaGroup(c1);
        cg2.or(c2);
        IgniteQuery q = new IgniteQuery(cg1);
        q.and(cg2);
        Assert.assertEquals("", expected, q.toString());
    }
}
