package io.cookma.axonframework.infrastructure.config

import com.mongodb.MongoClient
import io.cookma.recipeManagement.infrastructure.config.upcast.RecipeCreateOrUpdatedEventUpcaster
import org.axonframework.config.EventHandlingConfiguration
import org.axonframework.eventhandling.tokenstore.TokenStore
import org.axonframework.mongo.DefaultMongoTemplate
import org.axonframework.mongo.MongoTemplate
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine
import org.axonframework.mongo.eventsourcing.eventstore.documentperevent.DocumentPerEventStorageStrategy
import org.axonframework.mongo.eventsourcing.tokenstore.MongoTokenStore
import org.axonframework.serialization.Serializer
import org.axonframework.serialization.upcasting.event.EventUpcaster
import org.axonframework.serialization.upcasting.event.EventUpcasterChain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.net.UnknownHostException


@Configuration
class AxonConfiguration {

    @Value("\${mongodb.host.name}")
    private val mongodbHost: String? = null

    @Value("\${mongodb.host.port}")
    private val mongoDBPort: Int = 0

    @Autowired
    lateinit var eventSerializer: Serializer

    //    @Bean
//    fun axonJsonSerializer(): Serializer {
//        val objectMapper = ObjectMapper()
//        objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
//        return JacksonSerializer(objectMapper)
//    }

    @Autowired
    fun configureProcessors(eventHandlingConfiguration: EventHandlingConfiguration) {
        eventHandlingConfiguration.usingTrackingProcessors()
    }

    @Bean
    fun getMongoTokenStore(): TokenStore {
        return MongoTokenStore(axonMongoTemplate(), eventSerializer)
    }


    @Bean
    fun eventStorageEngine(): MongoEventStorageEngine {
        return MongoEventStorageEngine(
                eventSerializer, upcasterChain(), axonMongoTemplate(), DocumentPerEventStorageStrategy())
    }

    @Bean
    fun upcasterChain(): EventUpcaster = EventUpcasterChain(RecipeCreateOrUpdatedEventUpcaster())

    @Bean
    fun axonMongoTemplate(): MongoTemplate {
        return DefaultMongoTemplate(mongoClient())
    }



    @Bean
    @Throws(UnknownHostException::class)
    fun mongoClient(): MongoClient {
        return MongoClient(mongodbHost, mongoDBPort)
    }
}