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
public class Passport {
    @JsonProperty("name_kor")
    private String nameKor;

    @JsonProperty("passport_number")
    private String passportNumber;

    @JsonProperty("expiry_date")
    private String expiryDate;

    @JsonProperty("sex")
    private String sex;

    @JsonProperty("passportType")
    private String passportType;

    @JsonProperty("birthday")
    private String birthday;

    @JsonProperty("personal_number")
    private String personalNumber;

    @JsonProperty("issuing_country")
    private String issuingCountry;

    @JsonProperty("nationality")
    private String nationality;

    @JsonProperty("sur_name")
    private String surName;

    @JsonProperty("given_name")
    private String givenName;
}
