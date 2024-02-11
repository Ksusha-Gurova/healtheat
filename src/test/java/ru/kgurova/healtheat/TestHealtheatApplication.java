package ru.kgurova.healtheat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.TestConfiguration;

@TestConfiguration(proxyBeanMethods = false)
public class TestHealtheatApplication {

  public static void main(String[] args) {
    SpringApplication.from(HealtheatApplication::main).with(TestHealtheatApplication.class)
        .run(args);
  }

}
