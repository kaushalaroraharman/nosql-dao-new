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

import com.mongodb.ReadPreference;

/**
 * The enum Mongo read preference.
 */
public enum MongoReadPreference {

    /**
     * Primary read preference.
     */
    PRIMARY("primary") {
        @Override
        public ReadPreference getReadPreference() {
            return ReadPreference.primary();
        }
    },
    /**
     * Secondary read preference.
     */
    SECONDARY("secondary") {
        @Override
        public ReadPreference getReadPreference() {
            return ReadPreference.secondary();
        }
    },
    /**
     * Secondary preferred read preference.
     */
    SECONDARY_PREFERRED("secondaryPreferred") {
        @Override
        public ReadPreference getReadPreference() {
            return ReadPreference.secondaryPreferred();
        }
    },
    /**
     * Primary preferred read preference.
     */
    PRIMARY_PREFERRED("primaryPreferred") {
        @Override
        public ReadPreference getReadPreference() {
            return ReadPreference.primaryPreferred();
        }
    },
    /**
     * Nearest read preference.
     */
    NEAREST("nearest") {
        @Override
        public ReadPreference getReadPreference() {
            return ReadPreference.nearest();
        }
    };

    /**
     * The read preference string.
     */
    private String readPrefernce;

    /**
     * Instantiates a new Mongo read preference.
     *
     * @param readPreference the read preference string
     */
    private MongoReadPreference(String readPreference) {
        this.readPrefernce = readPreference;
    }

    /**
     * Gets enum.
     *
     * @param readPreferenceStr the read preference string
     * @return the enum
     */
    public static MongoReadPreference getEnum(String readPreferenceStr) {
        MongoReadPreference readPreference = null;
        switch (readPreferenceStr) {
            case "primary":
                readPreference = MongoReadPreference.PRIMARY;
                break;
            case "secondary":
                readPreference = MongoReadPreference.SECONDARY;
                break;
            case "secondaryPreferred":
                readPreference = MongoReadPreference.SECONDARY_PREFERRED;
                break;
            case "primaryPreferred":
                readPreference = MongoReadPreference.PRIMARY_PREFERRED;
                break;
            case "nearest":
                readPreference = MongoReadPreference.NEAREST;
                break;
            default:
                throw new InvalidReadPreferenceException("Invalid read prefernce");
        }
        return readPreference;
    }

    @Override
    public String toString() {
        return readPrefernce;
    }

    /**
     * Gets the read preference.
     *
     * @return the read preference
     */
    public abstract ReadPreference getReadPreference();
}
