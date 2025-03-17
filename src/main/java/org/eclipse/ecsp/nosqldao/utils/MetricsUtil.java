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

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import org.eclipse.ecsp.utils.metrics.GenericIgniteHistogram;

import java.util.function.Supplier;

/**
 * This class Provides utility methods for Metrics.

 * @author ssasidharan
 *
 */
public class MetricsUtil {

    /**
     * Private constructor to prevent instantiation.
     */
    private MetricsUtil() {
    }

    /**
     * Observes and records metrics if metrics are enabled.
     *
     * @param <T> The type of the result produced by the instrumented function.
     * @param metricsInitialized Indicates if metrics are enabled.
     * @param histo The histogram to record the metrics.
     * @param requestCounter The counter to increment for each request.
     * @param requestGauge The gauge to increment for each request.
     * @param instrumentedFunction The function to execute and observe.
     * @param labelsFunction The function to generate labels for the metrics.
     * @return The result of the instrumented function.
     */
    public static <T> T observeIfEnabled(boolean metricsInitialized, GenericIgniteHistogram histo,
            Counter requestCounter, Gauge requestGauge, Supplier<T> instrumentedFunction,
            Supplier<String[]> labelsFunction) {
        if (metricsInitialized) {
            String[] labels = labelsFunction.get();
            requestCounter.labels(labels).inc();
            requestGauge.labels(labels).inc();
            return histo.observe(instrumentedFunction, labels);
        } else {
            return instrumentedFunction.get();
        }
    }
}
