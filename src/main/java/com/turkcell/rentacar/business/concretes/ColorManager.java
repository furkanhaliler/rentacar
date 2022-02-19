package com.turkcell.rentacar.business.concretes;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.CreateColorRequest;
import com.turkcell.rentacar.business.requests.DeleteColorRequest;
import com.turkcell.rentacar.business.requests.UpdateColorRequest;
import com.turkcell.rentacar.core.exceptions.BrandNotFoundException;
import com.turkcell.rentacar.core.exceptions.ColorAlreadyExistException;
import com.turkcell.rentacar.core.exceptions.ColorNotfoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.dataAccess.abstracts.ColorDao;
import com.turkcell.rentacar.entities.concretes.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ColorManager implements ColorService {

    private ColorDao colorDao;
    private ModelMapperService modelMapperService;

    @Autowired
    public ColorManager(ColorDao colorDao,ModelMapperService modelMapperService) {
        this.colorDao = colorDao;
        this.modelMapperService=modelMapperService;
    }

    @Override
    public List<ColorListDto> getAll() {
        List<Color> result = this.colorDao.findAll();

        return result.stream()
                .map(product -> this.modelMapperService
                        .forDto()
                        .map(product,ColorListDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void add(CreateColorRequest createColorRequest) {
        if(this.colorDao.existsColorByName(createColorRequest.getName())) {
            throw new ColorAlreadyExistException("Aynı isimde renk kayıtlı");
        }else {
            Color color = this.modelMapperService
                    .forRequest().map(createColorRequest,Color.class);
            this.colorDao.save(color);
        }
    }

    @Override
    public GetColorDto getById(Integer id){
        if(this.colorDao.existsById(id)) {
            Color foundColor = colorDao.getById(id);
            return this.modelMapperService.forDto().map(foundColor, GetColorDto.class);
        }else {
            throw new ColorNotfoundException("Bu id'de renk bulunamadı");
        }
    }

    @Override
    public void update(UpdateColorRequest updateColorRequest) {
        if (this.colorDao.existsById(updateColorRequest.getId())) {
            Color color = this.modelMapperService.forRequest().map(updateColorRequest, Color.class);
            colorDao.save(color);
        }else {
            throw new ColorNotfoundException("Bu id'de kayıtlı renk bulunamadı.");
        }
    }

    @Override
    public void delete(DeleteColorRequest deleteColorRequest) {
        if (this.colorDao.existsById(deleteColorRequest.getId())) {
            colorDao.deleteById(deleteColorRequest.getId());
        }else {
            throw new BrandNotFoundException("Bu id'de kayıtlı renk bulunamadı.");
        }
    }
}
