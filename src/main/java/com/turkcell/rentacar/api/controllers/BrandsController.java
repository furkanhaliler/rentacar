package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandsController {

    private BrandService brandService;

    @Autowired
    public BrandsController(BrandService brandService) {
        this.brandService = brandService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateBrandRequest createBrandRequest) {
        try {
            this.brandService.add(createBrandRequest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getall")
    public List<BrandListDto> getAll() {
        return this.brandService.getAll();
    }

    @GetMapping("/getByBrandId/{brandId}")
    public GetBrandDto getByBrandId(@RequestParam("brandId") int brandId) {
        return this.brandService.getById(brandId);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateBrandRequest updateBrandRequest) {
        this.brandService.update(updateBrandRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteBrandRequest deleteBrandRequest) {
        this.brandService.delete(deleteBrandRequest);
    }
}
