package com.emazon.emazon_stock_api.infrastructure.output.jpa.adapter;

import com.emazon.emazon_stock_api.domain.models.Brand;
import com.emazon.emazon_stock_api.domain.spi.IBrandPersistencePort;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.emazon_stock_api.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {

    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;

    @Override
    public boolean existsNameBrand(String name) {
        if(brandRepository.findByName(name).isPresent()){
            return true;
        }
        return false;
    }

    @Override
    public void saveBrand(Brand brand) {
        existsNameBrand(brand.getName());
        brandRepository.save(brandEntityMapper.toBrandEntity(brand));
    }
}
