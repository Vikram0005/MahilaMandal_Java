package com.mahilamandal.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRequest{
    private int id;
    private String groupName;
    private float amount;
    private int tenure;
    private int addedBy;
}
