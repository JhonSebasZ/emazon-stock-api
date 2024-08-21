package com.emazon.emazon_stock_api.application.handler.brand;

import com.emazon.emazon_stock_api.application.dto.brand.BrandRequest;
import com.emazon.emazon_stock_api.application.mapper.Brand.IBrandRequestMapper;
import com.emazon.emazon_stock_api.application.mapper.Brand.IBrandResponseMapper;
import com.emazon.emazon_stock_api.domain.api.IBrandServicePort;
import com.emazon.emazon_stock_api.domain.models.Brand;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{

    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;

    @Override
    public void saveBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.saveBrand(brand);
    }
}
