package com.project.project.model;

import javax.persistence.*;
import java.util.HashMap;

@Entity
public class VehicleMockup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RentACar rentACarCompany;



}
