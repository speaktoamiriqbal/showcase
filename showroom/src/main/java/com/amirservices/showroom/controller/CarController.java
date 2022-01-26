package com.amirservices.showroom.controller;

import com.amirservices.showroom.dto.CarIncomingDTO;
import com.amirservices.showroom.dto.CarOutgoingDTO;
import com.amirservices.showroom.util.CarConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {


    @Operation(summary = "Creates a car ")
    @ApiResponse(responseCode = "201", description = "Order is created",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> createCar(
            @Valid @RequestBody
                    CarIncomingDTO carIncomingDto){

        log.info(CarConstants.CREATE_CAR_ENTRY);

        CarOutgoingDTO carOutgoingDto = CarOutgoingDTO.builder().build();
        // todo:   call service here
        return ResponseEntity.status(HttpStatus.CREATED).body(carOutgoingDto);
    }


    @GetMapping(value = "/get/{registrationNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> loadCar(
            @NotEmpty
            @PathVariable(value = "registrationNumber" )
                    String carId) throws Exception {

        log.info(CarConstants.READ_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDto = null;
       try {

           carOutgoingDto = CarOutgoingDTO.
                   builder()
                   .registrationNumber(carId)
                   .description("Sample description for car " + carId)
                   .build();

           //int d = 2/0;

       }catch (Exception exp){
           throw new Exception("message from exp", exp);

       }

        // todo:   call service here
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDto);
    }


    @PutMapping(value = "/put", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> updateCar(
            @Valid
            @RequestBody
                    CarIncomingDTO carIncomingDto){

        log.info(CarConstants.UPDATE_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDto = CarOutgoingDTO.builder().build();
        // todo:   call service here
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDto);
    }



    @DeleteMapping(value = "/delete/{registrationNumber}")
    public ResponseEntity<CarOutgoingDTO> deleteCar(
            @NotEmpty
            @PathVariable (value = "registrationNumber")
                    String carId){

        log.info(CarConstants.DELETE_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDto = CarOutgoingDTO.builder().build();
        // todo:   call service here
        return ResponseEntity.status(HttpStatus.OK).body(carOutgoingDto);

    }

}
