package com.fullcycle.CatalogoVideo.e2e.category;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.infrastructure.category.mysql.CategoryRepository;
import com.fullcycle.CatalogoVideo.runners.E2eTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class CategoryE2ETests extends E2eTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private CategoryRepository repository;

  @BeforeEach
  void before() {
    repository.deleteAll();
    repository.flush();
  }

  @Test
  public void testCreateCategory() throws Exception {
    final String expectedName = "Action";
    final String expectedDescription = "The most watched category";
    final boolean expectedIsActive = true;

    final CreateCategoryInputData input = new CreateCategoryInputData();
    input.setName(expectedName);
    input.setDescription(expectedDescription);
    input.setIsActive(expectedIsActive);

    assertEquals(0, repository.count());

    final var result = mockMvc.perform(
      post("/categories")
        .contentType(APPLICATION_JSON)
        .content(writeValueAsString(input))
    )
      .andExpect(status().isCreated())
      .andExpect(content().contentType(APPLICATION_JSON))
      .andExpect(jsonPath("$.id", is(notNullValue())))
      .andExpect(jsonPath("$.name", is(expectedName)))
      .andExpect(jsonPath("$.description", is(expectedDescription)))
      .andExpect(jsonPath("$.is_active", is(expectedIsActive)))
      .andReturn().getResponse();

    assertEquals(1, repository.count());

    final var actualResult = 
      readValue(result.getContentAsString(), CategoryOutputData.class);

    final var actualPersisted = repository.findById(actualResult.getId()).get();

    assertEquals(expectedName, actualPersisted.getName());
    assertEquals(expectedDescription, actualPersisted.getDescription());
    assertEquals(expectedIsActive, actualPersisted.getIsActive());
    assertNotNull(actualPersisted.getCreatedAt());
    assertNotNull(actualPersisted.getUpdatedAt());
    assertNull(actualPersisted.getDeletedAt());
  }
}
