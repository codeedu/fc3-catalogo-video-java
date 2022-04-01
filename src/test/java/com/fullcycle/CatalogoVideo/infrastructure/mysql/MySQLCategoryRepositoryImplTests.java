package com.fullcycle.CatalogoVideo.infrastructure.mysql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.infrastructure.data.SpringDataCategoryRepository;
import com.fullcycle.CatalogoVideo.infrastructure.persistence.CategoryPersistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class MySQLCategoryRepositoryImplTests {
    

    @InjectMocks
    private MySQLCategoryRepositoryImpl repository;

    @Mock
    private SpringDataCategoryRepository springDataRepository;

    @Test
    public void saveCategory() {
        Category expected = new Category(
            "Action",
            "Action Description",
            true
        );
        Category input = new Category(
            "Action",
            "Action Description",
            true            
        );

        doReturn(CategoryPersistence.from(expected))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));
        
        Category actual = repository.create(input);

        assertThat(actual).isNotNull();
        assertThat(actual.getName()).isEqualTo(expected.getName());
        assertThat(actual).hasFieldOrPropertyWithValue("name", "Action");
        assertTrue(actual.getIsActive());
    }

    @Test
    public void findAllCategories() {
        Category entity1 = new Category(
            "Action",
            "Action Description",
            true
        );
        Category entity2 = new Category(
            "Horror",
            "Horror Description",
            true            
        );        

        List<CategoryPersistence> expected = new ArrayList<CategoryPersistence>();
        expected.add(CategoryPersistence.from(entity1));
        expected.add(CategoryPersistence.from(entity2));

        doReturn(expected)
            .when(springDataRepository)
            .findAll();

        List<Category> actual = repository.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual).isNotEmpty();
        assertThat(actual).hasSize(2);
    }

    @Test
    public void findByIdCategory() {
        Category entity = new Category(
            "Action",
            "Action Description",
            true
        );        

        CategoryPersistence input = CategoryPersistence.from(entity);

        doReturn(Optional.of(input))
            .when(springDataRepository)
            .findById(entity.getId());

        Optional<Category> actual = repository.findById(entity.getId());

        assertThat(actual.isPresent()).isTrue();
        assertThat(actual).isNotNull();
    }

    @Test
    public void deleteCategory() {
        Category entity = new Category(
            "Action",
            "Action Description",
            true
        );    

        doNothing()
            .when(springDataRepository)
            .deleteById(entity.getId());

        repository.remove(entity.getId());  
        
        verify(springDataRepository, times(1)).deleteById(entity.getId());
    }

    @Test
    public void updateCategory() {
        Category expected = new Category(
            "Action",
            "Action Description",
            true
        );
        Category input = new Category(
            "Action",
            "Action Description",
            true            
        );

        doReturn(CategoryPersistence.from(expected))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));
        
        Category toUpdate = repository.create(input);

        toUpdate.update("Horror", toUpdate.getDescription(), false);

        doReturn(CategoryPersistence.from(toUpdate))
            .when(springDataRepository)
            .save(any(CategoryPersistence.class));

        repository.update(toUpdate);

        assertThat(toUpdate).isNotNull();
        assertThat(toUpdate).hasFieldOrPropertyWithValue("name", "Horror");
        assertThat(toUpdate.getIsActive()).isFalse();
    }
}
