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
import dev.morphia.annotations.Id;

import java.util.Map;

/**
 * CustomVehicleProfile entity class.
 */
@Entity("customVehicleProfile")
public class CustomVehicleProfile extends VehicleProfile {
    @Id
    private String customId;

    public CustomVehicleProfile(String customId) {
        this.customId = customId;
    }

    public CustomVehicleProfile() {

    }

    public CustomVehicleProfile(Map<String, ? extends Ecu> ecus, String customId) {
        super(ecus);
        this.customId = customId;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }
}
