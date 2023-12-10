package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.RoleEntity;
import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.RoleRepository;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.UserLoginRequest;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.info.RoleInfo;
import com.mahilamandal.response.info.UserInfo;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.Util;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserRegistrationServiceImplementation implements UserRegistrationService {

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public BaseResponse addUser(UserRegistrationRequest userRegistration) {

        BaseResponse baseResponse=new BaseResponse();

        UserRegistrationEntity entity=new UserRegistrationEntity();
        entity.setUserName(userRegistration.getUserName());
        entity.setAddedBy(userRegistration.getAddedBy());
        entity.setPassword(userRegistration.getPassword());
        entity.setMobileNo(userRegistration.getMobileNo());
        entity.setAddress(userRegistration.getAddress());

        Optional<RoleEntity> roleEntity=roleRepository.findById(userRegistration.getRoleId());

        if (roleEntity.isPresent()){
            entity.setRole(roleEntity.get());
            userRegistrationRepository.save(entity);
            baseResponse.setMessage("Data Inserted Successfully");
            baseResponse.setStatusCode(StatusCode.Success.ordinal());
        }
        else {
            baseResponse.setMessage("Invalid Role Id");
            baseResponse.setStatusCode(StatusCode.Failed.ordinal());
        }
        return baseResponse;
    }

    @Override
    public Response<UserInfo> loginUser(UserLoginRequest userLoginRequest) {
        Response<UserInfo> response=new Response<>();

        UserRegistrationEntity entity= userRegistrationRepository.findByMobileNoAndPassword(
                userLoginRequest.getMobileNo(),
                userLoginRequest.getPassword()
        );

        if (entity!=null){

            RoleInfo roleInfo=RoleInfo.builder()
                    .id(entity.getRole().getId())
                    .roleName(entity.getRole().getRoleName())
                    .timestamp(Util.getTimeStamp(entity.getRole().getCreatedDateTime()))
                    .build();

            response.setResponse(new UserInfo(
                    entity.getId(),
                    entity.getAddedBy(),
                    entity.getUserName(),
                    entity.getPassword(),
                    entity.getMobileNo(),
                    roleInfo,
                    entity.getAddress()
            ));
            response.setMessage("Login Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }else {
            response.setMessage("Invalid Mobile No Or Password !");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }

    @Override
    public Response<UserInfo> findUserById(int userId) {
        Response<UserInfo> response=new Response<>();

        Optional<UserRegistrationEntity> user= userRegistrationRepository.findById(userId);

        if (user.isPresent()){
            UserRegistrationEntity entity=user.get();
            RoleInfo roleInfo=RoleInfo.builder()
                    .id(entity.getRole().getId())
                    .roleName(entity.getRole().getRoleName())
                    .build();

            response.setResponse(new UserInfo(
                    entity.getId(),
                    entity.getAddedBy(),
                    entity.getUserName(),
                    entity.getPassword(),
                    entity.getMobileNo(),
                    roleInfo,
                    entity.getAddress()
            ));
            response.setMessage("Fetched Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }else {
            response.setMessage("No record found !");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }
}
