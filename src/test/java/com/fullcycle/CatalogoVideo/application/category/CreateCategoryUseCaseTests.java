package com.fullcycle.CatalogoVideo.application.category;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.create.CreateCategoryInputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.create.CreateCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CreateCategoryUseCaseTests {
    
    @InjectMocks
    private CreateCategoryUseCase useCase; 

    @Mock
    ICategoryRepository repository;


    @BeforeEach
    void initUseCase() {
        useCase = new CreateCategoryUseCase(repository);
    }

    @Test
    public void executeReturnsCreatedCategory() {
        Category category = new Category(
            "Action", 
            "Action Category"  
        );

        when(repository.create(any(Category.class)))
            .thenReturn(category);
        
        CreateCategoryInputData input = new CreateCategoryInputData(
            category.getName(),
            category.getDescription(),
            category.getIsActive()
        );
        CategoryOutputData actual = useCase.execute(input);
        repository.create(category);

        assertThat(actual.getName()).isEqualTo(category.getName());
        assertTrue(actual.getIsActive());
    }  
    
    @Test
    public void executeReturnsCreatedCategoryWithName() {
        Category category = new Category(
            "Action"
        );

        when(repository.create(any(Category.class)))
            .thenReturn(category);
        
        CreateCategoryInputData input = new CreateCategoryInputData(
            category.getName(),
            category.getDescription(),
            category.getIsActive()
        );
        CategoryOutputData actual = useCase.execute(input);
        repository.create(category);

        assertThat(actual.getName()).isEqualTo(category.getName());
        assertTrue(actual.getIsActive());
    } 

    @Test
    public void executeReturnsCreatedCategoryWithIsActiveFalse() {
        Category category = new Category(
            "Action",
            "Description",
            false
        );

        when(repository.create(any(Category.class)))
            .thenReturn(category);
        
        CreateCategoryInputData input = new CreateCategoryInputData(
            category.getName(),
            category.getDescription(),
            category.getIsActive()
        );
        CategoryOutputData actual = useCase.execute(input);
        repository.create(category);

        assertThat(actual.getName()).isEqualTo(category.getName());
        assertThat(actual.getDescription()).isEqualTo(category.getDescription());
        assertFalse(actual.getIsActive());
    }     
}

