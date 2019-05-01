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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * @author Mosonyi István
 *         mosonyi15p@yahoo.com
 *         +36 20 275 1263
 * @date ${date}
 *
 */
public class AbstractRepositoryTest {
    
    @Test
    public void testCreate() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity = new SimpleIdentifiable("SimpleIdentifiable");

        repository.create(entity);
        assertEquals(1, repository.getRepository().size());
        assertEquals(new Integer(1), entity.getId());
    }
    
    @Test
    public void testRead() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity = new SimpleIdentifiable("SimpleIdentifiable");
        repository.create(entity);
        
        SimpleIdentifiable readEntity = repository.read(1);
        assertNotNull(readEntity);
        assertEquals(new Integer(1), readEntity.getId());
        assertEquals("SimpleIdentifiable", readEntity.getName());
    }
    
    @Test
    public void testUpdate() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity = new SimpleIdentifiable("SimpleIdentifiable");
        
        repository.update(entity);
        assertEquals(1, repository.getRepository().size());
        assertEquals(new Integer(1), entity.getId());
        
        entity.setName("ModifiedSimpleIdentifiable");
        
        repository.update(entity);
        assertEquals(1, repository.getRepository().size());
        assertEquals(new Integer(1), entity.getId());
        assertEquals("ModifiedSimpleIdentifiable", entity.getName());
    }
    
    @Test
    public void testDelete() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity = new SimpleIdentifiable("SimpleIdentifiable");
        repository.create(entity);
        
        int repositorySize = repository.getRepository().size();
        repository.delete(entity);
        assertEquals(repositorySize - 1, repository.getRepository().size());
        
        repositorySize = repository.getRepository().size();
        repository.delete(entity);
        assertEquals(repositorySize, repository.getRepository().size());
        
        repositorySize = repository.getRepository().size();
        repository.delete(new SimpleIdentifiable("SimpleIdentifiable"));
        assertEquals(repositorySize, repository.getRepository().size());
    }
    
    @Test
    public void testRefresh() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity = new SimpleIdentifiable("SimpleIdentifiable");
        repository.create(entity);
        int id = entity.getId();
        entity = new SimpleIdentifiable("SimpleIdentifiable");
        entity.setId(id);
        entity.setName("ModifiedIdentifiable");
        
        entity = repository.refresh(entity);
        assertEquals("SimpleIdentifiable", entity.getName());
        
        entity = new SimpleIdentifiable("SimpleIdentifiable");
        entity.setId(repository.getNextKey());
        entity.setName("ModifiedIdentifiable");
        entity = repository.refresh(entity);
        assertEquals("ModifiedIdentifiable", entity.getName());
        
        entity = new SimpleIdentifiable("SimpleIdentifiable");
        entity.setName("ModifiedIdentifiable");
        entity = repository.refresh(entity);
        assertEquals("ModifiedIdentifiable", entity.getName());
    }
    
    @Test
    public void testFindAll() {
        RepositoryImpl repository = new RepositoryImpl();
        SimpleIdentifiable entity1 = new SimpleIdentifiable("SimpleIdentifiable1");
        SimpleIdentifiable entity2 = new SimpleIdentifiable("SimpleIdentifiable2");
        repository.create(entity1);
        repository.create(entity2);
        
        List<SimpleIdentifiable> foundEntities = repository.findAll();
        assertEquals(repository.getRepository().size(), foundEntities.size());
        assertNotNull(foundEntities.stream().filter(entity -> entity.getName().equals("SimpleIdentifiable1")).findFirst());
        assertNotNull(foundEntities.stream().filter(entity -> entity.getName().equals("SimpleIdentifiable2")).findFirst());
    }
    
    private static final class SimpleIdentifiable implements Identifiable<Integer> {

        private Integer id;
        
        private String name;
        
        public SimpleIdentifiable(String name) {
            this.name = name;
        }
        
        @Override
        public Integer getId() {
            return id;
        }

        @Override
        public void setId(Integer id) {
            this.id = id;
        }
        
        public String getName() {
            return name;
        }
        
        public void setName(String name) {
            this.name = name;
        }
        
    }
    
    private static final class RepositoryImpl extends AbstractRepository<SimpleIdentifiable, Integer> {

        private final Map<Integer, SimpleIdentifiable> repository = new HashMap<>();
        
        private Integer sequence = 0;
        
        @Override
        protected Map<Integer, SimpleIdentifiable> getRepository() {
            return repository;
        }

        @Override
        protected synchronized Integer getNextKey() {
            return ++sequence;
        }
        
    }

}
