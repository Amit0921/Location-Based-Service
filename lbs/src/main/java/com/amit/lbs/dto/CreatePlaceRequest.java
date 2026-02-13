package com.amit.lbs.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreatePlaceRequest {

    @NotBlank
    private String name;

    private String type;

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;
}
