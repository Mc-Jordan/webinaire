package com.chilo_tech.gestion_webinaire.restserver;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "backoffice",url = "${backoffice.api.url}/item/votes")
public interface IBackOfficeClient {

    @GetMapping
    ResponseEntity<?> getUser(@RequestHeader("AUTHORIZATION") String token)
}
