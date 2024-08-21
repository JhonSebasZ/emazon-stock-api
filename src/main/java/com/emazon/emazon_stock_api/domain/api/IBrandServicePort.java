package com.emazon.emazon_stock_api.domain.api;

import com.emazon.emazon_stock_api.domain.models.Brand;

public interface IBrandServicePort {
    boolean existsNameBrand(String name);
    void saveBrand(Brand brand);
}
