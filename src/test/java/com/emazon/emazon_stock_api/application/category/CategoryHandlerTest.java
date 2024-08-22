package com.emazon.emazon_stock_api.application.category;

import com.emazon.emazon_stock_api.application.Exceptions.pagination.InvalidOrder;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.NumberPageNegativeException;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.WrongSizeException;
import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;
import com.emazon.emazon_stock_api.application.handler.category.CategoryHandler;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryRequestMapper;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryResponseMapper;
import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryHandlerTest {

    @Mock
    private ICategoryServicePort categoryServicePort;

    @Mock
    private ICategoryRequestMapper categoryRequestMapper;

    @Mock
    private ICategoryResponseMapper categoryResponseMapper;

    @InjectMocks
    private CategoryHandler categoryHandler;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory(){
        CategoryRequest request = new CategoryRequest(1L, "test", "test of handler");
        Category category = new Category(1L, "test", "test of handler");
        CategoryResponse response = new CategoryResponse(1L, "test", "test of handler");

        when(categoryRequestMapper.toCategory(any(CategoryRequest.class))).thenReturn(category);
        when(categoryServicePort.saveCategory(any(Category.class))).thenReturn(category);
        when(categoryResponseMapper.toCategoryResponse(any(Category.class))).thenReturn(response);

        CategoryResponse result = categoryHandler.saveCategory(request);

        assertEquals(response, result);
        verify(categoryRequestMapper, times(1)).toCategory(any(CategoryRequest.class));
        verify(categoryServicePort, times(1)).saveCategory(any(Category.class));
        verify(categoryResponseMapper, times(1)).toCategoryResponse(any(Category.class));
    }

    @Test
    void testListCategories() {
        PaginatedResult<Category> paginatedResult = new PaginatedResult<>(
                List.of(new Category(1L, "test", "test of handler")),
                0, 10, 1, 1, true);
        PageResponse<CategoryResponse> pageResponse = new PageResponse<>(
                List.of(new CategoryResponse(1L, "test", "test of handler")),
                0, 10, 1, 1, true);

        when(categoryServicePort.listCategories(0, 10, "asc", "name")).thenReturn(paginatedResult);
        when(categoryResponseMapper.toPageResponse(paginatedResult)).thenReturn(pageResponse);

        PageResponse<CategoryResponse> result = categoryHandler.listCategories(0, 10, "asc", "name");
        assertEquals(pageResponse, result);
        verify(categoryServicePort, times(1)).listCategories(0, 10, "asc", "name");
    }

    @Test
    void testValidateDataOfPaginationNumberPageNegative() {
        int negativePage = -1;
        int size = 10;
        String sortDirection = "asc";

        Exception exception = assertThrows(NumberPageNegativeException.class, () -> {
            categoryHandler.validateDataOfPagination(negativePage, size, sortDirection);
        });
        assertEquals("Page number cannot be negative", exception.getMessage());
    }

    @Test
    void testValidateDataOfPaginationWrongSize() {
        int page = 0;
        int invalidSize = 0;
        String sortDirection = "asc";

        Exception exception = assertThrows(WrongSizeException.class, () -> {
            categoryHandler.validateDataOfPagination(page, invalidSize, sortDirection);
        });
        assertEquals("Size must be greater than zero", exception.getMessage());
    }

    @Test
    void testValidateDataOfPaginationInvalidSortDirection() {
        int page = 0;
        int size = 10;
        String invalidSortDirection = "invalid";

        Exception exception = assertThrows(InvalidOrder.class, () -> {
            categoryHandler.validateDataOfPagination(page, size, invalidSortDirection);
        });
        assertEquals("Sort direction must be 'asc' or 'desc'", exception.getMessage());
    }

    @Test
    void testValidateDataOfPaginationValidInput() {
        int page = 0;
        int size = 10;
        String sortDirection = "asc";

        assertDoesNotThrow(() -> {
            categoryHandler.validateDataOfPagination(page, size, sortDirection);
        });
    }
}
