package com.fullcycle.CatalogoVideo.runners;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class E2eTest extends IntegrationTest {
  
  @Autowired
  private ObjectMapper mapper;

  protected String writeValueAsString(final Object o) {
    try {
      return mapper.writeValueAsString(o);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }

  protected <T> T readValue(final String json, final Class<T> clazz) {
    try {
      return mapper.readValue(json, clazz);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
