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

import com.mongodb.MongoNamespace;
import com.mongodb.client.MongoCollection;
import dev.morphia.AdvancedDatastore;
import dev.morphia.mapping.Mapper;
import dev.morphia.query.Query;
import dev.morphia.query.UpdateOperations;
import dev.morphia.query.internal.MorphiaCursor;
import org.eclipse.ecsp.nosqldao.IgniteCriteria;
import org.eclipse.ecsp.nosqldao.IgniteCriteriaGroup;
import org.eclipse.ecsp.nosqldao.IgniteQuery;
import org.eclipse.ecsp.nosqldao.Operator;
import org.eclipse.ecsp.nosqldao.QueryTranslator;
import org.eclipse.ecsp.nosqldao.UpdatesTranslator;
import org.eclipse.ecsp.nosqldao.ecall.ECallDAOMongoImpl;
import org.eclipse.ecsp.nosqldao.ecall.ECallEvent;
import org.eclipse.ecsp.nosqldao.ecall.MockTestDAOMongoImpl;
import org.eclipse.ecsp.nosqldao.ecall.MockTestEvent;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Optional;

import static org.mockito.Matchers.eq;

/**
 * Test class for IgniteBaseDAOMongoImpl.
 */
public class IgniteBaseDAOMongoImplMockTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @InjectMocks
    private MockTestDAOMongoImpl testDAOMongoImpl;

    @InjectMocks
    private ECallDAOMongoImpl testEcallDAOMongoImpl;

    @Mock
    private AdvancedDatastore ds;

    @Mock
    private Mapper mapper;

    @Mock
    private MongoCollection mongoCollection;

    @Mock
    private MongoNamespace namespace;

    @Mock
    private QueryTranslator queryTranslator;

    @Mock
    private Query query;

    @Mock
    private UpdatesTranslator<UpdateOperations<MockTestEvent>> updatesTranslator;

    private String collection;

    /**
     * Setup method.
     */
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        collection = testDAOMongoImpl.getOverridingCollectionName();
        Mockito.when(ds.getMapper()).thenReturn(mapper);
        Mockito.when(mapper.getCollection(Mockito.any())).thenReturn(mongoCollection);
        Mockito.when(mongoCollection.getNamespace()).thenReturn(namespace);
        Mockito.when(namespace.getCollectionName()).thenReturn(collection);
    }

    @Test
    public void testFindAll() {
        Query<MockTestEvent> query = (Query<MockTestEvent>) Mockito.mock(Query.class);
        Mockito.when(ds.find(collection, MockTestEvent.class)).thenReturn(query);
        MorphiaCursor<MockTestEvent> morphiaCursor = (MorphiaCursor<MockTestEvent>) Mockito.mock(MorphiaCursor.class);
        Mockito.when(query.iterator()).thenReturn(morphiaCursor);
        testDAOMongoImpl.findAll();
        Mockito.verify(ds, Mockito.times(1)).find(collection, MockTestEvent.class);
    }

    @Test
    public void testSave() {
        ECallEvent event = new ECallEvent();
        testEcallDAOMongoImpl.save(event);
        Mockito.verify(ds, Mockito.times(1)).save(event);
    }

    @Test
    public void testSaveAll() {
        ECallEvent event = new ECallEvent();
        ECallEvent event2 = new ECallEvent();
        testEcallDAOMongoImpl.saveAll(event, event2);
        Mockito.verify(ds, Mockito.times(1)).save(event);
        Mockito.verify(ds, Mockito.times(1)).save(event2);
    }

    @Test
    public void testCountByQuery() {
        Query<MockTestEvent> query = Mockito.mock(Query.class);
        Mockito.when(query.count())
                .thenReturn(1L);

        IgniteCriteria igniteCriteria = new IgniteCriteria("id", Operator.EQ, "id1");
        IgniteCriteriaGroup igniteCriteriaGroup = new IgniteCriteriaGroup(igniteCriteria);
        IgniteQuery igniteQuery = new IgniteQuery(igniteCriteriaGroup);

        Mockito.when(queryTranslator.translate(eq(igniteQuery), eq(Optional.ofNullable(collection))))
                .thenReturn(query);

        long count = testDAOMongoImpl.countByQuery(igniteQuery);
        Assert.assertEquals(Long.valueOf(1), Long.valueOf(count));
        Mockito.verify(query, Mockito.times(1)).count();
    }

    @Test
    public void testUpdateE() {
        ECallEvent event = new ECallEvent();
        testEcallDAOMongoImpl.update(event);
        Mockito.verify(ds, Mockito.times(1)).save(event);
    }

    @Test
    public void testUpdateAll() {
        ECallEvent event = new ECallEvent();
        ECallEvent event2 = new ECallEvent();
        testEcallDAOMongoImpl.updateAll(event, event2);
        Mockito.verify(ds, Mockito.times(1)).save(event);
        Mockito.verify(ds, Mockito.times(1)).save(event2);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDelete() {
        MockTestEvent event = new MockTestEvent();
        testDAOMongoImpl.delete(event);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDistinctWhenCustomCollectionIsSet() {
        IgniteQuery iq = new IgniteQuery();
        testDAOMongoImpl.distinct(iq, "field");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testDistinct() {
        IgniteQuery igniteQuery = new IgniteQuery();
        testDAOMongoImpl.distinct(igniteQuery, "id");
    }

}
