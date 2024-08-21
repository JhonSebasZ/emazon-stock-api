package com.emazon.emazon_stock_api.domain;

import com.emazon.emazon_stock_api.domain.exceptions.category.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.category.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategorySuccess() {
        Category category = new Category(1L, "clothes", "men's clothing");
        when(categoryPersistencePort.existsByName("clothes")).thenReturn(false);
        when(categoryPersistencePort.saveCategory(category)).thenReturn(category);

        Category savedCategory = categoryUseCase.saveCategory(category);

        assertEquals(category, savedCategory);
        verify(categoryPersistencePort, times(1)).existsByName("clothes");
        verify(categoryPersistencePort, times(1)).saveCategory(category);
    }

    @Test
    public void testSaveCategoryAlreadyExists() {
        Category category = new Category(2L, "phone", "high end phones");
        when(categoryPersistencePort.existsByName("phone")).thenReturn(true);
        assertThrows(CategoryAlreadyExistsException.class, () -> categoryUseCase.saveCategory(category));
        verify(categoryPersistencePort, times(1)).existsByName("phone");
        verify(categoryPersistencePort, times(0)).saveCategory(category);
    }

    @Test
    public void testValidateNameWhenNameIsNull() {
        assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.validateName(null);
        });
    }

    @Test
    public void testValidateNameWhenNameIsTooLong() {
        String longName = "A".repeat(51);
        assertThrows(InvalidCategoryNameException.class, () -> {
            categoryUseCase.validateName(longName);
        });
    }

    @Test
    void testValidateDescriptionWhenDescriptionIsNull() {
        assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.validateDescription(null);
        });
    }

    @Test
    void testValidateDescriptionWhenDescriptionIsTooLong() {
        String longDescription = "A".repeat(91);
        assertThrows(InvalidCategoryDescriptionException.class, () -> {
            categoryUseCase.validateDescription(longDescription);
        });
    }
}
