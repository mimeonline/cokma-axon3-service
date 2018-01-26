package de.cookma.recipeManagement.infrastructure.store

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.springframework.util.FileSystemUtils
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

/**
 * TODO Image Verarbeitung
 * - Move image to resource dir in the test package
 * - Use tempDir instead of fix path in the Project root
 */
class RecipeImageStoreTest {

    lateinit var recipeImageStore: RecipeImageStore
    lateinit var image: String

    @Before
    fun setUp() {
//        recipeImageStore = RecipeImageStore()
//        image = createDataUri()
    }

    @After
    fun setDown() {
//        removeDataUri()
    }

    @Test
    fun store() {
        // given

        // when
//        recipeImageStore.store("testdir", image)
//
//        // then
//        assertTrue(Files.exists(Paths.get("image-dir/testdir/image.png")))
    }

    private fun removeDataUri() {
        var fileLocation = Paths.get("image-dir", "testdir")
        FileSystemUtils.deleteRecursively(fileLocation.toFile())
    }

    private fun createDataUri(): String {
        var path = Paths.get("image-dir/image.png")
        var bytes = Files.readAllBytes(path)
        var base64String = Base64.getEncoder().encodeToString(bytes)
        var image = "data:image/png;base64," + base64String
        return image
    }
}