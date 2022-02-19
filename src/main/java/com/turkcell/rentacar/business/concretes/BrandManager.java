package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.BrandService;
import com.turkcell.rentacar.business.dtos.BrandListDto;
import com.turkcell.rentacar.business.dtos.CarGetDto;
import com.turkcell.rentacar.business.dtos.GetBrandDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.CreateBrandRequest;
import com.turkcell.rentacar.business.requests.DeleteBrandRequest;
import com.turkcell.rentacar.business.requests.UpdateBrandRequest;
import com.turkcell.rentacar.core.exceptions.BrandAlreadyExistException;
import com.turkcell.rentacar.core.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.BrandDao;
import com.turkcell.rentacar.entities.concretes.Brand;
import com.turkcell.rentacar.entities.concretes.Car;
import com.turkcell.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandManager implements BrandService {

    private BrandDao brandDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public BrandManager(BrandDao brandDao,ModelMapperService modelMapperService) {
        this.brandDao = brandDao;
        this.modelMapperService = modelMapperService;
    }

    @Override
    public List<BrandListDto> getAll() {
        List<Brand> result = this.brandDao.findAll();
        return result.stream()
                .map(product -> this.modelMapperService
                        .forDto()
                        .map(product,BrandListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(CreateBrandRequest createBrandRequest) {
        if(this.brandDao.existsBrandByName(createBrandRequest.getName())) {
            throw new BrandAlreadyExistException("Bu id'de kayıtlı marka kayıtlı.");
        }else {
            Brand brand = this.modelMapperService
                    .forRequest().map(createBrandRequest,Brand.class);
            this.brandDao.save(brand);
        }
    }

    @Override
    public GetBrandDto getById(Integer id){
        Optional<Brand> brand = brandDao.findById(id);
        if(brand.isPresent()) {
            GetBrandDto response = this.modelMapperService.forDto().map(brand.get(), GetBrandDto.class);
            return response;
        }else {
            throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }

    @Override
    public void update(UpdateBrandRequest updateBrandRequest) {
        if (this.brandDao.findById(updateBrandRequest.getId()).isPresent()) {
            Brand brand = this.modelMapperService.forRequest().map(updateBrandRequest, Brand.class);
            brandDao.save(brand);
        }else {
            throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }

    @Override
    public void delete(DeleteBrandRequest deleteBrandRequest) {
        if (this.brandDao.findById(deleteBrandRequest.getId()).isPresent()) {
            brandDao.deleteById(deleteBrandRequest.getId());
        }else {
            throw new BrandNotFoundException("Bu id'de kayıtlı marka bulunamadı.");
        }
    }
}
