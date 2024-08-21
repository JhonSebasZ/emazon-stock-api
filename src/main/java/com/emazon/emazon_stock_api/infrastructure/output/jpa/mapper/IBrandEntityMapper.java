package com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper;

import com.emazon.emazon_stock_api.domain.models.Brand;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
}
