package com.emazon.emazon_stock_api.application.handler;

import com.emazon.emazon_stock_api.application.dto.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.CategoryResponse;
import com.emazon.emazon_stock_api.application.mapper.ICategoryRequestMapper;
import com.emazon.emazon_stock_api.application.mapper.ICategoryResponseMapper;
import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.entities.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler{

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public void saveCategory(CategoryRequest categoryRequest) {
        Category category = categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.saveCategory(category);
    }
}
