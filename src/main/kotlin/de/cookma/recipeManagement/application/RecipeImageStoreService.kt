package de.cookma.recipeManagement.application

import de.cookma.recipeManagement.presentation.RecipeController
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Service
import org.springframework.util.FileSystemUtils
import org.springframework.web.multipart.MultipartFile
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct


@Service
class RecipeImageStoreService {


    val imageLocation: Path = Paths.get("image-dir");

    fun store(file: MultipartFile) {
        try {
            Files.copy(file.inputStream, imageLocation.resolve(file.originalFilename))
        } catch (e: Exception) {
            throw RuntimeException("FAIL!")
        }
    }

    /**
     * data.image.substring(str.indexOf(",") + 1) ia also a solution to get the image
     */
    fun store(file: RecipeController.ImageFile) {
        init()
        try {
            FileOutputStream("image-dir/image.png").use({ imageOutFile ->
                // Converting a Base64 String into Image byte array
                val imageByteArray = Base64.getDecoder().decode(file.image.replace("data:image/png;base64,",""))
                imageOutFile.write(imageByteArray)
            })
        } catch (e: FileNotFoundException) {
            println("Image not found" + e)
        } catch (ioe: IOException) {
            println("Exception while reading the Image " + ioe)
        }

    }

    fun load(filename: String): Resource {
        try {
            val file = imageLocation.resolve(filename)
            val resource = UrlResource(file.toUri())
            return if (resource.exists() || resource.isReadable) {
                resource
            } else {
                throw RuntimeException("FAIL!")
            }
        } catch (e: MalformedURLException) {
            throw RuntimeException("FAIL!")
        }

    }

    fun deleteAll() {
        FileSystemUtils.deleteRecursively(imageLocation.toFile())
    }

    @PostConstruct
    fun init() {
        if (Files.notExists(imageLocation)) {
            try {
                Files.createDirectory(imageLocation)
            } catch (e: IOException) {
                throw RuntimeException("Could not initialize storage!")
            }
        }
    }
}