package io.pivotal.services.mock

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.net.URI

@Controller
class MainController {

    val log: Logger = LoggerFactory.getLogger(MainController::class.java)
    var displayableRequest: DisplayableRequest = DisplayableRequest(RequestEntity(HttpMethod.GET, URI.create("")))

    @GetMapping("/last")
    fun getLastBody(model: Model): String {
        model.addAttribute("displayableRequest", displayableRequest)
        return "last"
    }
    @GetMapping @ResponseBody
    fun logGetAndReturn(requestEntity: RequestEntity<String>): ResponseEntity<String> {
        displayableRequest = DisplayableRequest(requestEntity)
        log.info("Get Request Path: {}", requestEntity.url.path)
        log.info("Get Request Query: {}", requestEntity.url.query)
        log.info("Get Request Headers: {}", requestEntity.headers)
        return ResponseEntity(HttpStatus.OK)
    }
    @PostMapping @ResponseBody
    fun logPostAndReturn(requestEntity: RequestEntity<String>): ResponseEntity<String> {
        displayableRequest = DisplayableRequest(requestEntity)
        log.info("Post Request Path: {}", requestEntity.url.path)
        log.info("Post Request Headers: {}", requestEntity.headers)
        log.info("Post Request Body: {}", requestEntity.body)
        return ResponseEntity(HttpStatus.CREATED)
    }
    @PutMapping @ResponseBody
    fun logPutAndReturn(requestEntity: RequestEntity<String>): ResponseEntity<String> {
        displayableRequest = DisplayableRequest(requestEntity)
        log.info("Put Request Path: {}", requestEntity.url.path)
        log.info("Put Request Headers: {}", requestEntity.headers)
        log.info("Put Request Body: {}", requestEntity.body)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @PatchMapping @ResponseBody
    fun logPatchAndReturn(requestEntity: RequestEntity<String>): ResponseEntity<String> {
        displayableRequest = DisplayableRequest(requestEntity)
        log.info("Patch Request Path: {}", requestEntity.url.path)
        log.info("Patch Request Headers: {}", requestEntity.headers)
        log.info("Patch Request Body: {}", requestEntity.body)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
    @DeleteMapping @ResponseBody
    fun logDeleteAndReturn(requestEntity: RequestEntity<String>): ResponseEntity<String> {
        displayableRequest = DisplayableRequest(requestEntity)
        log.info("Delete Request Path: {}", requestEntity.url.path)
        log.info("Delete Request Headers: {}", requestEntity.headers)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}