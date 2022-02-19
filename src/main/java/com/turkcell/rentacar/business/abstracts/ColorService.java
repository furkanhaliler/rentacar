package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.*;
import com.turkcell.rentacar.entities.concretes.Color;

import java.util.List;

public interface ColorService {
    List<ColorListDto> getAll();
    void add(CreateColorRequest createColorRequest);
    GetColorDto getById(Integer id);
    void update(UpdateColorRequest updateColorRequest);
    void delete(DeleteColorRequest deleteColorRequest);
    
}
