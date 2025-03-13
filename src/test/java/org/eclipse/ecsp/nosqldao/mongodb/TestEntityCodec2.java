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

import com.harman.ignite.utils.logger.IgniteLogger;
import com.harman.ignite.utils.logger.IgniteLoggerFactory;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.eclipse.ecsp.nosqldao.ecall.TestEntity2;

/**
 * Test class for EntityCodec2.
 */
public class TestEntityCodec2 implements Codec<TestEntity2> {
    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(TestEntityCodec2.class);
    private Codec<Integer> integerCodec = null;
    private Codec<String> stringCodec = null;

    public TestEntityCodec2() {
    }

    public TestEntityCodec2(CodecRegistry registry) {
        this.integerCodec = registry.get(Integer.class);
        this.stringCodec = registry.get(String.class);
    }

    @Override
    public void encode(BsonWriter writer, TestEntity2 value, EncoderContext encoderContext) {
        LOGGER.debug("Encoding TestEntity2 {} ...", value.toString());
        writer.writeStartDocument();
        writer.writeName("id");
        integerCodec.encode(writer, value.getId(), encoderContext);
        writer.writeName("name");
        stringCodec.encode(writer, value.getName(), encoderContext);
        writer.writeEndDocument();
    }

    @Override
    public Class<TestEntity2> getEncoderClass() {
        return TestEntity2.class;
    }

    @Override
    public TestEntity2 decode(BsonReader reader, DecoderContext decoderContext) {
        LOGGER.debug("Decoding TestEntity2...");
        TestEntity2 entity = new TestEntity2();
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            if (fieldName.equals("id")) {
                entity.setId(integerCodec.decode(reader, decoderContext));
            } else if (fieldName.equals("name")) {
                entity.setName(stringCodec.decode(reader, decoderContext));
            } else if (fieldName.equals("_id")) {
                reader.readObjectId();
            }
        }
        reader.readEndDocument();
        return entity;
    }
}
