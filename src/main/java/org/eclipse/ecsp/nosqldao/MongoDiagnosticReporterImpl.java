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

import com.harman.ignite.diagnostic.DiagnosticData;
import com.harman.ignite.diagnostic.DiagnosticReporter;
import org.eclipse.ecsp.nosqldao.utils.PropertyNames;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class provides methods for Mongo diagnostic reporter.
 */
@Component("mongoReporter")
public class MongoDiagnosticReporterImpl implements DiagnosticReporter {

    /**
     * The constant DIAGNOSTIC_MONGO_METRIC_NAME.
     */
    private static final String DIAGNOSTIC_MONGO_METRIC_NAME = "DIAGNOSTIC_MONGO_METRIC";

    /**
     * The constant DIAGNOSTIC_MONGO_REPORTER_NAME.
     */
    private static final String DIAGNOSTIC_MONGO_REPORTER_NAME = "DIAGNOSTIC_MONGO_REPORTER";

    /**
     * The Mongo diagnostic data.
     */
    DiagnosticData mongoDiagnosticData = new DiagnosticData();

    /**
     * Boolean flag for diagnostic mongo reporter enabled.
     */
    @Value("${" + PropertyNames.MONGO_DIAGNOSTIC_REPORTER_ENABLED + ": false }")
    private boolean diagnosticMongoReporterEnabled;

    /**
     * Gets diagnostic data.
     *
     * @return the diagnostic data
     */
    @Override
    public DiagnosticData getDiagnosticData() {
        return mongoDiagnosticData;
    }

    /**
     * Gets diagnostic reporter name.
     *
     * @return the diagnostic reporter name
     */
    @Override
    public String getDiagnosticReporterName() {
        return DIAGNOSTIC_MONGO_REPORTER_NAME;
    }

    /**
     * Gets diagnostic metric name.
     *
     * @return the diagnostic metric name
     */
    @Override
    public String getDiagnosticMetricName() {
        return DIAGNOSTIC_MONGO_METRIC_NAME;
    }

    /**
     * Is diagnostic reporter enabled boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isDiagnosticReporterEnabled() {
        return diagnosticMongoReporterEnabled;
    }

    /**
     * Put diagnosticData to mongoDiagnosticData.
     *
     * @param diagnosticData the diagnostic data
     */
    public void put(DiagnosticData diagnosticData) {
        mongoDiagnosticData.putAll(diagnosticData);
    }
}
