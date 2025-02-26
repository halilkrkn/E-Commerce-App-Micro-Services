package org.halilkrkn.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

// EnableWebFluxSecurity ne işe yarıyor?
// Bu anotasyon, Spring Security'nin WebFlux için yapılandırılmasını etkinleştirir.
// Bu, Spring WebFlux uygulamaları için güvenlik yapılandırmasını sağlar.
// WebFlux, Spring Framework 5.0'da tanıtılan yeni bir web çerçevesidir.
// WebFlux, Spring MVC'nin yanı sıra, reaktif programlama modelini destekleyen bir web çerçevesidir.
// WebFlux, reaktif programlama modelini kullanarak, daha az kaynak kullanarak daha fazla işlem yapılmasını sağlar.


@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/eureka/**")
                        .permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 ->
                        oauth2.jwt(Customizer.withDefaults()));

        return serverHttpSecurity.build();
    }
}
