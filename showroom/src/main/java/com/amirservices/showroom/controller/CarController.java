package com.amirservices.showroom.controller;

import com.amirservices.showroom.dto.CarIncomingDTO;
import com.amirservices.showroom.dto.CarMapper;
import com.amirservices.showroom.dto.CarOutgoingDTO;
import com.amirservices.showroom.model.Car;
import com.amirservices.showroom.model.ManufacturingDetail;
import com.amirservices.showroom.model.Owner;
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

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;


@Slf4j
@RestController
@RequestMapping(path = "/api/v1/cars", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {

    @Autowired
    CarService carService;


    @Operation(summary = "Creates a car ")
    @ApiResponse(responseCode = "201", description = "Order is created",
            content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = CarOutgoingDTO.class))})

    @PostMapping(value = "/create",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> createCar(
            @RequestBody
                    CarIncomingDTO carIncomingDto){

        log.info(CarConstants.CREATE_CAR_ENTRY);


        Car car = carService.persistCar(carIncomingDto);
        CarOutgoingDTO carOutgoingDTO = CarMapper.performEntityToDTOMapping(car);
        // todo:   call service here
        return ResponseEntity.status(HttpStatus.CREATED).body(carOutgoingDTO);
    }


    @GetMapping(value = "/get/{registrationNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarOutgoingDTO> loadCar(
            @NotEmpty
            @PathVariable(value = "registrationNumber" )
                    String registrationNumber) throws Exception {

        log.info(CarConstants.READ_CAR_ENTRY);
        CarOutgoingDTO carOutgoingDto = null;
       try {

           /* Car car = Car.builder()
                    .registrationDate(new Date())
                    .registrationId("LEM-1375")
                    .owner(
                            Owner.builder()
                                    .fullName("Amir Iqbal")
                                    .telephone(1234567L)
                                    .email("ai@abc.com")
                                    .fullAddress("my home address")
                                    .type('P')
                                    .build()
                    )
                    .manufacturingDetail(
                            ManufacturingDetail.builder()
                                    .brand("toyota")
                                    .model("vitz")
                                    .type("hatchback")
                                    .makeYear(2022)
                                    .build()
                    ).build();

           carOutgoingDto = CarMapper.performEntityToDTOMapping(car);*/
           carOutgoingDto = carService.getCarByRegistrationNumber(registrationNumber);

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
