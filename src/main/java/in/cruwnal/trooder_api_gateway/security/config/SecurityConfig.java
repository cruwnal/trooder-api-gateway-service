package in.cruwnal.trooder_api_gateway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {




        @Bean
        public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
            return http
                    .csrf(ServerHttpSecurity.CsrfSpec::disable) // Modern functional style
                    .authorizeExchange(exchanges -> exchanges
                            // Public endpoints
                            .pathMatchers("/actuator/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                            .pathMatchers("/api/products/**").permitAll()

                            // Everything else requires a valid JWT
                            .anyExchange().authenticated()
                    )
                    .oauth2ResourceServer(oauth2 -> oauth2
                            .jwt(Customizer.withDefaults())
                    )
                    .build();
        }

    // ADD THIS TO PREVENT CRASHING IF JWK-SET-URI IS OFFLINE
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://72.61.249.10:8081/oauth2/jwks").build();
    }
}
