package io.pivotal.services.mock

import org.springframework.http.RequestEntity

data class DisplayableRequest(val requestEntity: RequestEntity<String>) {}