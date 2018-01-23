package de.cookma.recipeManagement.infrastructure.config

import com.mongodb.MongoClient
import org.axonframework.mongo.DefaultMongoTemplate
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.UnknownHostException


@Configuration
class AxomConfiguration {

//    @Bean
//    fun axonJsonSerializer(): Serializer {
//        val objectMapper = ObjectMapper()
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
//        return JacksonSerializer(objectMapper)
//    }
//
//    @Bean
//    fun  eventStorageEngine(): MongoEventStorageEngine {
//        return  MongoEventStorageEngine(
//                axonJsonSerializer(),null, DefaultMongoTemplate(mongoClient()),  DocumentPerEventStorageStrategy())
//    }


    @Bean
    fun eventStorageEngine(): MongoEventStorageEngine {
        return MongoEventStorageEngine(DefaultMongoTemplate(mongoClient()))
    }

    @Bean
    @Throws(UnknownHostException::class)
    fun mongoClient(): MongoClient {
        return MongoClient("127.0.0.1", 27017)
    }

//    @Bean(name = arrayOf("axonMongoTemplate"))
//    fun axonMongoTemplate(): MongoTemplate {
//        val mongoFactory = MongoFactory()
//        mongoFactory.setMongoAddresses(Arrays.asList(ServerAddress(mongoUrl)))
//        val mongoClient = mongoFactory.createMongo()
//        return DefaultMongoTemplate(mongoClient, mongoDbName, eventsCollectionName, snapshotCollectionName)
//    }
//    @Bean
//    fun eventStorageEngine(): EventStorageEngine {
//        return InMemoryEventStorageEngine()
//    }
//
//    @Bean
//    fun eventStore(): EventStore {
//        return EmbeddedEventStore(eventStorageEngine())
//    }

//    @Bean
//    fun recipeRepository(): Repository<Recipe> {
//        return EventSourcingRepository(Recipe::class.java, eventStore())
//    }

}
