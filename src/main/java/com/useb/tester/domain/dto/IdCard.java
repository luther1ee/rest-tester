package com.useb.tester.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class IdCard {
    @JsonProperty("name")
    private String name;
    @JsonProperty("jumin")
    private String jumin;
    @JsonProperty("issued_date")
    private String issuedDate;
    @JsonProperty("region")
    private String region;
    @JsonProperty("validation")
    private boolean validation;
    @JsonProperty("found_face")
    private float foundFace;
    @JsonProperty("face_recog_time")
    private int faceRecogTime;
    @JsonProperty("specular_ratio")
    private float specularRatio;
    @JsonProperty("passport")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Passport passport;
    @JsonProperty("resident_registration")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private ResidentRegistration residentRegistration;
    @JsonProperty("driver_license")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private DriverLicense driverLicense;

}
