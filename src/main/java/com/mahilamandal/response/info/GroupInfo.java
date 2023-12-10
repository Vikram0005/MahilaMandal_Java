package com.mahilamandal.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupInfo {
    private int id;
    private String groupName;
    private float amount;
    private int tenure;
    private int addedBy;
}
