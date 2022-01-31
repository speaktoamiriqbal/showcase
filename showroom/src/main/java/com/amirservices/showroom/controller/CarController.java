package com.amirservices.showroom.controller;

import com.amirservices.showroom.dto.CarIncomingDTO;
import com.amirservices.showroom.dto.CarOutgoingDTO;
import com.amirservices.showroom.service.CarService;
import com.amirservices.showroom.util.CarConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    CarService carService;


    @Operation(summary = "Saves a car ")
    @ApiResponse(responseCode = "201", description = "Car is saved",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> createCar(
            @RequestBody
                    CarIncomingDTO carIncomingDto) throws Exception{

        log.info(CarConstants.CREATE_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDTO = carService.persistCar(carIncomingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(carOutgoingDTO);
    }


    @Operation(summary = "Search a car by registration")
    @ApiResponse(responseCode = "201", description = "Car is searched by registration",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})
    @GetMapping(value = "/get/{registrationNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> loadCar(
            @NotEmpty
            @PathVariable(value = "registrationNumber" )
                    String registrationNumber) throws Exception {

        log.info(CarConstants.READ_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDto = null;
        carOutgoingDto = carService.getCarByRegistrationNumber(registrationNumber);
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDto);
    }



    @Operation(summary = "Modify an existing car ")
    @ApiResponse(responseCode = "201", description = "Existing Car is modified",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})
    @PutMapping(value = "/put", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> updateCar (
            @RequestBody
                    CarIncomingDTO carIncomingDto) throws Exception{

        log.info(CarConstants.UPDATE_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDTO = carService.updateCar(carIncomingDto);
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDTO);
    }


    @Operation(summary = "Removes a car ")
    @ApiResponse(responseCode = "201", description = "Car is removed",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})
    @DeleteMapping(value = "/delete/{registrationNumber}")
    public ResponseEntity<CarOutgoingDTO> deleteCar(
            @NotEmpty
            @PathVariable (value = "registrationNumber")
                    String carId) throws Exception{

        log.info(CarConstants.DELETE_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDTO = carService.removeCar(carId);
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDTO);

    }

}
