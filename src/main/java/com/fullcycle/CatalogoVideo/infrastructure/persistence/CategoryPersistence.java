package com.fullcycle.CatalogoVideo.infrastructure.persistence;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fullcycle.CatalogoVideo.domain.entity.Category;
import com.fullcycle.CatalogoVideo.domain.exception.NotNullException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryPersistence {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;

    @Column
    @NotNull(message = "name can not be null")
    @NotBlank(message = "name can not be blank")
    private String name;
    
    @Column
    private String description;
    
    @Column
    private Boolean isActive = true;

    public static CategoryPersistence from(Category category) {
        if (category == null) {
            throw new NotNullException();
        }

        return new CategoryPersistence(
            category.getId(), 
            category.getName(), 
            category.getDescription(), 
            category.getIsActive()
        );

    }

    public Category fromThis() {
        return new Category(
            getId(),
            getName(),
            getDescription(),
            getIsActive()
        );
    }
}
