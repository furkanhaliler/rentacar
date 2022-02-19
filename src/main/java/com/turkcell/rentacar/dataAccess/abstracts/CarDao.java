package com.turkcell.rentacar.dataAccess.abstracts;

import com.turkcell.rentacar.business.dtos.CarListDto;
import com.turkcell.rentacar.core.utilities.results.DataResult;
import com.turkcell.rentacar.entities.concretes.Car;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarDao extends JpaRepository<Car,Integer> {
    boolean existsCarById(Integer id);
    List<Car> findByDailyPriceLessThan(double requestedPrice);
    List<Car> findByDailyPriceBetween(double maxValue, double minValue);
}
