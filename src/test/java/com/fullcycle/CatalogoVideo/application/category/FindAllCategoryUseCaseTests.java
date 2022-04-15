package com.fullcycle.CatalogoVideo.application.category;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fullcycle.CatalogoVideo.application.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.category.findall.FindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.category.Category;
import com.fullcycle.CatalogoVideo.domain.category.gateways.ICategoryGateway;
import com.fullcycle.CatalogoVideo.runners.UnitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

public class FindAllCategoryUseCaseTests extends UnitTest {

    @InjectMocks
    private FindAllCategoryUseCase useCase; 

    @Mock
    private ICategoryGateway repository;

    @BeforeEach
    void initUseCase() {
        Mockito.reset(repository);
    }

    @Test
    public void executeReturnsFindAllCategory() {
        List<Category> categories = Arrays.asList(
            Category.newCategory(
                "Action",
                "Action Description",
                true
            ),
            Category.newCategory(
                "Horror",
                "Horror Description",
                true
            ),
            Category.newCategory(
                "Suspense",
                "Suspense Description",
                true
            )
        );

        when(repository.findAll())
            .thenReturn(categories);
        
        // doReturn(categories)
        //     .when(repository)
        //     .findAll();

        useCase.execute();
        repository.findAll();
        
        assertThat(categories).isNotNull();
        assertThat(categories).hasSize(3);
    }

    @Test
    public void executeReturnsFindAllCategoryAndListSizeIsZero() {
        List<Category> categories = new ArrayList<Category>();

        when(repository.findAll())
            .thenReturn(categories);

        List<CategoryOutputData> actual = useCase.execute();
        repository.findAll();
        
        assertThat(categories).isNotNull();
        assertThat(categories).hasSize(0);

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(0);
        
        verify(repository, times(2)).findAll();
    }
}
