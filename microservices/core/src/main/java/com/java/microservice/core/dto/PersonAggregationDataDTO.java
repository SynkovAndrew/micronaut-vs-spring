package com.java.microservice.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonAggregationDataDTO {
    private Integer averageSalary;
    private Integer averageAge;
    private Gender gender;
    private Country country;
}
