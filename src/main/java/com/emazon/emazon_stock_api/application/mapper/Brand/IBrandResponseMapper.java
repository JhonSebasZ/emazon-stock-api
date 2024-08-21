package com.emazon.emazon_stock_api.application.mapper.Brand;

import com.emazon.emazon_stock_api.application.dto.brand.BrandResponse;
import com.emazon.emazon_stock_api.domain.models.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}
