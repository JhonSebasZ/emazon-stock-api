package com.emazon.emazon_stock_api.application.handler.brand;

import com.emazon.emazon_stock_api.application.dto.brand.BrandRequest;

public interface IBrandHandler {
    void saveBrand(BrandRequest brandRequest);
}
