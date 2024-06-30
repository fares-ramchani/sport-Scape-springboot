package com.elife.sprotscape;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@SpringBootApplication
public class SprotscapeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprotscapeApplication.class, args);}
    @Bean
    CommandLineRunner start(JdbcUserDetailsManager JdbcUserDetailsManager, PasswordEncoder PasswordEncoder) {

      return args -> {
        if (!JdbcUserDetailsManager.userExists("fares"))
          JdbcUserDetailsManager.createUser(User.withUsername("fares").password(PasswordEncoder.encode("12345")).authorities("ADMIN").build());
        if (!JdbcUserDetailsManager.userExists("achraf"))
          JdbcUserDetailsManager.createUser(User.withUsername("achraf").password(PasswordEncoder.encode("12345")).authorities("ADMIN").build());
        if (!JdbcUserDetailsManager.userExists("aya"))
          JdbcUserDetailsManager.createUser(User.withUsername("aya").password(PasswordEncoder.encode("12345")).authorities("ADMIN").build());


      };
    }
  }
