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

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;
import org.eclipse.ecsp.nosqldao.utils.NumericConstants;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * Test class for IgniteDAOMongoAdminClient.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IgniteDAOMongoConfigWithProps.class, IgniteDAOMongoAdminClient.class })
@TestPropertySource("/ignite-dao-admin-client.properties")
public class IgniteDAOAdminClientTest {

    private static MongodStarter mongodInstance = MongodStarter.getDefaultInstance();
    private static MongodExecutable mongodExecutor;
    private static MongoClient mongoClient;
    @Autowired
    IgniteDAOMongoAdminClient igniteDaoMongoClient;

    /**
     * Create mongo server.
     *
     * @throws Exception the exception
     */
    @BeforeClass
    public static void createMongoServer() throws Exception {
        MongodConfig mongodConfig = MongodConfig.builder().version(Version.Main.V4_4)
                .net(new Net("localhost", NumericConstants.MONGO_HOST, Network.localhostIsIPv6()))
                .build();
        mongodExecutor = mongodInstance.prepare(mongodConfig);
        mongodExecutor.start();
        createMongoAdminUser("admin50", "password0");
    }

    private static void createMongoAdminUser(String user, String password) {
        try (MongoClient mongoClient = MongoClients.create("mongodb://localhost:" + NumericConstants.MONGO_HOST)) {
            Map<String, Object> cmdArgs = new BasicDBObject();
            cmdArgs.put("createUser", user);
            cmdArgs.put("pwd", password);
            String[] roles = { "readWrite" };
            cmdArgs.put("roles", roles);
            BasicDBObject cmd = new BasicDBObject(cmdArgs);
            MongoDatabase adminDatabase = mongoClient.getDatabase("admin");
            adminDatabase.runCommand(cmd);
        }
    }

    @Test
    public void testMongoAdminClient() {
        mongoClient = igniteDaoMongoClient.getAdminClient();
        MongoDatabase adminDatabase = mongoClient.getDatabase("admin");
        assertNotNull(adminDatabase.listCollections().first());
    }

    @After
    public void tearUpMongoServer() {
        mongoClient.close();
        mongodExecutor.stop();
    }
}