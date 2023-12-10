package com.mahilamandal.services;

import com.mahilamandal.request.UserLoginRequest;
import com.mahilamandal.request.UserRegistrationRequest;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.info.UserInfo;
import com.mahilamandal.response.BaseResponse;

public interface UserRegistrationService {

    BaseResponse addUser(UserRegistrationRequest userRegistration);
    Response<UserInfo> loginUser(UserLoginRequest userLoginRequest);
    Response<UserInfo> findUserById(int userId);
}
