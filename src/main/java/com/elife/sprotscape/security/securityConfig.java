package com.elife.sprotscape.security;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.introspection.OpaqueTokenIntrospector;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.function.client.WebClient;

import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import java.util.List;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableMethodSecurity(prePostEnabled = true)
public class securityConfig {
  final WebClient userInfoClient;

  @Bean
  public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  // @Bean
  // public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
  // PasswordEncoder passwordEncoder = passwordEncoder();

  // InMemoryUserDetailsManager aa = new InMemoryUserDetailsManager();
  // aa.createUser(User.withUsername("user11").password(passwordEncoder.encode("12345")).authorities("USER").build());

  // aa.createUser(
  // User.withUsername("admin22").password(passwordEncoder.encode("12345")).authorities("USER",
  // "ADMIN").build());
  // return aa;
  // }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(ar -> ar
        .requestMatchers("/auth/login/**").permitAll()
        .requestMatchers("/auth/signUpGoogle/**").permitAll()
        .requestMatchers("/auth/url/**").permitAll()
        .requestMatchers("/auth/callback/**").permitAll()
        .requestMatchers("/auth/AcessToken/**").permitAll()
        .requestMatchers("/stripe/payment/**").permitAll()
        .requestMatchers("/propritaire/ajouterPropritaire/**").permitAll()
        .requestMatchers("/propritaire/VerifierCompte/**").permitAll()
        .requestMatchers("/stade/getToutLesStades/**").permitAll()
        .requestMatchers("/stade/getStadeByIdAndActivite/**").permitAll()
        .requestMatchers("/stade/getStadeById/**").permitAll()
        .requestMatchers("/stade/getStadeByVilleAndActivite/**").permitAll()
        .requestMatchers("/activite/**").permitAll()
          .requestMatchers("/Seances/**").permitAll()

      )
      .authorizeHttpRequests(ar -> ar
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oa -> oa.jwt(Customizer.withDefaults()))
      .cors(Customizer.withDefaults())
      .build();
  }
  @Bean
  public SecurityFilterChain securityFilterChain2(HttpSecurity http) throws Exception {
    return http
      .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .csrf(csrf -> csrf.disable())
      .authorizeHttpRequests(ar -> ar
        .requestMatchers("/auth/login/**").permitAll()
        .requestMatchers("/auth/signUpGoogle/**").permitAll()
        .requestMatchers("/auth/url/**").permitAll()
        .requestMatchers("/auth/callback/**").permitAll()
        .requestMatchers("/auth/AcessToken/**").permitAll()
      )
      .authorizeHttpRequests(ar -> ar
        .anyRequest().authenticated()
      )
      .oauth2ResourceServer(oa -> oa.opaqueToken(Customizer.withDefaults()))
      .cors(Customizer.withDefaults())
      .build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    String secretKey = "bgjdfghdf6sh5sd4h5dsh65stdh65stfh654SF6hsfsh15+sf1th56gfhs4785lg";
    return new NimbusJwtEncoder(new ImmutableSecret<>(secretKey.getBytes()));
  }

  @Bean
  JwtDecoder jwtDecoder() {
    String secretKey = "bgjdfghdf6sh5sd4h5dsh65stdh65stfh654SF6hsfsh15+sf1th56gfhs4785lg";
    SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey.getBytes(), "RSA");
    return NimbusJwtDecoder.withSecretKey(secretKeySpec).macAlgorithm(MacAlgorithm.HS512).build();
  }

  @Bean
  public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    daoAuthenticationProvider.setUserDetailsService(userDetailsService);
    return new ProviderManager(daoAuthenticationProvider);
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration corsConfiguration = new CorsConfiguration();
    corsConfiguration.addAllowedOrigin("*");
    corsConfiguration.addAllowedMethod("*");
    corsConfiguration.addAllowedHeader("*");
    corsConfiguration.setExposedHeaders(List.of("x-auth-token"));
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfiguration);
    return source;
  }

  @Bean
  public OpaqueTokenIntrospector introspector() {
    return new GoogleOpaqueTokenIntrospector(userInfoClient);
  }
}
