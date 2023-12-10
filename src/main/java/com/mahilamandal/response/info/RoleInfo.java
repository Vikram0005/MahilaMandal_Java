package com.mahilamandal.response.info;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoleInfo {
    private int id;
    private String roleName;

    private Timestamp timestamp;
}
