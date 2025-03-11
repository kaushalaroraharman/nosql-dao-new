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

package org.eclipse.ecsp.nosqldao.spring.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.ConfigurationCondition;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * This class enables @Configuration class will be added based
 * on @Conditional(MongoPropsCondition.class) . This will be true if default
 * implementation is enabled.
 */
public class MongoPropsCondition implements ConfigurationCondition {

    /**
     * Check if the default datastore is enabled.
     * Property name is db.default.datastore.provider.enabled.
     * If the property is not set or set to true, then the default datastore is enabled.
     *
     * @param context  the context
     *                 {@link ConditionContext}
     * @param metadata the metadata
     *                 {@link AnnotatedTypeMetadata}
     *
     * @return boolean
     */
    @Override
    public boolean matches(ConditionContext context,
            AnnotatedTypeMetadata metadata) {
        String defaultDatastoreEnabled = context.getEnvironment()
                .getProperty("db.default.datastore.provider.enabled");
        return StringUtils.isBlank(defaultDatastoreEnabled)
                || (StringUtils.isNotEmpty(defaultDatastoreEnabled) && defaultDatastoreEnabled.equals("true"));
    }

    /**
     * PARSE_CONFIGURATION-fully exclude the configuration class.
     */
    @Override
    public ConfigurationPhase getConfigurationPhase() {
        return ConfigurationPhase.PARSE_CONFIGURATION;
    }
}
