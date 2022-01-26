package com.amirservices.showroom.dto;

import com.amirservices.showroom.model.Car;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;

import java.util.Calendar;
import java.util.Date;

public class CarMapper {

    private static final String OWNER_TYPE_PERSON = "PERSON";
    private static final String OWNER_TYPE_COMPANY = "COMPANY";

    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";


    // Converters
    public static Converter<Character, String> ownerTypeConverter = new AbstractConverter<Character, String>() {
        protected String convert(Character source) {
            if(source== null)
            return "INVALID";

            if(source == 'P'){
                return OWNER_TYPE_PERSON;
            }else if(source == 'C'){
                return OWNER_TYPE_COMPANY;
            }else{
                return  "INVALID";
            }
        }
    };


    public static Converter<String, String> toUpperCaseConverter = new AbstractConverter<String, String>() {
        protected String convert(String source) {
            if(StringUtils.isBlank(source))
                return source;

            return source.trim().toUpperCase();
        }
    };


    public static Converter<String, String> toLowerCaseConverter = new AbstractConverter<String, String>() {
        protected String convert(String source) {
            if(StringUtils.isBlank(source))
                return source;

            return source.trim().toLowerCase();
        }
    };

    public static Converter<Date, Integer> dateToYearConverter = new AbstractConverter<Date, Integer>() {
        protected Integer convert(Date source) {
            if(source == null)
                return 0;

            Calendar cal= Calendar.getInstance();
            cal.setTime(source);
            return cal.get(Calendar.YEAR);
        }
    };



    public static final  Car performDTOtoEntityMapping(CarIncomingDTO incomingCar){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(CarIncomingDTO.class, Car.class);



        Car car= modelMapper.map(incomingCar, Car.class);

        return car;
    }

    public static final CarOutgoingDTO performEntityToDTOMapping(Car entityCar){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.createTypeMap(Car.class, CarOutgoingDTO.class)
                .addMapping(Car::getDescription, CarOutgoingDTO::setDescription)
                .addMapping(Car::getRegistrationId, CarOutgoingDTO::setRegistrationNumber)
                //.addMappings(mapper -> mapper.using(dateToYearConverter).map(src -> src.getRegistrationDate(), CarOutgoingDTO::setLastRegistrationDate))
                .addMappings(mapper -> mapper.using(ownerTypeConverter).map(src -> src.getOwner().getType(), CarOutgoingDTO::setCurrentOwnerType))
                .addMapping(src -> src.getOwner().getFullName(), CarOutgoingDTO::setCurrentOwnerName)
                .addMapping(src -> src.getOwner().getEmail(), CarOutgoingDTO::setCurrentOwnerEmail)
                .addMapping(src -> src.getOwner().getTelephone(), CarOutgoingDTO::setCurrentOwnerTelephone)
                .addMapping(src -> src.getOwner().getFullAddress(), CarOutgoingDTO::setCurrentOwnerAddress)
                .addMapping(src -> src.getManufacturingDetail().getMakeYear(), CarOutgoingDTO::setManufacturingYear)
                .addMappings(mapper -> mapper.using(toUpperCaseConverter).map(src -> src.getManufacturingDetail().getBrand(), CarOutgoingDTO::setBrand))
                .addMappings(mapper -> mapper.using(toUpperCaseConverter).map(src -> src.getManufacturingDetail().getModel(), CarOutgoingDTO::setModel))
                .addMappings(mapper -> mapper.using(toUpperCaseConverter).map(src -> src.getManufacturingDetail().getType(), CarOutgoingDTO::setBodyType));


        CarOutgoingDTO carOutgoingDTO = modelMapper.map(entityCar, CarOutgoingDTO.class);

        return carOutgoingDTO;
    }







}
