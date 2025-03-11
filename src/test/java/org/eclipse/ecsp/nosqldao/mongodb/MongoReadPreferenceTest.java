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

package org.eclipse.ecsp.nosqldao.mongodb;

import com.mongodb.MongoClientOptions;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Testing MongoReadPreference.
 */
@RunWith(JUnit4.class)
public class MongoReadPreferenceTest {

    private static List<String> preferences = new ArrayList<>();

    /**
     * Get all preferences.
     */
    @BeforeClass
    public static void getPreferences() {
        for (MongoReadPreference preference : MongoReadPreference.values()) {
            preferences.add(preference.toString());
        }
    }

    @Test
    public void testMongoReadPreferences() {

        MongoClientOptions mongoClientOptions;
        for (String preference : preferences) {
            mongoClientOptions = MongoClientOptions.builder()
                    .readPreference(MongoReadPreference.getEnum(preference).getReadPreference())
                    .build();

            assertTrue(StringUtils.equalsAnyIgnoreCase(preference, mongoClientOptions.getReadPreference().getName()));
        }
    }

}
