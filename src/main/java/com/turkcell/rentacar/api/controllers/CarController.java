package com.turkcell.rentacar.api.controllers;

import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.CarGetDto;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;

import org.aspectj.weaver.ast.FieldGetCall;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping("/add")
    public void add(@RequestBody CreateCarRequest createCarRequest) {
        try {
            this.carService.add(createCarRequest);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/getall")
    public DataResult<List<CarListDto>> getAll() {
        return this.carService.getAll();
    }

    @GetMapping("/getByCarId/{carId}")
    public DataResult<CarGetDto> getByCarId(@PathVariable("carId") Integer carId) {
        return this.carService.getByCarId(carId);
    }

    @PutMapping("/update")
    public Result update(@RequestBody UpdateCarRequest updateCarRequest) {
    	return this.carService.update(updateCarRequest);
        
    }

    @DeleteMapping("/delete")
    public Result delete(@RequestBody DeleteCarRequest deleteCarRequest) {
        return this.carService.delete(deleteCarRequest);
    }
    
    @GetMapping("/getAllPaged/{pageNo}/{pageSize}")
    DataResult<List<CarListDto>> getAllPaged(@PathVariable("pageNo") int pageNo, @PathVariable("pageSize") int pageSize){
    	
    	return this.carService.getAllPaged(pageNo, pageSize);
    	
    }
    
    @GetMapping("/getAllSorted/{orderOfSort}")
    DataResult<List<CarListDto>> getAllSorted (@RequestParam("orderOfSort") String param){
    	
    	return this.carService.getAllSorted(param);
    }
    
    @GetMapping("/findByDailyPriceLessThan/{requestedPrice}")
    DataResult<List<CarListDto>> findByDailyPriceLessThan(@PathVariable("requestedPrice") double requestedPrice){
    	
    	return this.carService.findByDailyPriceLessThan(requestedPrice);
    	
    }
    
    @GetMapping("/findByDailyPriceBetween/{since}/{until}")
    DataResult<List<CarListDto>> findByDailyPriceBetween (@PathVariable("since") double since, @PathVariable("until") double until){
    	
    	return this.carService.findByDailyPriceBetween(since, until);
    }
}
