package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.ColorService;
import com.turkcell.rentacar.business.dtos.ColorListDto;
import com.turkcell.rentacar.business.dtos.GetColorDto;
import com.turkcell.rentacar.business.requests.*;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/colors")
public class ColorsController {

    private ColorService colorService;

    public ColorsController(ColorService colorService) {
        this.colorService = colorService;
    }

    @GetMapping("/getAll")
    public List<ColorListDto> getAll() {
        return colorService.getAll();
    }

    @PostMapping("/add")
    public void save(@RequestBody CreateColorRequest createColorRequest) {
        colorService.add(createColorRequest);
    }

    @GetMapping("/getById/{id}")
    public GetColorDto getColorById(@PathVariable Integer id) {
        return colorService.getById(id);
    }

    @PutMapping("/update")
    public void update(@RequestBody UpdateColorRequest updateColorRequest) {
        this.colorService.update(updateColorRequest);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody DeleteColorRequest deleteColorRequest) {
        this.colorService.delete(deleteColorRequest);
    }
}
