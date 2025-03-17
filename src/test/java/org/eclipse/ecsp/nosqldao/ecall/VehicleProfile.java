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

package org.eclipse.ecsp.nosqldao.ecall;

import dev.morphia.annotations.Entity;
import org.eclipse.ecsp.domain.Version;
import org.eclipse.ecsp.entities.IgniteEntity;

import java.util.Map;

/**
 * VehicleProfile entity class.
 */
@Entity
public class VehicleProfile implements IgniteEntity {

    private Map<String, ? extends Ecu> ecus;

    public VehicleProfile() {
    }

    public VehicleProfile(Map<String, ? extends Ecu> ecus) {
        this.ecus = ecus;
    }

    public Map<String, ? extends Ecu> getEcus() {
        return ecus;
    }

    public void setEcus(Map<String, ? extends Ecu> ecus) {
        this.ecus = ecus;
    }

    @Override
    public Version getSchemaVersion() {
        return null;
    }

    @Override
    public void setSchemaVersion(Version version) {
        // no schema version required for test class
    }
}

