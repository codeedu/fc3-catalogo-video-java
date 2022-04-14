package com.fullcycle.CatalogoVideo.api.category;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullcycle.CatalogoVideo.api.configuration.GlobalExceptionHandler;
import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.category.create.ICreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.delete.IDeleteCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.findall.IFindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.get.IFindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.IUpdateCategoryUseCase;
import com.fullcycle.CatalogoVideo.application.category.update.UpdateCategoryInputData;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

public class CategoryEndpointTests extends UnitTest {
    
    private MockMvc mockMvc;
    private JacksonTester<CreateCategoryInputData> createJson;
    private JacksonTester<UpdateCategoryInputData> updateJson;

    @InjectMocks
    private CategoryEndpoint endpoint;

    @Mock
    private ICreateCategoryUseCase createUseCase;

    @Mock
    private IFindAllCategoryUseCase findAllUseCase;

    @Mock
    private IFindByIdCategoryUseCase findByIdUseCase;

    @Mock
    private IDeleteCategoryUseCase removeUseCase;

    @Mock
    private IUpdateCategoryUseCase updateUseCase;

    @BeforeEach
    public void init() {
        JacksonTester.initFields(this, new ObjectMapper());
        mockMvc = MockMvcBuilders
                    .standaloneSetup(endpoint)
                    .setControllerAdvice(new GlobalExceptionHandler())
                    .build();
    }

    @Test
    public void createCategory() throws Exception {
        CreateCategoryInputData input = new CreateCategoryInputData();
        input.setName("Action");

        String payload = createJson.write(input)
                                   .getJson();

        Category entity = new Category(
            "Action",
            "",
            true
        );
        CategoryOutputData output = new CategoryOutputData(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getIsActive()
        );

        doReturn(output)
            .when(createUseCase)
            .execute(any(CreateCategoryInputData.class));

        mockMvc.perform(post("/categories")
               .contentType(APPLICATION_JSON)
               .content(payload))
               .andExpect(status().isCreated())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$.name", is("Action")));
    }

    @Test
    public void findAllCategories() throws Exception {
        Category entity1 = new Category(
            "Action",
            "",
            true            
        );
        Category entity2 = new Category(
            "Horror",
            "",
            true            
        );     
        CategoryOutputData output1 = new CategoryOutputData(
            entity1.getId(),
            entity1.getName(),
            entity1.getDescription(),
            entity1.getIsActive()
        );
        CategoryOutputData output2 = new CategoryOutputData(
            entity2.getId(),
            entity2.getName(),
            entity2.getDescription(),
            entity2.getIsActive()
        );  
        
        List<CategoryOutputData> output = new ArrayList<CategoryOutputData>();
        output.addAll(Arrays.asList(output1, output2));

        doReturn(output)
            .when(findAllUseCase)
            .execute();

        mockMvc.perform(get("/categories")
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findByIdCategory() throws Exception {
        Category entity = new Category(
            "Action",
            "",
            true            
        );
        CategoryOutputData output = new CategoryOutputData(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getIsActive()
        );

        doReturn(output)
            .when(findByIdUseCase)
            .execute(entity.getId());

        mockMvc.perform(get("/categories/" + entity.getId())
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON));        
    }
    
    @Test
    public void removeCategory() throws Exception {
        Category entity = new Category(
            "Action",
            "",
            true
        );

        doNothing()
            .when(removeUseCase)
            .execute(entity.getId());

        mockMvc.perform(delete("/categories/" + entity.getId())
               .contentType(APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }

    @Test
    public void updateCategory() throws Exception {
        Category entity = new Category(
            "Action",
            "",
            true
        );
        UpdateCategoryInputData input = new UpdateCategoryInputData();
        input.setName("Horror");   
        input.setDescription(entity.getDescription());
        input.setIsActive(entity.getIsActive());     

        String payload = updateJson.write(input)
                                   .getJson();

        doNothing()
            .when(updateUseCase)
            .execute(entity.getId(), input);

        mockMvc.perform(put("/categories/" + entity.getId())
               .contentType(APPLICATION_JSON)
               .content(payload))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isNoContent());
    }
}
