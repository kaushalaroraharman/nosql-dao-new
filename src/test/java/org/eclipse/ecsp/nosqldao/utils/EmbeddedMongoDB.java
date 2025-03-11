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

import com.harman.ignite.utils.logger.IgniteLogger;
import com.harman.ignite.utils.logger.IgniteLoggerFactory;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.eclipse.ecsp.nosqldao.spring.config.AbstractIgniteDAOMongoConfig;
import org.junit.rules.ExternalResource;

import java.util.Map;

/**
 * EmbeddedMongoDB class.
 */
public class EmbeddedMongoDB extends ExternalResource {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(EmbeddedMongoDB.class);
    private MongodStarter mongodStarter = MongodStarter.getDefaultInstance();
    private MongodExecutable mongodExecutable;
    private MongodProcess mongodProcess;

    private int port = 0;

    @Override
    protected void before() throws Throwable {
        port = Network.getFreeServerPort();
        MongodConfig mongodConfig = MongodConfig.builder().version(Version.Main.V4_4)
                .net(new Net("localhost", port, Network.localhostIsIPv6()))
                .build();
        mongodExecutable = mongodStarter.prepare(mongodConfig);
        mongodProcess = mongodExecutable.start();
        LOGGER.info("Embedded mongo DB started on port {} ", port);
        AbstractIgniteDAOMongoConfig.overridingPort = port;

        LOGGER.info("MongoClient connecting for pre-work DB configuration...");
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:" + port)) {
            final MongoDatabase adminDatabase = mongoClient.getDatabase("admin");
            Map<String, Object> commandArguments = new BasicDBObject();
            commandArguments.put("createUser", "admin");
            commandArguments.put("pwd", "password");
            String[] roles = { "readWrite" };
            commandArguments.put("roles", roles);
            BasicDBObject command = new BasicDBObject(commandArguments);
            adminDatabase.runCommand(command);
        }
    }

    @Override
    protected void after() {
        if (null != mongodProcess) {
            mongodProcess.stop();
        }
        if (null != mongodExecutable) {
            mongodExecutable.stop();
        }
    }

    /** Kill the embedded mongo db process. */
    public void kill() {
        if (null != mongodProcess) {
            mongodProcess.stop();
        }
        if (null != mongodExecutable) {
            mongodExecutable.stop();
        }
    }
}