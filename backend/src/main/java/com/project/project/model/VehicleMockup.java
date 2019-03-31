package com.project.project.model;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class VehicleMockup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RentACar rentACarCompany;

}
