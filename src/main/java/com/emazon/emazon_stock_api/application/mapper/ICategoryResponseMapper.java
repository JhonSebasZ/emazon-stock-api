package com.emazon.emazon_stock_api.application.mapper;

import com.emazon.emazon_stock_api.application.dto.CategoryResponse;
import com.emazon.emazon_stock_api.domain.entities.Category;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ICategoryResponseMapper {
    CategoryResponse toCategoryResponse(Category category);

    default List<CategoryResponse> toCategoryList(List<Category> categoryList) {
        return categoryList.stream()
                .map(category -> {
                    CategoryResponse categoryResponse = new CategoryResponse();
                    categoryResponse.setId(category.getId());
                    categoryResponse.setName(category.getName());
                    categoryResponse.setDescription(category.getDescription());
                    return categoryResponse;
                }).toList();
    }
}
