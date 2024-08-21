package com.emazon.emazon_stock_api.infrastructure.input.rest;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.handler.category.CategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category/")
@RequiredArgsConstructor
public class CategoryRestController {

    private final CategoryHandler categoryHandler;

    @PostMapping()
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryHandler.saveCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoryResponse);
    }
}
