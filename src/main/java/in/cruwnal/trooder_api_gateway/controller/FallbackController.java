package in.cruwnal.trooder_api_gateway.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@RestController
public class FallbackController {
// fallback controller added
    @GetMapping("/fallback/product-service")
    public Mono<ResponseEntity<Map<String, Object>>> productServiceFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "Service Unavailable");
        response.put("message", "Product Service is down");

        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
    }

    @GetMapping("/fallback/auth-service")
    public Mono<ResponseEntity<Map<String, Object>>> authServiceFallback() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "Service Unavailable");
        response.put("message", "Authentication is currently unavailable. We are working on it!");
        return Mono.just(ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(response));
    }
}
