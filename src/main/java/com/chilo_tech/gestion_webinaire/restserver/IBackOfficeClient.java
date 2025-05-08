package com.chilo_tech.gestion_webinaire.restserver;


import com.chilo_tech.gestion_webinaire.dto.BackendRequestVote;
import com.chilo_tech.gestion_webinaire.dto.ContactRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Map;

@FeignClient(name = "backoffice",url = "${backoffice.api.url}")
public interface IBackOfficeClient {

    @PostMapping("items/votes")
    ResponseEntity<?> createVotes(@RequestHeader("Authorization") String token, @RequestBody List<BackendRequestVote> votes);

    @PostMapping("items/contacts")
    ResponseEntity<?> createUser(@RequestHeader("Authorization") String token, @RequestBody ContactRequest contact);
}
