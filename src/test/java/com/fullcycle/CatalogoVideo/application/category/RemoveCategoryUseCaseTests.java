package com.fullcycle.CatalogoVideo.application.category;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.assertj.core.api.Assertions.assertThat;


import com.fullcycle.CatalogoVideo.application.usecase.category.delete.RemoveCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class RemoveCategoryUseCaseTests {

    @InjectMocks
    private RemoveCategoryUseCase useCase; 

    @Mock
    ICategoryRepository repository;


    @BeforeEach
    void initUseCase() {
        useCase = new RemoveCategoryUseCase(repository);
    }

    @Test
    public void executeReturnsRemove() throws Exception {
        Category category = new Category(
            "Action",
            "Action Description",
            true
        );

        doNothing()
            .when(repository)
            .remove(category.getId());

        useCase.execute(category.getId());
        repository.remove(category.getId());
        
        assertThat(category).isNotNull();
        verify(repository, times(2)).remove(category.getId());
    }
}
