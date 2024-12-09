package ovh.wiktormalyska.pharmacysystembackend.security;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  public SecurityConfig(
      JwtAuthenticationFilter jwtAuthenticationFilter,
      AuthenticationProvider authenticationProvider) {
    this.authenticationProvider = authenticationProvider;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("auth/**")
                    .permitAll()
                    .requestMatchers("drug/**")
                    .hasAnyAuthority("PHARMACIST", "MANAGER", "ADMINISTRATOR")
                    .requestMatchers("drug/order/**")
                    .hasAnyAuthority("MANAGER", "ADMINISTRATOR")
                    .requestMatchers("drug/order/add", "drug/order/get")
                    .hasAnyAuthority("PHARMACIST", "MANAGER", "ADMINISTRATOR")
                    .requestMatchers("pharmacy/**", "pharmacist/**", "manager/**")
                    .hasAnyAuthority("MANAGER", "ADMINISTRATOR")
//                    .requestMatchers("administrator/**")
//                    .hasAnyAuthority("ADMINISTRATOR")
                    .requestMatchers("administrator/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .cors(cors -> cors.configurationSource(corsConfigurationSource()))
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(
        List.of(
            "http://localhost:3000",
            "https://frontend.pharmacy.wiktormalyska.ovh",
            "https://pharmacy.wiktormalyska.ovh"));
    configuration.setAllowedMethods(List.of("*"));
    configuration.setAllowedHeaders(List.of("*"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    source.registerCorsConfiguration("/**", configuration);

    return source;
  }
}
