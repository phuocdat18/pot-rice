package com.cg.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;


@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
@Entity
@Table(name = "location_region")
public class LocationRegion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String provinceId;

    private String provinceName;

    private String districtId;

    private String districtName;

    private String wardId;

    private String wardName;

    private String address;

    public LocationRegion(Long id) {
        this.id = id;
    }

}
