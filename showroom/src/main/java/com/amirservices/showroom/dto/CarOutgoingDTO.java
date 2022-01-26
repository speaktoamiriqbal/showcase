package com.amirservices.showroom.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder
public class CarOutgoingDTO {

    @Max(250)
    private String description;

    @NotNull
    private String registrationNumber;

    @Max(7)
    private String currentOwnerType;

    @Size(min = 0, max = 50)
    private String currentOwnerName;

    @Email
    private String currentOwnerEmail;

    @PositiveOrZero
    @Max(15)
    private Long currentOwnerTelephone;

    @Max(250)
    private String currentOwnerAddress;

    @PositiveOrZero
    private Integer manufacturingDate;

    @PastOrPresent
    private Date lastRegistrationDate;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotBlank
    private String bodyType;





}
