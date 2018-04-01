package io.cookma.recipeManagement.infrastructure.config.upcast

import org.axonframework.serialization.SerializedType
import org.axonframework.serialization.SimpleSerializedType
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster
import org.dom4j.Document
import org.springframework.context.annotation.Bean


class RecipeCreateOrUpdatedEventUpcaster  : SingleEventUpcaster() {

    override fun canUpcast(ir: IntermediateEventRepresentation): Boolean =
            recipeUpdated("1.0") == ir.type || recipeCreated("1.0") == ir.type


    override fun doUpcast(ir: IntermediateEventRepresentation): IntermediateEventRepresentation = when (recipeUpdated("1.0") == ir.type) {
        true -> upcastPayload(ir, recipeUpdated("2.0"))
        false -> upcastPayload(ir, recipeCreated("2.0"))
    }

    private fun upcastPayload(ir: IntermediateEventRepresentation, serialzedType: SerializedType): IntermediateEventRepresentation =
            ir.upcastPayload(
                    serialzedType,
                    Document::class.java,
                    { document: Document ->
                        document.rootElement.addElement("testField").text = ""
                        document
                    }
            )

    private fun recipeUpdated(revision: String?): SerializedType = SimpleSerializedType("io.cookma.recipeManagement.domain.model.RecipeUpdatedEvent", revision)
    private fun recipeCreated(revision: String?): SerializedType = SimpleSerializedType("io.cookma.recipeManagement.domain.model.RecipeCreatedEvent", revision)
}



