package com.turkcell.rentacar.business.concretes;


import com.turkcell.rentacar.business.abstracts.CarService;
import com.turkcell.rentacar.business.dtos.CarGetDto;
import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.business.requests.CreateCarRequest;
import com.turkcell.rentacar.business.requests.DeleteCarRequest;
import com.turkcell.rentacar.business.requests.UpdateCarRequest;
import com.turkcell.rentacar.core.exceptions.CarNotFoundException;
import com.turkcell.rentacar.core.utilities.mapping.ModelMapperService;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorDataResult;
import com.turkcell.rentacar.core.utilities.results.ErrorResult;
import com.turkcell.rentacar.core.utilities.results.Result;
import com.turkcell.rentacar.core.utilities.results.SuccessDataResult;
import com.turkcell.rentacar.core.utilities.results.SuccessResult;
import com.turkcell.rentacar.dataAccess.abstracts.CarDao;
import com.turkcell.rentacar.entities.concretes.Car;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarManager implements CarService {

	private CarDao carDao;
	private ModelMapperService modelMapperService;

	public CarManager(CarDao carDao, ModelMapperService modelMapperService) {
		this.carDao = carDao;
		this.modelMapperService = modelMapperService;
	}

	@Override
	public Result add(CreateCarRequest createCarRequest) {
		Car car = this.modelMapperService.forRequest().map(createCarRequest, Car.class);
		this.carDao.save(car);
		return new SuccessResult();
	}

	@Override
	public DataResult<CarGetDto> getByCarId(int id) {
		if (carDao.existsCarById(id)) {
			Car car = carDao.getById(id);
			CarGetDto response = this.modelMapperService.forDto().map(car, CarGetDto.class);
			return new SuccessDataResult<CarGetDto>(response, "Id'ye göre listelendi");
		} else {
			return new ErrorDataResult("Bu Id'de araç bulunamadı");
		}
	}

	@Override
	public DataResult<List<CarListDto>> getAll() {
		List<Car> result = this.carDao.findAll();
		List<CarListDto> response = result.stream()
				.map(color -> this.modelMapperService.forDto().map(color, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response, "Veriler listelendi.");
	}

	@Override
	public Result update(UpdateCarRequest updateCarRequest) {
		if (carDao.existsCarById(updateCarRequest.getId())) {
			Car car = this.modelMapperService.forRequest().map(updateCarRequest, Car.class);
			this.carDao.save(car);
			return new SuccessResult("Araba güncellendi.");
		} else {
			return new ErrorResult("Araba bulunamadı");
		}
	}

	@Override
	public Result delete(DeleteCarRequest deleteCarRequest) {
		if (this.carDao.existsCarById(deleteCarRequest.getId())) {
			this.carDao.deleteById(deleteCarRequest.getId());
			return new SuccessResult("Araba silindi.");
		} else {
			return new ErrorResult("Araba bulunamadı");
		}

	}

	@Override
	public DataResult<List<CarListDto>> getAllPaged(int pageNo, int pageSize) {

		Pageable pageable = PageRequest.of(pageNo - 1, pageSize);

		List<Car> result = this.carDao.findAll(pageable).getContent();

		List<CarListDto> response = result.stream()
				.map(product -> this.modelMapperService.forDto().map(product, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public DataResult<List<CarListDto>> getAllSorted(String param) {

		Sort sort;
		
		if(param.toUpperCase().equals("ASC")) {
			sort = Sort.by(Sort.Direction.ASC, "dailyPrice");
		}else if(param.toUpperCase().equals("DESC")) {
			sort = Sort.by(Sort.Direction.DESC, "dailyPrice");
		}else {
			return new ErrorDataResult<List<CarListDto>>("Düzgün parametre giriniz. ASC veya DESC");
		}

		List<Car> result = this.carDao.findAll(sort);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceLessThan(double requestedPrice) {

		List<Car> result = this.carDao.findByDailyPriceLessThan(requestedPrice);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
	}

	@Override
	public DataResult<List<CarListDto>> findByDailyPriceBetween(double since, double until) {
		
		List<Car> result = this.carDao.findByDailyPriceBetween(since, until);

		List<CarListDto> response = result.stream()
				.map(car -> this.modelMapperService.forDto().map(car, CarListDto.class))
				.collect(Collectors.toList());
		return new SuccessDataResult<List<CarListDto>>(response);
		
	}

}
