package com.emazon.emazon_stock_api.infrastructure.input.rest;

import com.emazon.emazon_stock_api.application.dto.brand.BrandRequest;
import com.emazon.emazon_stock_api.application.handler.brand.BrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/brand/")
@RequiredArgsConstructor
public class BrandRestController {

    private final BrandHandler brandHandler;

    @PostMapping()
    public ResponseEntity<Void> saveBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.saveBrand(brandRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
