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

/**
 * Interface to implement with every single entity to make them
 * easily identifiable with a unique key.
 *
 * @author Mosonyi István
 *         mosonyi15p@yahoo.com
 *         +36 20 275 1263
 * @date ${date}
 *
 * @param <K> Id type of entity.
 *
 */
public interface Identifiable<K> {

    /**
     * Retrieve the unique identifier of the entity.
     *
     * @return Id of entity.
     */
    K getId();

    /**
     * Set the id of the entity.
     *
     * @param id Unique identifier to set.
     */
    void setId(K id);

}
