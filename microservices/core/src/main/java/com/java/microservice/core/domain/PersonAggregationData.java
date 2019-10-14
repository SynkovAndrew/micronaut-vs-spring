package com.java.microservice.core.domain;

import com.java.microservice.core.dto.Country;
import com.java.microservice.core.dto.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonAggregationData {
    private Double averageSalary;
    private Double averageAge;
    private Gender gender;
    private Country country;
}
