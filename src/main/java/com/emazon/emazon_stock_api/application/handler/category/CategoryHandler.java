package com.emazon.emazon_stock_api.application.handler.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryRequestMapper;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryResponseMapper;
import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.models.Category;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoryHandler implements ICategoryHandler {

    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;

    @Override
    public CategoryResponse saveCategory(CategoryRequest categoryRequest) {
        String cleanName = categoryRequest.getName().trim();
        String cleanDescription = categoryRequest.getDescription().trim();

        Category category = categoryRequestMapper.toCategory(new CategoryRequest(categoryRequest.getId(), cleanName, cleanDescription));
        return categoryResponseMapper.toCategoryResponse(categoryServicePort.saveCategory(category));
    }
}
