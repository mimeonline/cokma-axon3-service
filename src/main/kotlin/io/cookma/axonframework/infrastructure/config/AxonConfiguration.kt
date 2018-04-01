package io.cookma.axonframework.infrastructure.config

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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
import org.axonframework.serialization.json.JacksonSerializer
import org.axonframework.serialization.upcasting.event.EventUpcaster
import org.axonframework.serialization.upcasting.event.EventUpcasterChain
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import java.net.UnknownHostException


@Configuration
class AxonConfiguration {

    @Value("\${mongodb.host.name}")
    private val mongodbHost: String? = null

    @Value("\${mongodb.host.port}")
    private val mongoDBPort: Int = 0

    @Autowired
    lateinit var axonXmlSerializer: Serializer

    @Bean
    @Qualifier("eventSerializer")
    fun axonJsonSerializer(): Serializer {
        return JacksonSerializer(jacksonObjectMapper())
    }

    @Autowired
    fun configureProcessors(eventHandlingConfiguration: EventHandlingConfiguration) {
        eventHandlingConfiguration.usingTrackingProcessors()
    }

    @Bean
    fun getMongoTokenStore(): TokenStore {
        return MongoTokenStore(axonMongoTemplate(), axonXmlSerializer)
    }


    @Bean
    fun eventStorageEngine(): MongoEventStorageEngine {
        return MongoEventStorageEngine(
                axonJsonSerializer(), upcasterChain(), axonMongoTemplate(), DocumentPerEventStorageStrategy())
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