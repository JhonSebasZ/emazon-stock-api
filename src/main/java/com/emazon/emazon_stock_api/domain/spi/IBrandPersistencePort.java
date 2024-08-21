package com.emazon.emazon_stock_api.domain.spi;

import com.emazon.emazon_stock_api.domain.models.Brand;

public interface IBrandPersistencePort {
    boolean existsNameBrand(String name);
    void saveBrand(Brand brand);
}
