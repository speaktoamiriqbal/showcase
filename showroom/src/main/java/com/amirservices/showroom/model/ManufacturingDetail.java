package com.amirservices.showroom.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="manufacturing_details")
public class ManufacturingDetail extends BaseEntity{

    @Column
    private String type; // SEDAN, HATACHBACK,

    @Column
    private String brand; // TOYOTA, HONDA, FORD

    @Column
    private String model; // VITZ, CITY, FIESTA

    @Column(length = 4)
    private Integer makeYear;

    @OneToMany(mappedBy = "manufacturingDetail", cascade = CascadeType.ALL)
    private List<Car> cars;

}
