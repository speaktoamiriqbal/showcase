package com.amirservices.showroom.dto;


import com.amirservices.showroom.model.Car;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper(uses={DateMapper.class,
        OwnerTypeMapper.class})

public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper( CarMapper.class );

    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "registrationId", target = "registrationNumber"),
            @Mapping(source = "registrationDate", target = "lastRegistrationDate"),
            @Mapping(source = "owner.fullName", target = "currentOwnerName"),
            @Mapping(source = "owner.email", target = "currentOwnerEmail"),
            @Mapping(source = "owner.telephone", target = "currentOwnerTelephone"),
            @Mapping(source = "owner.fullAddress", target = "currentOwnerAddress"),
            @Mapping(source = "owner.type", target = "currentOwnerType" ),
            @Mapping(source = "manufacturingDetail.type", target = "bodyType"),
            @Mapping(source = "manufacturingDetail.brand", target = "brand"),
            @Mapping(source = "manufacturingDetail.model", target = "model"),
            @Mapping(source = "manufacturingDetail.makeYear", target = "manufacturingYear")
    })
    CarOutgoingDTO entityToOutgoingDto(Car car);


    @BeanMapping(ignoreByDefault = true)
    @Mappings({
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "registrationNumber", target = "registrationId"),
            @Mapping(source = "lastRegistrationDate", target = "registrationDate"),
            @Mapping(source = "currentOwnerName", target = "owner.fullName"),
            @Mapping(source = "currentOwnerEmail", target = "owner.email"),
            @Mapping(source = "currentOwnerTelephone", target = "owner.telephone"),
            @Mapping(source = "currentOwnerAddress", target = "owner.fullAddress"),
            @Mapping(source = "currentOwnerType", target = "owner.type"),
            @Mapping(source = "bodyType", target = "manufacturingDetail.type"),
            @Mapping(source = "brand", target = "manufacturingDetail.brand"),
            @Mapping(source = "model", target = "manufacturingDetail.model"),
            @Mapping(source = "manufacturingYear", target = "manufacturingDetail.makeYear")
    })
    Car incomingDtoToEntity(CarIncomingDTO carIncomingDTO);









//@Mapping(source = "manufacturingDate", dateFormat = "dd.MM.yyyy")
   // Car incomingDtoToEntity(CarIncomingDTO carIncomingDTO);


}



/*
*
* pper.createTypeMap(CarIncomingDTO.class, Car.class)
  .addMapping(CarIncomingDTO::getDescription, Car::setDescription)
  .addMapping(CarIncomingDTO::getRegistrationNumber, Car::setRegistrationId)
  //.addMappings(mapper -> mapper.using(dateToYearConverter).map(src -> src.getRegistrationDate(), CarOutgoingDTO::setLastRegistrationDate))
  .addMappings(mapper -> mapper.map(src -> src.getCurrentOwnerType(), (dest, v) -> dest.getOwner().setType(OwnerConverter.abbreviate((String) v))))
  .addMapping(src -> src.getCurrentOwnerName() , (dest, v) -> dest.getOwner().setFullName((String) v))
  .addMapping(src -> src.getCurrentOwnerEmail() , (dest, v) -> dest.getOwner().setEmail((String) v))
  .addMapping(src -> src.getCurrentOwnerTelephone() , (dest, v) -> dest.getOwner().setTelephone((Long) v))
  .addMapping(src -> src.getCurrentOwnerAddress() , (dest, v) -> dest.getOwner().setFullAddress((String) v))
  .addMapping(src -> src.getManufacturingYear() , (dest, v) -> dest.getManufacturingDetail().setMakeYear((Integer) v))
  .addMappings(mapper -> mapper.using(toLowerCaseConverter).map(src -> src.getBrand() , (dest, v) -> dest.getManufacturingDetail().setBrand((String) v)))
  .addMappings(mapper -> mapper.using(toLowerCaseConverter).map(src -> src.getModel() , (dest, v) -> dest.getManufacturingDetail().setModel((String) v)))
  .addMappings(mapper -> mapper.using(toLowerCaseConverter).map(src -> src.getBodyType() , (dest, v) -> dest.getManufacturingDetail().setType((String) v)));

* */