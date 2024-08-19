package com.emazon.emazon_stock_api.domain.usecase;

import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.entities.Category;
import com.emazon.emazon_stock_api.domain.exceptions.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;

import java.util.List;

public class CategoryUseCase implements ICategoryServicePort {

    private final ICategoryPersistencePort categoryPersistencePort;
    private int MaxLengthName = 50;
    private int MaxLengthDescription = 90;

    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    @Override
    public boolean existsByName(String name) {
        return categoryPersistencePort.existsByName(name);
    }

    @Override
    public Category saveCategory(Category category) {
        validateCategory(category.getName(), category.getDescription());
        return categoryPersistencePort.saveCategory(category);
    }

    public void validateCategory(String name, String description) {
        validateName(name);
        validateDescription(description);
    }

    public void validateName(String name) {
        if (name == null || name.isEmpty()) {
            throw new InvalidCategoryNameException("El nombre de la categoría no puede estar vacío.");
        }
        if (name.length() > MaxLengthName) {
            throw new InvalidCategoryNameException("El nombre de la categoría no puede tener más de 50 caracteres.");
        }
    }

    public void validateDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new InvalidCategoryDescriptionException("La descripción de la categoría es obligatoria.");
        }
        if (description.length() > MaxLengthDescription) {
            throw new InvalidCategoryDescriptionException("La descripción de la categoría no puede tener más de 90 caracteres.");
        }
    }
}
