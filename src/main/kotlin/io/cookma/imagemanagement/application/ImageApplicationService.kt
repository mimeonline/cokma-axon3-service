package io.cookma.imagemanagement.application

import io.cookma.imagemanagement.infrastructure.store.FileDto
import io.cookma.imagemanagement.infrastructure.store.ImageStore
import io.cookma.imagemanagement.utility.DataUri
import io.cookma.recipeManagement.application.RecipeDto
import io.cookma.recipeManagement.domain.model.CmdImage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ImageApplicationService {

    @Autowired
    lateinit var imageStore: ImageStore

    fun storeImage(recipe: RecipeDto): CmdImage {
        val imageId = UUID.randomUUID().toString()
        val extension = DataUri(recipe.image).extension()
        val image = CmdImage(imageId, extension)
        imageStore.store(imageId, recipe.image)
        return image
    }

    fun storeImage(file: FileDto): String {
        val imageId = UUID.randomUUID().toString()
        imageStore.store(imageId, file.file)
        return imageId
    }
}