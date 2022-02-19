package com.turkcell.rentacar.business.requests;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetColorRequest {
    private int id;
    private String name;
}
