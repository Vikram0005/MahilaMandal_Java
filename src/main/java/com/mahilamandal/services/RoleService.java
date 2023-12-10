package com.mahilamandal.services;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.request.RoleRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.RoleResponse;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.info.RoleInfo;

public interface RoleService {

    BaseResponse addRole(RoleRequest roleRequest);

    Response<RoleResponse> getAllRole();
    Response<RoleInfo> getRoleById(int roleId);
    BaseResponse deleteRole(int roleId);
}
