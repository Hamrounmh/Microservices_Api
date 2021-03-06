package fr.univ.dauphine.innov.message.config;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {
    @Autowired
    private ServerSecurityContextRepository securityContextRepository;

    @Bean
    ReactiveUserDetailsService noOps() {
        return s -> Mono.empty();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http    .cors().and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable()
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.GET,"/operations/*").permitAll()
                .pathMatchers(HttpMethod.GET,"/operations").permitAll()
                .pathMatchers(HttpMethod.POST,"/operations").permitAll()
                .pathMatchers(HttpMethod.GET,"/operationsHistory").permitAll()
                .pathMatchers(HttpMethod.DELETE,"/operations/*").permitAll()
                .pathMatchers(HttpMethod.PUT,"/operations").permitAll()
                // allow health check for consul !
                .pathMatchers(HttpMethod.GET,"/actuator/health").permitAll()
                .anyExchange().authenticated();
        return http.build();
    }
}
