package com.turkcell.rentacar.business.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarGetDto {
    private int id;
    private int dailyPrice;
    private int modelYear;
    private String description;
    private String brandName;
    private String colorName;
}
