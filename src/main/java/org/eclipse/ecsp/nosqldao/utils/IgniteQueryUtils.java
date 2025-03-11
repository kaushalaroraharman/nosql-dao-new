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

package org.eclipse.ecsp.nosqldao.utils;

import org.eclipse.ecsp.nosqldao.LogicalOperator;
import org.eclipse.ecsp.nosqldao.LopContent;

/**
 * This class provides utility methods for Ignite Query.
 */
public class IgniteQueryUtils {

    /**
     * The  lop content.
     */
    protected LopContent lopContent;

    /**
     * Resolve lop content.
     *
     * @param currentLop the current lop
     */
    protected void resolveLopContent(LogicalOperator currentLop) {
        if (null == lopContent) {
            if (LogicalOperator.AND == currentLop) {
                lopContent = LopContent.ANDONLY;
            } else if (LogicalOperator.OR == currentLop) {
                lopContent = LopContent.ORONLY;
            }
        } else {
            if (!LopContent.MIXED.equals(lopContent)
                    && (LogicalOperator.AND.equals(currentLop) && !LopContent.ANDONLY.equals(lopContent)
                    || LogicalOperator.OR.equals(currentLop) && !LopContent.ORONLY.equals(lopContent))) {
                lopContent = LopContent.MIXED;
            }
        }

    }
}