package com.turkcell.rentacar.business.abstracts;

import com.turkcell.rentacar.business.dtos.CarGetDto;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.entities.concretes.Car;

import java.util.List;

public interface CarService {
	
	
    Result add(CreateCarRequest createCarRequest);
    DataResult<CarGetDto> getByCarId(int id);
    DataResult<List<CarListDto>> getAll();
    Result update(UpdateCarRequest updateCarRequest);
    Result delete(DeleteCarRequest deleteCarRequest);
    DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize);
    DataResult<List<CarListDto>> getAllSorted (String param);
    DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice);
    DataResult<List<CarListDto>> findByDailyPriceBetween (double since, double until);
}
