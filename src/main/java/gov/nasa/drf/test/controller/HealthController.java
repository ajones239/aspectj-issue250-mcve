package gov.nasa.drf.test.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @RequestMapping(
        value = "/health/database",
        method = RequestMethod.GET
    )
    ResponseEntity<?> getDatabaseHealth(@RequestHeader("Authorization") String bearerToken)
    {
        return null;
    }

}

