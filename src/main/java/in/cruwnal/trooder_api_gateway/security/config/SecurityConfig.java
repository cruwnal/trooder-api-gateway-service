package in.cruwnal.trooder_api_gateway.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http){
        http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll() // Let users reach Auth Service to login
                        .anyExchange().authenticated()        // Protect everything else
                ).oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));//It activates the logic to look for an Authorization: Bearer <JWT> header in every incoming request.

        return http.build();
    }

}
