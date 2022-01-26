package com.amirservices.showroom.service;

import com.amirservices.showroom.dto.CarIncomingDTO;
import com.amirservices.showroom.dto.CarMapper;
import com.amirservices.showroom.dto.CarOutgoingDTO;
import com.amirservices.showroom.model.Car;
import com.amirservices.showroom.model.ManufacturingDetail;
import com.amirservices.showroom.model.Owner;
import com.amirservices.showroom.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public Car persistCar(CarIncomingDTO incomingDTO){

        Car car = Car.builder()
                .registrationDate(new Date())
                .registrationId("LEM1375")
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

        return carRepository.save(car);

    }

    public CarOutgoingDTO getCarByRegistrationNumber(String registrationId){
        Car car = carRepository.findCarsByRegistrationId(registrationId);
        return  CarMapper.performEntityToDTOMapping(car);

    }





}
