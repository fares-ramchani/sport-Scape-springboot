package com.elife.sprotscape.security;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.elife.sprotscape.DAO.AthleteRepository.AthleteRepository;
import com.elife.sprotscape.DTO.SecurityDTO.TokenDto;
import com.elife.sprotscape.DTO.SecurityDTO.UrlDto;
import com.elife.sprotscape.Entities.Athlete;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/auth")
@AllArgsConstructor

public class securityControlleur {
  @Autowired
  private AuthenticationManager authenticationManager;
  @Autowired
  private JdbcUserDetailsManager jdbcUserDetailsManager;
  @Autowired
  private PasswordEncoder PasswordEncoder;

  @Autowired
  private JwtEncoder jwtEncoder;

  private AthleteRepository AthleteRepository;
  final String clientId = "915793484911-nve6j81k4k2k7aek8hlfmj3d89mjruut.apps.googleusercontent.com";

  final String clientSecret = "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";

  @GetMapping(path = "/profile")
  @ResponseBody
  public Authentication authentification() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication;
  }

  @GetMapping("/url")
  public ResponseEntity<UrlDto> auth() {
    String url = new GoogleAuthorizationCodeRequestUrl(clientId,
        "http://localhost:4200",
        Arrays.asList(
            "email",
            "profile",
            "openid"))
        .build();

    return ResponseEntity.ok(new UrlDto(url));
  }

  @GetMapping("/callback")
  public ResponseEntity<TokenDto> callback(@RequestParam("code") String code) throws URISyntaxException {

    String token;
    try {
      token = new GoogleAuthorizationCodeTokenRequest(
          new NetHttpTransport(), new GsonFactory(),
          clientId,
          clientSecret,
          code,
          "http://localhost:4200").execute().getIdToken();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(new TokenDto(token));
  }
  @GetMapping("/AcessToken")
  public ResponseEntity<TokenDto> callbackgetAcessToken(@RequestParam("code") String code) throws URISyntaxException {

    String token;
    try {
      token = new GoogleAuthorizationCodeTokenRequest(
        new NetHttpTransport(), new GsonFactory(),
        clientId,
        clientSecret,
        code,
        "http://localhost:4200").execute().getAccessToken();
    } catch (IOException e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
    return ResponseEntity.ok(new TokenDto(token));
  }

  @GetMapping("/signUpGoogle")
  public ResponseEntity<TokenDto> signUpGoogle(@RequestParam("code") String code) throws URISyntaxException {
    Athlete Athlete = new Athlete();
    String token;
    try {
      token = new GoogleAuthorizationCodeTokenRequest(
          new NetHttpTransport(), new GsonFactory(),
          clientId,
          clientSecret,
          code,
          "http://localhost:4200").execute().getIdToken();

      GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
          .setAudience(Collections.singletonList(clientId))
          .build();
      GoogleIdToken idToken = null;
      idToken = verifier.verify(token);

      GoogleIdToken.Payload payload = idToken.getPayload();

      String userId = payload.getSubject();
      String email = payload.getEmail();
      boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
      String name = (String) payload.get("name");
      String pictureUrl = (String) payload.get("picture");
      Athlete.setMail(payload.getEmail());
      Athlete.setNom(name);
      jdbcUserDetailsManager
          .createUser(User.withUsername(name).password(PasswordEncoder.encode(name)).authorities("Athlete").build());
      AthleteRepository.save(Athlete);
    } catch (IOException e) {
      System.err.println(e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }

    return ResponseEntity.ok(new TokenDto(token));
  }

  @PostMapping(path = "/login")
  public Map<String, String> login(String username, String password) {
    if (jdbcUserDetailsManager.userExists(username)) {
      Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, password));
      Instant instant = Instant.now();
      String scope = authentication.getAuthorities().stream().map(a -> a.getAuthority())
          .collect(Collectors.joining(" "));
      JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
          .issuedAt(instant)
          .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
          .subject(username)
          .claim("scope", scope)
          .build();
      JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
          JwsHeader.with(MacAlgorithm.HS512).build(),
          jwtClaimsSet);
      String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();
      return Map.of("acces-token", jwt);
    } else {
      String er1 = "username n'exist pas";
      return Map.of("er1", er1);
    }
  }
}
