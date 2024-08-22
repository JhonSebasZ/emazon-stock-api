package com.emazon.emazon_stock_api.infrastructure.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;
import com.emazon.emazon_stock_api.application.handler.category.CategoryHandler;
import com.emazon.emazon_stock_api.infrastructure.input.rest.CategoryRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryRestControllerTest {

    @Mock
    private CategoryHandler categoryHandler;

    @InjectMocks
    private CategoryRestController categoryRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCategory() {
        CategoryRequest request = new CategoryRequest(1L, "test", "test of handler");
        CategoryResponse response = new CategoryResponse(1L, "test", "test of handler");
        when(categoryHandler.saveCategory(request)).thenReturn(response);

        ResponseEntity<CategoryResponse> result = categoryRestController.saveCategory(request);
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    void testListCategories() {
        PageResponse<CategoryResponse> pageResponse = new PageResponse<>(List.of(new CategoryResponse(1L, "test", "test of handler")), 0, 10, 1, 1, true);
        when(categoryHandler.listCategories(0, 10, "asc", "name")).thenReturn(pageResponse);

        ResponseEntity<PageResponse<CategoryResponse>> result = categoryRestController.getAllCategory(0, 10, "asc");
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(pageResponse, result.getBody());
    }
}
