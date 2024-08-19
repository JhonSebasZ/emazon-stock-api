package com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper;

import com.emazon.emazon_stock_api.domain.entities.Category;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryEntityMapper {

    CategoryEntity toCategoryEntity(Category category);
}
