package com.mahilamandal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private String villageName;
    private String postName;
    private String districtName;
    private String stateName;
    private String pinCode;
}
