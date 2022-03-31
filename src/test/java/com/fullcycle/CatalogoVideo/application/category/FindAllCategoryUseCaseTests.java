package com.fullcycle.CatalogoVideo.application.category;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

import com.fullcycle.CatalogoVideo.application.usecase.category.common.CategoryOutputData;
import com.fullcycle.CatalogoVideo.application.usecase.category.findall.FindAllCategoryUseCase;
import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.repository.ICategoryRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class FindAllCategoryUseCaseTests {

    @InjectMocks
    private FindAllCategoryUseCase useCase; 

    @Mock
    ICategoryRepository repository;


    @BeforeEach
    void initUseCase() {
        useCase = new FindAllCategoryUseCase(repository);
    }

    @Test
    public void executeReturnsFindAllCategory() {
        List<Category> categories = Arrays.asList(
            new Category(
                "Action",
                "Action Description",
                true
            ),
            new Category(
                "Horror",
                "Horror Description",
                true
            ),
            new Category(
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

        List<CategoryOutputData> actual = useCase.execute();
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
