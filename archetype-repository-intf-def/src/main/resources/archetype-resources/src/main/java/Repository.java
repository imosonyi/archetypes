/*
 * This file is part of ${name}.
 *
 * ${name} is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ${name} is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ${name}.  If not, see <https://www.gnu.org/licenses/>.
 */
package ${package};

import java.util.List;

/**
 * Interface to implement for basic CRUD operations in the repository.
 *
 * @author Mosonyi István
 *         mosonyi15p@yahoo.com
 *         +36 20 275 1263
 * @date ${date}
 * @param <T> Entity type.
 * @param <K> Id type of entity.
 */
public interface Repository<T, K> {

    /**
     * Store entity in the repository.
     *
     * @param entity Entity instance.
     * @return Entity itself.
     */
    T create(T entity);

    /**
     * Find entity in the repository by id.
     *
     * @param key Entity primary key.
     * @return Found entity or null.
     */
    T read(K key);

    /**
     * Update the repository with the modifications stored in the entity object.
     *
     * @param entity Entity instance.
     * @return Entity itself.
     */
    T update(T entity);

    /**
     * Delete the entity from the repository.
     *
     * @param entity Entity instance.
     * @return Entity itself.
     */
    T delete(T entity);

    /**
     * Re-read the properties of the entity into the entity instance.
     *
     * @param entity Entity instance.
     * @return Entity itself.
     */
    T refresh(T entity);

    /**
     * Find all entity instances in the repository.
     *
     * @return List of entities.
     */
    List<T> findAll();

}
