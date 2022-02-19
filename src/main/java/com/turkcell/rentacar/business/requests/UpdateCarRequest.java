package com.turkcell.rentacar.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCarRequest {

    private int id;
    private int dailyPrice;
    private int modelYear;
    private String description;
    private int brandId;
    private int colorId;

}
