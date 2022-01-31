package com.amirservices.showroom.service;

import com.amirservices.showroom.dto.CarIncomingDTO;
import com.amirservices.showroom.dto.CarMapper;
import com.amirservices.showroom.dto.CarOutgoingDTO;
import com.amirservices.showroom.model.Car;
import com.amirservices.showroom.repository.CarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public CarOutgoingDTO persistCar(CarIncomingDTO incomingDTO){

        Car car = carRepository.findCarsByRegistrationId(incomingDTO.getRegistrationNumber());
        if (car !=  null){
            log.warn("Car with same Registration: {} already exist, therefore aborting operation.", car.getRegistrationId());
            return  CarMapper.INSTANCE.entityToOutgoingDto(car);
        }

        car = CarMapper.INSTANCE.incomingDtoToEntity(incomingDTO);
        car = carRepository.save(car);

        CarOutgoingDTO carOutgoingDTO = CarMapper.INSTANCE.entityToOutgoingDto(car);
        return carOutgoingDTO;
    }

    public CarOutgoingDTO getCarByRegistrationNumber(String registrationId){
        Car car = carRepository.findCarsByRegistrationId(registrationId);
        return  null;

    }

    public CarOutgoingDTO updateCar(CarIncomingDTO incomingDTO){
        Car existingCar = carRepository.findCarsByRegistrationId(incomingDTO.getRegistrationNumber());
        if (existingCar == null) {
            log.warn("Car with same Registration: {} does not exist, therefore aborting operation.", existingCar.getRegistrationId());
            return null;
        }

        Car requestCar = CarMapper.INSTANCE.incomingDtoToEntity(incomingDTO);
        requestCar.setId(existingCar.getId());
        requestCar.getOwner().setId(existingCar.getOwner().getId());
        requestCar.getManufacturingDetail().setId(existingCar.getManufacturingDetail().getId());

        Car updatedCar = carRepository.save(requestCar);
        CarOutgoingDTO carOutgoingDTO = CarMapper.INSTANCE.entityToOutgoingDto(updatedCar);
        return carOutgoingDTO;

    }

    public CarOutgoingDTO removeCar(String registrationId){
        Car car = carRepository.findCarsByRegistrationId(registrationId);
        if (car == null){
            log.warn("Car with same Registration: {} does not exist, therefore aborting operation.", car.getRegistrationId());
            return null;
        }
        carRepository.delete(car);

        CarOutgoingDTO carOutgoingDTO = CarMapper.INSTANCE.entityToOutgoingDto(car);
        return carOutgoingDTO;
    }





}
