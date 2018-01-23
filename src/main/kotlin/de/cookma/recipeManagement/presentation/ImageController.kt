package de.cookma.recipeManagement.presentation

import de.cookma.recipeManagement.infrastructure.store.FileDto
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import de.cookma.recipeManagement.infrastructure.store.RecipeImageStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation .*

@RestController
@RequestMapping("/images")
@CrossOrigin
class ImageController {

    @Autowired
    lateinit var recipeImageStore: RecipeImageStore

    @DeleteMapping
    fun deleteAllFiles(): String {
        recipeImageStore.deleteAll()
        return "OK"
    }

    @GetMapping("/{imagename:.+}")
    @ResponseBody
    fun getFile(@PathVariable imagename: String): ResponseEntity<Resource> {
        val file = recipeImageStore.load(imagename)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file)
    }

    @PostMapping
    fun storeImage(@RequestBody file: FileDto): String {
        recipeImageStore.store("subdir", file.file)
        return "ok"
    }
}
