package com.amirservices.showroom.repository;


import com.amirservices.showroom.model.Car;
import com.amirservices.showroom.model.ManufacturingDetail;
import com.amirservices.showroom.model.Owner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    CarRepository underTest;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findCarsByRegistrationId() {
        //given
        String registrationNumber = "LEM-1375";
        Car carToSave = Car.builder()
                .registrationId(registrationNumber)
                .manufacturingDetail(new ManufacturingDetail())
                .owner(new Owner())
                .build();
        underTest.save(carToSave);

        //when
         Car expectedCar = underTest.findCarsByRegistrationId(registrationNumber);

        //then
        assertThat(expectedCar).hasFieldOrPropertyWithValue("registrationId", registrationNumber);


    }
}