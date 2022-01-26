package com.amirservices.showroom.repository;

import com.amirservices.showroom.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {

    Car findCarsByRegistrationId(String registrationId);
}
