package com.emazon.emazon_stock_api.domain;

import com.emazon.emazon_stock_api.domain.exceptions.InvalidCategoryDescriptionException;
import com.emazon.emazon_stock_api.domain.exceptions.InvalidCategoryNameException;
import com.emazon.emazon_stock_api.domain.spi.ICategoryPersistencePort;
import com.emazon.emazon_stock_api.domain.usecase.CategoryUseCase;
import com.emazon.emazon_stock_api.infrastructure.exception.CategoryAlreadyExistsException;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter.CategoryJpaAdapter;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.ICategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class CategoryUseCaseTest {

    @Mock
    private ICategoryPersistencePort categoryPersistencePort;

    @Mock
    private final ICategoryRepository categoryRepository;

    private CategoryUseCase categoryUseCase;
    private CategoryJpaAdapter categoryJpaAdapter;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        categoryUseCase = new CategoryUseCase(categoryPersistencePort);
    }

    @Test
    public void shouldThrowInvalidCategoryNameExceptionWhenNameIsEmpty() {
        assertThrows(InvalidCategoryNameException.class, () -> categoryUseCase.validateName(""));
    }

    @Test
    public void shouldThrowInvalidCategoryNameExceptionWhenNameIsTooLong() {
        String longName = "This is a very long category name that exceeds the limit";
        assertThrows(InvalidCategoryNameException.class, () -> categoryUseCase.validateName(longName));
    }

    @Test
    public void shouldThrowInvalidCategoryDescriptionExceptionWhenDescriptionIsEmpty() {
        assertThrows(InvalidCategoryDescriptionException.class, () -> categoryUseCase.validateDescription(""));
    }

    @Test
    public void shouldThrowInvalidCategoryDescriptionExceptionWhenDescriptionIsTooLong() {
        String longDescription = "This is a very long category description that exceeds the character limit allowed for descriptions";
        assertThrows(InvalidCategoryDescriptionException.class, () -> categoryUseCase.validateDescription(longDescription));
    }
}
