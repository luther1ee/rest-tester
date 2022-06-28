package com.useb.tester.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class DriverLicense {
    @JsonProperty("driver_type")
    private String driverType;
    @JsonProperty("driver_number")
    private String driverNumber;
    @JsonProperty("driver_serial")
    private String driverSerial;
}
