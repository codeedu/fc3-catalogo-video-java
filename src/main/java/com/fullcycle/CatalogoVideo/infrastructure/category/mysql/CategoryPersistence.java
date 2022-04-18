package com.fullcycle.CatalogoVideo.infrastructure.category.mysql;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fullcycle.CatalogoVideo.domain.category.Category;
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

    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime createdAt;
    
    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime updatedAt;
    
    @Column(columnDefinition = "DATETIME(6)")
    private LocalDateTime deletedAt;

    public static CategoryPersistence from(final Category category) {
        if (category == null) {
            throw new NotNullException();
        }

        return new CategoryPersistence(
            category.getId(), 
            category.getName(), 
            category.getDescription(), 
            category.getIsActive(),
            category.getCreatedAt(),
            category.getUpdatedAt(),
            category.getDeletedAt()
        );

    }

    public Category fromThis() {
        return new Category(
            getId(),
            getName(),
            getDescription(),
            getIsActive(),
            getCreatedAt(),
            getUpdatedAt(),
            getDeletedAt()
        );
    }
}
