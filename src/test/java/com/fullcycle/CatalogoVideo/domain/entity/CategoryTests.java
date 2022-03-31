package com.fullcycle.CatalogoVideo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fullcycle.CatalogoVideo.domain.exception.DomainException;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CategoryTests {
    
    @Test
    public void throwDomainExceptionWhenNameIsNull() {
        assertThrows(DomainException.class, () -> new Category(null, "Description"));
    }

    @Test
    public void throwDomainExceptionWhenNameIsBlank() {
        assertThrows(DomainException.class, () -> new Category("", "Description"));
    }

    @Test
    public void createCategoryWithNameAndDescriptionIsNull() {
        final Category entity = new Category(
            "Name 1",
            null
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertNull(entity.getDescription());
    }

    @Test
    public void createCategoryWithNameAndDescription() {
        final Category entity = new Category(
            "Name 1",
            "Description 2"
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertEquals(entity.getDescription(), "Description 2");
    }    

    @Test
    public void createCategoryAndIsActiveTrue() {
        final Category entity = new Category(
            "Name 1",
            "Description 2"
        );

        assertNotNull(entity);
        assertTrue(entity.getIsActive());        
    }

    @Test
    public void createCategoryAndIsDeactivate() {
        final Category entity = new Category(
            "Name 1",
            "Description 2"
        );

        entity.deactivate();
        assertNotNull(entity);
        assertFalse(entity.getIsActive());        
    }

    @Test
    public void createCategoryAndUpdate() {
        final Category entity = new Category(
            "Name 1",
            "Description 2"
        );

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 1");
        assertEquals(entity.getDescription(), "Description 2");
        assertTrue(entity.getIsActive());        

        entity.update("Name 2", "Desc", false);

        assertNotNull(entity);
        assertEquals(entity.getName(), "Name 2");
        assertEquals(entity.getDescription(), "Desc");        
        assertFalse(entity.getIsActive());        
    }    
}
