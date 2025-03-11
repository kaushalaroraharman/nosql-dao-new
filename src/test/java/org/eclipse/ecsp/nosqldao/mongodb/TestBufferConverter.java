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

import org.bson.BsonBinary;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.ByteArrayCodec;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

/**
 * Morphia {@link ByteArrayCodec} for {@link BytesBuffer}.
 *
 * @author ashekar
 */
public class TestBufferConverter implements Codec<TestBuffer> {

    @Override
    public void encode(BsonWriter bsonWriter, TestBuffer bytesBuffer, EncoderContext encoderContext) {
        if (bytesBuffer == null) {
            throw new IllegalArgumentException("Given value is not an instance of com.google.protobuf.BytesBuffer");
        }
        bsonWriter.writeBinaryData(new BsonBinary(bytesBuffer.getBytes()));
    }

    @Override
    public TestBuffer decode(BsonReader bsonReader, DecoderContext decoderContext) {
        if (bsonReader.readBinaryData().getData() == null) {
            return null;
        }

        if (bsonReader.readBinaryData().getData() != null) {
            TestBuffer buffer = new TestBuffer(bsonReader.readBinaryData().getData());
            return buffer;
        }
        throw new IllegalArgumentException("Could not decode the value to com.google.protobuf.BytesBuffer");
    }

    @Override
    public Class<TestBuffer> getEncoderClass() {
        return TestBuffer.class;
    }
}
