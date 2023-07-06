package io.github.eendroroy.bootstrap.controller.api.v1

import io.github.eendroroy.bootstrap.controller.BaseApiController
import io.github.eendroroy.bootstrap.dto.response.HelloResponse
import io.github.eendroroy.bootstrap.dto.response.common.AppResponse
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(
    "/api/v1/controller",
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
class V1AppController : BaseApiController() {
    @GetMapping("/hello")
    fun getRetailerInfo(@RequestParam retailerElNumber: String): ResponseEntity<AppResponse<HelloResponse>> {
        return ResponseEntity.status(HttpStatus.OK).body(AppResponse(HelloResponse("Hi")))
    }
}
