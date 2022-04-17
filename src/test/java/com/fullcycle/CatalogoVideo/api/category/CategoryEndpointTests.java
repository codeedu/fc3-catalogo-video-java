package com.fullcycle.CatalogoVideo.api.category;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.eq;
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

import java.util.List;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.api.configuration.GlobalExceptionHandler;
import com.fullcycle.CatalogoVideo.api.configuration.ObjectMapperConfig;
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

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
        JacksonTester.initFields(this, ObjectMapperConfig.getMapper());
        mockMvc = MockMvcBuilders
                    .standaloneSetup(endpoint)
                    .setControllerAdvice(new GlobalExceptionHandler())
                    .setMessageConverters(new MappingJackson2HttpMessageConverter(
                        ObjectMapperConfig.getMapper()
                    ))
                    .build();
    }

    @Test
    public void createCategory() throws Exception {
        final String expectedName = "Action";
        final String expectedDescription = "The most watched category";
        final boolean expectedIsActive = true;

        final CreateCategoryInputData input = new CreateCategoryInputData();
        input.setName(expectedName);
        input.setDescription(expectedDescription);
        input.setIsActive(expectedIsActive);

        final String payload = createJson.write(input).getJson();

        final CategoryOutputData output = CategoryOutputData.fromDomain(Category.newCategory(
            expectedName,
            expectedDescription,
            expectedIsActive
        ));

        doReturn(output)
            .when(createUseCase).execute(eq(input));

        mockMvc.perform(post("/categories")
               .contentType(APPLICATION_JSON)
               .content(payload))
               .andDo(MockMvcResultHandlers.log())
               .andExpect(status().isCreated())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(notNullValue())))
               .andExpect(jsonPath("$.name", is(expectedName)))
               .andExpect(jsonPath("$.description", is(expectedDescription)))
               .andExpect(jsonPath("$.is_active", is(expectedIsActive)));
    }

    @Test
    public void findAllCategoriesWithDefaultParams() throws Exception {
        final var expectedDefaultSearch = "";
        final var expectedDefaultPage = 0;
        final var expectedDefaultPerPage = 15;
        final var expectedDefaultSort = "name";
        final var expectedDefaultDir = "asc";

        final CategoryOutputData expectecActionCategory = CategoryOutputData.fromDomain(Category.newCategory(
            "Action",
            "The most watched category",
            true            
        ));

        final CategoryOutputData expectecHorrorCategory = CategoryOutputData.fromDomain(Category.newCategory(
            "Horror",
            "The second most watched category",
            true
        ));  
        
        final IFindAllCategoryUseCase.Input useCaseInput = IFindAllCategoryUseCase.Input.builder()
            .search(expectedDefaultSearch)
            .page(expectedDefaultPage)
            .perPage(expectedDefaultPerPage)
            .sort(expectedDefaultSort)
            .direction(expectedDefaultDir)
            .build();

        doReturn(List.of(expectecActionCategory, expectecHorrorCategory))
            .when(findAllUseCase).execute(eq(useCaseInput));

        mockMvc.perform(get("/categories")
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findAllCategoriesWithFulfilledParams() throws Exception {
        final var expectedSearch = "act";
        final var expectedPage = 2;
        final var expectedPerPage = 20;
        final var expectedSort = "description";
        final var expectedDir = "desc";

        final CategoryOutputData expectecActionCategory = CategoryOutputData.fromDomain(Category.newCategory(
            "Action",
            "The most watched category",
            true            
        ));
        
        final IFindAllCategoryUseCase.Input useCaseInput = IFindAllCategoryUseCase.Input.builder()
            .search(expectedSearch)
            .page(expectedPage)
            .perPage(expectedPerPage)
            .sort(expectedSort)
            .direction(expectedDir)
            .build();

        doReturn(List.of(expectecActionCategory))
            .when(findAllUseCase).execute(eq(useCaseInput));

        mockMvc.perform(
                    get("/categories")
                        .queryParam("search", expectedSearch)
                        .queryParam("page", String.valueOf(expectedPage))
                        .queryParam("perPage", String.valueOf(expectedPerPage))
                        .queryParam("sort", expectedSort)
                        .queryParam("dir", expectedDir)
                        .contentType(APPLICATION_JSON)
                )
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$", hasSize(1)))
               .andExpect(jsonPath("$.[0].id", Matchers.equalTo(expectecActionCategory.getId().toString())));
    }

    @Test
    public void findByIdCategory() throws Exception {
        final String expectedName = "Action";
        final String expectedDescription = "The most watched category";
        final boolean expectedIsActive = true;

        final CategoryOutputData expectecActionCategory = CategoryOutputData.fromDomain(Category.newCategory(
            expectedName,
            expectedDescription,
            expectedIsActive
        ));

        doReturn(expectecActionCategory)
            .when(findByIdUseCase)
            .execute(expectecActionCategory.getId());

        mockMvc.perform(get("/categories/" + expectecActionCategory.getId())
               .contentType(APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(content().contentType(APPLICATION_JSON))
               .andExpect(jsonPath("$.id", is(expectecActionCategory.getId().toString())))
               .andExpect(jsonPath("$.name", is(expectedName)))
               .andExpect(jsonPath("$.description", is(expectedDescription)))
               .andExpect(jsonPath("$.is_active", is(expectedIsActive))); 
    }
    
    @Test
    public void removeCategory() throws Exception {
        final UUID expectedId = UUID.randomUUID();

        doNothing()
            .when(removeUseCase).execute(expectedId);

        mockMvc.perform(delete("/categories/" + expectedId.toString())
               .contentType(APPLICATION_JSON))
               .andExpect(status().isNoContent());
    }

    @Test
    public void updateCategory() throws Exception {
        final String expectedName = "Horror";
        final String expectedDescription = "The most watched category";
        final boolean expectedIsActive = true;

        final Category entity = Category.newCategory(
            "Action",
            expectedDescription,
            expectedIsActive
        );

        final UpdateCategoryInputData input = new UpdateCategoryInputData();
        input.setName(expectedName);   
        input.setDescription(expectedDescription);
        input.setIsActive(expectedIsActive);     

        final String payload = updateJson.write(input).getJson();

        doNothing()
            .when(updateUseCase).execute(eq(entity.getId()), eq(input));

        mockMvc.perform(put("/categories/" + entity.getId())
               .contentType(APPLICATION_JSON)
               .content(payload))
               .andDo(MockMvcResultHandlers.print())
               .andExpect(status().isNoContent());
    }
}
