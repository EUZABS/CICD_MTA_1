package com.epiuse.mtaservice.config;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    // Use JWT token authentication instead of the default Basic Auth.
    // This is not required for the MTAService to work, but is an example of how to use JWT authentication.
    // See https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-jwt
    // for more information.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//
            .and()
            .authorizeHttpRequests((authz) -> authz
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer((oauth2) -> oauth2
                .jwt(withDefaults())
            );
        return http.build();
    }

    //  @Bean
    //     public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //             http
    //                     .authorizeRequests((authorizeRequests) ->
    //                             authorizeRequests
    //                                     .anyRequest().authenticated()
    //                     )
    //                     .oauth2Client(withDefaults());
    //             return http.build();
    //     }


    // Google Oauth auth
    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //         http
    //                 .authorizeRequests((authorizeRequests) ->
    //                         authorizeRequests
    //                                 .anyRequest().authenticated()
    //                 )
    //                 .oauth2Login(withDefaults());
    //         return http.build();
    // }

    // @Bean
    // public ClientRegistrationRepository clientRegistrationRepository() {
    //         return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
    // }

    // private ClientRegistration googleClientRegistration() {
    //         return ClientRegistration.withRegistrationId("google")
    //                 .clientId("google-client-id")
    //                 .clientSecret("google-client-secret")
    //                 .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
    //                 .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
    //                 .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
    //                 .scope("openid", "profile", "email", "address", "phone")
    //                 .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
    //                 .tokenUri("https://www.googleapis.com/oauth2/v4/token")
    //                 .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
    //                 .userNameAttributeName(IdTokenClaimNames.SUB)
    //                 .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
    //                 .clientName("Google")
    //                 .build();
    // }

}