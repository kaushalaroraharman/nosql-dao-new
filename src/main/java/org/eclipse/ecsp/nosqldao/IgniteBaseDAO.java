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

package org.eclipse.ecsp.nosqldao;

import com.harman.ignite.entities.IgniteEntity;
import reactor.core.publisher.Flux;

import java.util.List;

/**
 *  This interface is used for exposing methods for IgniteBaseDao repository layer.
 *
 *  @param <K> the type of the primary key
 *  @param <E> the type of the entity
 */
public interface IgniteBaseDAO<K, E extends IgniteEntity> {

    /**
     * Saves the given entity.
     *
     * @param entity the entity to save
     * @return the saved entity
     */
    public E save(E entity);

    /**
     * Saves all given entities.
     *
     * @param entities the entities to save
     * @return the list of saved entities
     */
    public List<E> saveAll(@SuppressWarnings("unchecked") E... entities);

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity
     * @return the found entity
     */
    public E findById(K id);

    /**
     * Finds entities by their IDs.
     *
     * @param ids the IDs of the entities
     * @return the list of found entities
     */
    public List<E> findByIds(@SuppressWarnings("unchecked") K... ids);

    /**
     * Finds all entities.
     *
     * @return the list of all entities
     */
    public List<E> findAll();

    /**
     * Finds entities matching the given query.
     *
     * @param c the query to match
     * @return the list of matching entities
     */
    public List<E> find(IgniteQuery c);

    /**
     * Finds entities with paging information.
     *
     * @param c the query to match
     * @return the paging information response containing the list of matching entities
     */
    public IgnitePagingInfoResponse<E> findWithPagingInfo(IgniteQuery c);

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity
     * @return true if the entity was deleted, false otherwise
     */
    public boolean deleteById(K id);

    /**
     * Deletes entities by their IDs.
     *
     * @param ids the IDs of the entities
     * @return the number of entities deleted
     */
    public int deleteByIds(@SuppressWarnings("unchecked") K... ids);

    /**
     * Deletes entities matching the given query.
     *
     * @param igniteQuery the query to match
     * @return the number of entities deleted
     */
    public int deleteByQuery(IgniteQuery igniteQuery);

    /**
     * Deletes all entities.
     *
     * @return true if all entities were deleted, false otherwise
     */
    public boolean deleteAll();

    /**
     * Updates the given entity.
     *
     * @param entity the entity to update
     * @return true if the entity was updated, false otherwise
     */
    public boolean update(E entity);

    /**
     * Updates an entity by its ID.
     *
     * @param id the ID of the entity
     * @param u the updates to apply
     * @return true if the entity was updated, false otherwise
     */
    public boolean update(K id, Updates u);

    /**
     * Updating collection through query. this will be helpful in scenario
     * where one would like to filter and then update.
     *
     * @param c : IgniteQuery
     * @param u : Updates
     * @return boolean
     */
    public boolean update(IgniteQuery c, Updates u);

    /**
     * Updates all given entities.
     *
     * @param entities the entities to update
     * @return the list of update statuses
     */
    public boolean[] updateAll(@SuppressWarnings("unchecked") E... entities);

    /**
     * If update condition is not satisfied then it will insert a new record
     * in collection.
     *
     * @param igniteQuery : IgniteQuery
     * @param entity : entity
     * @return boolean
     */
    public boolean upsert(IgniteQuery igniteQuery, E entity);

    /**
     * Remove all objects in collection through query. this will be helpful in scenario where
     *
     * @param c : IgniteQuery
     * @param u : Updates
     * @return boolean
     */
    public boolean removeAll(IgniteQuery c, Updates u);

    /**
     * Deletes the given entity.
     * <p>
     * This method throws UnSupportedOperationException when getOverridingCollectionName() returns a value.
     * AdvancedDataStore does not support delete with dynamic collection name.
     * </p>
     *
     * @param entity : Entity
     * @return delete status
     */
    public boolean delete(E entity);

    /**
     * Find distinct values for given field.
     * <p>
     * This method throws UnSupportedOperationException when getOverridingCollectionName()
     * returns a value. AdvancedDataStore does not
     * support distinct with dynamic collection name.
     * </p>
     *
     * @param igniteQuery
     *         IgniteQuery
     * @param field
     *         field name for which to find distinct values
     * @return List of distinct values
     */
    List<K> distinct(IgniteQuery igniteQuery, String field);

    /**
     * This method can be used by services to override collection name provided with @Entity annotation
     * or when collection name is not provided.
     * The mongo dao implementation will give preference to collection name returned from this method over annotation.
     *
     * @return CollectionName
     */
    default String getOverridingCollectionName() {
        return null;
    }

    /**
     * Return the total count for given query.
     *
     * @param igniteQuery : IgniteQuery
     * @return count
     */
    public long countByQuery(IgniteQuery igniteQuery);

    /**
     * Streams entities matching the given query.
     *
     * @param igniteQuery the query to match
     * @return a Flux of matching entities
     */
    Flux<E> streamFind(IgniteQuery igniteQuery);

    /**
     * Returns the total count of all entities.
     *
     * @return the total count of all entities
     */
    long countAll();

    /**
     * Streams all entities.
     *
     * @return a Flux of all entities
     */
    Flux<E> streamFindAll();

    /**
     * This method can be used by services to check if the collection exists in the database or not.
     *
     * @param collectionName : CollectionName
     * @return true if the collection exists, false otherwise
     */
    public boolean collectionExists(String collectionName);

    /**
     * Gets and updates the given entity.
     *
     * @param entity the entity to get and update
     * @return true if the entity was updated, false otherwise
     */
    boolean getAndUpdate(E entity);
}
