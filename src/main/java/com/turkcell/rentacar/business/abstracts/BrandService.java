package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;


import java.util.List;

public interface BrandService {
    List<BrandListDto> getAll();
    void add(CreateBrandRequest brand);
    GetBrandDto getById(Integer id);
    void update(UpdateBrandRequest updateBrandRequest);
    void delete(DeleteBrandRequest deleteBrandRequest);
}
