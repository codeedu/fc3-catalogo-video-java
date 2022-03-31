package com.fullcycle.CatalogoVideo.application.category;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.UUID;

import com.fullcycle.CatalogoVideo.application.exception.NotFoundException;
import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.get.FindByIdCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FindByIdCategoryUseCaseTests {

    @InjectMocks
    private FindByIdCategoryUseCase useCase; 

    @Mock
    ICategoryRepository repository;


    @BeforeEach
    void initUseCase() {
        useCase = new FindByIdCategoryUseCase(repository);
    }

    @Test
    public void executeReturnsFindById() {
        Category category = new Category(
            "Action",
            "Action Description",
            true
        );

        Optional<Category> opCategory = Optional.of(category);

        when(repository.findById(category.getId()))
            .thenReturn(opCategory);
        
        doReturn(opCategory)
            .when(repository)
            .findById(category.getId());

        CategoryOutputData actual = useCase.execute(category.getId());
        repository.findById(category.getId());
        
        assertThat(category).isNotNull();
        assertThat(actual).isNotNull();
    }

    @Test
    public void throwNotFoundExceptionWhenIdIsWrong() {
        assertThrows(NotFoundException.class, () -> useCase.execute(UUID.fromString("fe8a03d7-6495-4231-9843-8ee2f5282622")));
    }    
}
