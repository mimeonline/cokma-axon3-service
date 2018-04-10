package io.cookma.imagemanagement.presentation

import io.cookma.imagemanagement.application.ImageApplicationService
import io.cookma.imagemanagement.infrastructure.store.FileDto
import io.cookma.imagemanagement.infrastructure.store.ImageStore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/images")
@CrossOrigin
class ImageController {

    @Autowired
    lateinit var imageStore: ImageStore

    @Autowired
    lateinit var imageApplicationService: ImageApplicationService

    @DeleteMapping
    fun deleteAllFiles(): String {
        imageStore.deleteAll()
        return "OK"
    }

    @GetMapping("/{imagename:.+}")
    @ResponseBody
    fun getFile(@PathVariable imagename: String): ResponseEntity<Resource> {
        val file = imageStore.load(imagename)
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.filename + "\"")
                .body(file)
    }

    @PostMapping
    fun storeImage(@RequestBody file: FileDto) = imageApplicationService.storeImage(file)

}
