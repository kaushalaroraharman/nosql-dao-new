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
import org.bson.codecs.IntegerCodec;
import org.bson.codecs.StringCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.eclipse.ecsp.nosqldao.ecall.TestEntity;

/**
 * TestEntityCodec class.
 */
public class TestEntityCodec implements Codec<TestEntity> {

    private static final IgniteLogger LOGGER = IgniteLoggerFactory.getLogger(TestEntityCodec.class);
    private CodecRegistry registry = null;
    private Codec<Integer> idCodec;
    private Codec<String> nameCodec;
    private TestEntityCodec2 entity2Codec = null;

    public TestEntityCodec() {
    }

    /**
     * init method.
     */
    public void init() {
        this.registry = CodecRegistries.fromCodecs(new IntegerCodec(), new StringCodec(), new TestEntityCodec2());
        this.idCodec = this.registry.get(Integer.class);
        this.nameCodec = this.registry.get(String.class);
        entity2Codec = new TestEntityCodec2(registry);
    }

    @Override
    public void encode(BsonWriter writer, TestEntity value, EncoderContext encoderContext) {
        init();
        LOGGER.debug("Encoding value {} ...", value.toString());
        writer.writeStartDocument();
        writer.writeName("id");
        idCodec.encode(writer, value.getId(), encoderContext);
        writer.writeName("name");
        nameCodec.encode(writer, value.getName(), encoderContext);
        writer.writeName("entity2");
        entity2Codec.encode(writer, value.getEntity(), encoderContext);
        writer.writeEndDocument();
    }

    @Override
    public Class<TestEntity> getEncoderClass() {
        return TestEntity.class;
    }

    @Override
    public TestEntity decode(BsonReader reader, DecoderContext decoderContext) {
        LOGGER.debug("Decoding TestEntity...");
        TestEntity entity = new TestEntity();
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            if (fieldName.equals("id")) {
                entity.setId(idCodec.decode(reader, decoderContext));
            } else if (fieldName.equals("name")) {
                entity.setName(nameCodec.decode(reader, decoderContext));
            } else if (fieldName.equals("entity2")) {
                entity.setEntity(entity2Codec.decode(reader, decoderContext));
            } else if (fieldName.equals("_id")) {
                reader.readObjectId();
            }
        }
        reader.readEndDocument();
        return entity;
    }
}
