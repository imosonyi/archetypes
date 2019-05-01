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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ${packageOfRepositoryIntfDef}.Repository;

/**
 * This {@link AbstractRepository} provides a default in-memory implementation
 * of the {@link Repository} interface.
 *
 * @author Mosonyi István
 *         mosonyi15p@yahoo.com
 *         +36 20 275 1263
 * @date ${date}
 *
 * @param <T> Entity type that must implement the {@link Identifiable} interface.
 * @param <K> Id type of entity.
 *
 */
public abstract class AbstractRepository<T extends Identifiable<K>, K> implements Repository<T, K> {

    /**
     * Store the entities in an in-memory {@link Map} and return it to
     * use in the default implementation in {@link AbstractRepository}.
     *
     * @return {@link Map} of the stored entities by their ids.
     */
    protected abstract Map<K, T> getRepository();

    /**
     * Get the next unique entity id in the sequence.
     *
     * @return Newly generated unique id.
     */
    protected abstract K getNextKey();

    @Override
    public synchronized T create(T entity) {
        K key = getNextKey();
        entity.setId(key);
        getRepository().put(key, entity);
        return entity;
    }

    @Override
    public T read(K key) {
        return getRepository().get(key);
    }

    @Override
    public synchronized T update(T entity) {
        if (entity.getId() == null) {
            K key = getNextKey();
            entity.setId(key);
        }
        getRepository().put(entity.getId(), entity);
        return entity;
    }

    @Override
    public synchronized T delete(T entity) {
        if (entity.getId() == null || !getRepository().containsKey(entity.getId())) {
            return entity;
        }
        return getRepository().remove(entity.getId());
    }

    @Override
    public T refresh(T entity) {
        if (entity.getId() == null || !getRepository().containsKey(entity.getId())) {
            return entity;
        }
        return getRepository().get(entity.getId());
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(getRepository().values());
    }

}
