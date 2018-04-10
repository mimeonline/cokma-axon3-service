package io.cookma.imagemanagement.infrastructure.store

import org.springframework.core.io.Resource
import org.springframework.core.io.UrlResource
import org.springframework.stereotype.Component
import org.springframework.util.FileSystemUtils
import java.io.IOException
import java.net.MalformedURLException
import java.nio.file.DirectoryNotEmptyException
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.*
import javax.annotation.PostConstruct


@Component
class ImageStore {


    val imageLocation: Path = Paths.get("image-dir");


    fun store(fileName: String, file: String) {
        init()
//        var fileLocation = imageLocation.resolve(fileDir)
//        Files.createDirectories(fileLocation)
        val imageByteArray = Base64.getDecoder().decode(file.substring(file.indexOf(",") + 1))
        var imageFile = imageLocation.resolve(fileName + "." + getExtension(file))

        Files.write(imageFile, imageByteArray)
    }

    private fun getExtension(file: String): String {
        var comma = file.indexOf(';')
        return file.substring("data:image/".length, comma)
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

    fun deleteFile(file: String) {
        var fileLocation = imageLocation.resolve(file)
        try {
            Files.delete(fileLocation)
        } catch (e: NoSuchFileException) {
            System.err.format("%s: no such" + " file or directory%n", fileLocation)
        } catch (e: DirectoryNotEmptyException) {
            System.err.format("%s not empty%n", fileLocation)
        } catch (e: IOException) {
            // File permission problems are caught here.
            System.err.println(e)
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