package com.emazon.emazon_stock_api.application.handler.category;

import com.emazon.emazon_stock_api.application.Exceptions.pagination.InvalidOrder;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.NumberPageNegativeException;
import com.emazon.emazon_stock_api.application.Exceptions.pagination.WrongSizeException;
import com.emazon.emazon_stock_api.application.dto.category.CategoryRequest;
import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryRequestMapper;
import com.emazon.emazon_stock_api.application.mapper.category.ICategoryResponseMapper;
import com.emazon.emazon_stock_api.domain.api.ICategoryServicePort;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public PageResponse<CategoryResponse> listCategories(int page, int size, String sortDirection, String sortBy) {
        validateDataOfPagination(page, size, sortDirection);
        PaginatedResult<Category> paginatedResult = categoryServicePort.listCategories(page, size, sortDirection, sortBy);
        return categoryResponseMapper.toPageResponse(paginatedResult);
    }

    public void validateDataOfPagination(int page, int size, String sortDirection){
        if(page < 0){
            throw new NumberPageNegativeException("Page number cannot be negative");
        }
        if(size <= 0){
            throw new WrongSizeException("Size must be greater than zero");
        }
        if(!sortDirection.equalsIgnoreCase("asc") && !sortDirection.equalsIgnoreCase("desc")){
            throw new InvalidOrder("Sort direction must be 'asc' or 'desc'");
        }
    }
}
