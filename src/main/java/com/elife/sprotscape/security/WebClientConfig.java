package com.elife.sprotscape.security;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class WebClientConfig {

  private String introspectUri = "https://www.googleapis.com/";

  @Bean
  public WebClient userInfoClient() {
    return WebClient.builder().baseUrl(introspectUri).build();
  }
}
