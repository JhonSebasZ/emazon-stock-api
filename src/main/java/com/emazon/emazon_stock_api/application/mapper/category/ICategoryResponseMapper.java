package com.emazon.emazon_stock_api.application.mapper.category;

import com.emazon.emazon_stock_api.application.dto.category.CategoryResponse;
import com.emazon.emazon_stock_api.application.dto.pagination.PageResponse;
import com.emazon.emazon_stock_api.domain.models.Category;
import com.emazon.emazon_stock_api.domain.models.PaginatedResult;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);
    List<CategoryResponse> toCategoryResponseList(List<Category> categories);

    default PageResponse<CategoryResponse> toPageResponse(PaginatedResult<Category> paginatedResult) {
        List<CategoryResponse> categoryResponses = toCategoryResponseList(paginatedResult.getContent());

        return new PageResponse<>(
                categoryResponses,
                paginatedResult.getPage(),
                paginatedResult.getSize(),
                paginatedResult.getTotalElements(),
                paginatedResult.getTotalPages(),
                paginatedResult.isLast()
        );
    }
}
