package com.amirservices.showroom.model;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(name = "registration_id", unique = true)
    private String registrationId;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private Owner owner;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @ManyToOne
    @JoinColumn(name = "manufacturingdetail_id", referencedColumnName = "id")
    private ManufacturingDetail manufacturingDetail;





}
