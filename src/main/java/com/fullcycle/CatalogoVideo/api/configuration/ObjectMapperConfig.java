package com.fullcycle.CatalogoVideo.api.configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class ObjectMapperConfig {
  
  private static final ObjectMapper INSTANCE = new Jackson2ObjectMapperBuilder()
    .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
    .modules(new Jdk8Module(), new JavaTimeModule())
    .featuresToDisable(
      DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
      SerializationFeature.WRITE_DATES_AS_TIMESTAMPS
    )
    .featuresToEnable(
      MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS,
      SerializationFeature.WRITE_ENUMS_USING_TO_STRING,
      DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL
    )
    .build();

  public static ObjectMapper getMapper() {
    return INSTANCE.copy();
  }

  @Bean
  public ObjectMapper objectMapper() {
    return INSTANCE;
  }
}