package ${package}.repository.mongo;

import ${package}.model.mongo.${modelClass};
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ${modelClass}Repository extends MongoRepository<${modelClass}, String> {

}
