package com.mahilamandal.response;

import com.mahilamandal.response.info.RoleInfo;
import lombok.Data;

import java.util.List;

@Data
public class RoleResponse{
    private List<RoleInfo> roles;
}
