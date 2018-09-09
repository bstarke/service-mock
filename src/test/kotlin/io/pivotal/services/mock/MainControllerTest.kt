package io.pivotal.services.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.rule.OutputCapture
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainControllerTest {

    @Autowired
    lateinit var restTemplate: TestRestTemplate

    val capture = OutputCapture()
    @Rule
    fun capture(): OutputCapture = capture

    @Test
    fun getLastBody() {
        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>("{\"Hello\": \"Simon\"}", headers)
        restTemplate.postForLocation("/bs/post/last", entity)
        val lastRequest = restTemplate.getForEntity<String>("/last")
        assertThat(lastRequest.body.toString()).contains("\"Content-Type\":[\"application/json")
        assertThat(lastRequest.body.toString()).contains("/bs/post/last")
        assertThat(lastRequest.body.toString()).contains("\"body\":\"{\\\"Hello\\\": \\\"Simon\\\"}\"")
    }

    @Test
    fun logGetAndReturn() {
        restTemplate.getForEntity<String>("/bs/get/it?what=nothing")
        assertThat(capture.toString()).contains("Get Request Path:")
        assertThat(capture.toString()).contains("/bs/get/it")
        assertThat(capture.toString()).contains("Get Request Query:")
        assertThat(capture.toString()).contains("what=nothing")
    }

    @Test
    fun logPostAndReturn() {
        var headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        val entity = HttpEntity<String>("{\"Hello\": \"Brad\"}", headers)
        restTemplate.postForLocation("/bs/post", entity)
        assertThat(capture.toString()).contains("Post Request Path:")
        assertThat(capture.toString()).contains("/bs/post")
        assertThat(capture.toString()).contains("{\"Hello\": \"Brad\"}")
        assertThat(capture.toString()).contains("Content-Type=[application/json")
    }

    @Test
    fun logPutAndReturn() {
        restTemplate.put("/bs/put", "{\"Hello\": \"Tyson\"}")
        assertThat(capture.toString()).contains("Put Request Path:")
        assertThat(capture.toString()).contains("/bs/put")
        assertThat(capture.toString()).contains("{\"Hello\": \"Tyson\"}")
    }

    @Test
    fun logPatchAndReturn() {
        restTemplate.postForObject("/bs/patch?_method=patch", "{\"Hello\": \"Bella\"}",String::class.java)
        assertThat(capture.toString()).contains("Patch Request Path:")
        assertThat(capture.toString()).contains("/bs/patch")
        assertThat(capture.toString()).contains("{\"Hello\": \"Bella\"}")
    }

    @Test
    fun logDeleteAndReturn() {
        restTemplate.delete("/bs/delete")
        assertThat(capture.toString()).contains("Delete Request Path:")
        assertThat(capture.toString()).contains("/bs/delete")
    }
}