package com.amirservices.showroom.model;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@Entity
@Table(name = "owners")
public class Owner extends BaseEntity {

    @Column
    private Character type;  // P -> Person,  C-> Company

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private Long telephone;

    @Column
    private String fullAddress;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Car> cars;


}
