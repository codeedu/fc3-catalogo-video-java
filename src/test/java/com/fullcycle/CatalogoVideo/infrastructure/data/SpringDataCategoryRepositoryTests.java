package com.fullcycle.CatalogoVideo.infrastructure.data;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import com.fullcycle.CatalogoVideo.infrastructure.persistence.CategoryPersistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SpringDataCategoryRepositoryTests {
 
    @Autowired
    private SpringDataCategoryRepository repository;


    @BeforeEach
    void initEach() {
        repository.deleteAll();
    }

    @Test
    public void saveCategory() {
        CategoryPersistence input = new CategoryPersistence();
        input.setName("Action");
        input.setDescription("Action Description");
        input.setIsActive(true);

        CategoryPersistence actual = repository.save(input);

        assertThat(actual).isNotNull();
        assertThat(actual).hasFieldOrPropertyWithValue("name", "Action");
        assertThat(actual.getIsActive()).isTrue();
    }

    @Test
    public void findAllCategoriesAndReturnTwoCategories() {
        CategoryPersistence entity1 = new CategoryPersistence();
        entity1.setName("Action");
        entity1.setDescription("Action Description");
        entity1.setIsActive(true);
        CategoryPersistence entity2 = new CategoryPersistence();
        entity2.setName("Horro");
        entity2.setDescription("Horro Description");
        entity2.setIsActive(true);    
        
        repository.saveAll(Arrays.asList(entity1, entity2));

        List<CategoryPersistence> actual = repository.findAll();

        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(2);
    }

    @Test
    public void findByIdCategory() {
        CategoryPersistence entity = new CategoryPersistence();
        entity.setName("Action");
        entity.setDescription("Action Description");
        entity.setIsActive(true);  
        
        CategoryPersistence saved = repository.save(entity);

        Optional<CategoryPersistence> actual = repository.findById(saved.getId());

        assertThat(actual.isPresent()).isTrue();    
        assertThat(actual).isNotNull();    
    }

    @Test
    public void findAllAndIsEmpty() {
        List<CategoryPersistence> actual = repository.findAll();
        
        assertThat(actual).isNotNull();
        assertThat(actual).hasSize(0);
    }

    @AfterAll
    void initAfterAll() {
        repository.deleteAll();
    }
}
