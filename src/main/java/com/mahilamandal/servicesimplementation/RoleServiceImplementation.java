package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.repository.RoleRepository;
import com.mahilamandal.request.RoleRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.RoleResponse;
import com.mahilamandal.response.info.RoleInfo;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImplementation implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseResponse addRole(RoleRequest roleRequest) {
        RoleEntity roleEntity=new RoleEntity();
        roleEntity.setRoleName(roleRequest.getRoleName());
        roleEntity.setId(roleRequest.getId());

        RoleEntity entity=roleRepository.save(roleEntity);
        return new BaseResponse("Role Added Successfully", StatusCode.Success.ordinal());
    }

    @Override
    public Response<RoleResponse> getAllRole() {
        Response<RoleResponse> response=new Response<>();
        RoleResponse roleResponse=new RoleResponse();

        List<RoleEntity> roleEntities=roleRepository.findAll();
        List<RoleInfo> roleInfos=new ArrayList<>();

        roleEntities.forEach(role->{
            RoleInfo roleInfo= RoleInfo.builder()
                    .id(role.getId())
                    .roleName(role.getRoleName())
                    .build();
            roleInfos.add(roleInfo);
        });
        roleResponse.setRoles(roleInfos);

        response.setResponse(roleResponse);
        response.setMessage("Roles fetched Successfully");
        response.setStatusCode(StatusCode.Success.ordinal());
        return response;
    }

    @Override
    public Response<RoleInfo> getRoleById(int roleId) {

        Response<RoleInfo> response=new Response<>();
        Optional<RoleEntity> roleEntity= roleRepository.findById(roleId);

        if (roleEntity.isPresent()){

            RoleEntity entity=roleEntity.get();

            RoleInfo roleInfo=RoleInfo.builder()
                    .id(entity.getId())
                    .roleName(entity.getRoleName())
                    .build();

            response.setResponse(roleInfo);
            response.setMessage("Role Fetched Successfully !!");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        else
        {
            response.setResponse(null);
            response.setMessage("No record found !!");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }

    @Override
    public BaseResponse deleteRole(int roleId) {
        BaseResponse response = new BaseResponse();
        if (roleId <= 0) {
            response.setMessage("Invalid Role Id!!");
            response.setStatusCode(StatusCode.Failed.ordinal());
        } else {
            roleRepository.deleteById(roleId);
            response.setMessage("Role Deleted Successfully!!");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        return response;
    }


}
