package com.mahilamandal.controller;

import com.mahilamandal.request.*;
import com.mahilamandal.request.dataclasses.*;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.services.GroupService;
import com.mahilamandal.services.MemberService;
import com.mahilamandal.services.RoleService;
import com.mahilamandal.services.UserRegistrationService;
import com.mahilamandal.utils.enums.RequestType;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;

@Component
public class ServiceHandler {
    @Autowired
    private UserRegistrationService userRegistrationService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private MemberService memberService;

    public Object callServicesBasedOnRequestType(int requestType, String requestData){
        Object response=new BaseResponse();
        Request baseRequest=JsonToObject(requestData,Request.class);
        switch(RequestType.values()[requestType]){
            case Test:
                response=new BaseResponse("Test API is working ", StatusCode.Success.ordinal());
                break;
            case AddRole:
                Request<RoleRequest> roleRequest= JsonToObject(requestData, RoleRequestData.class);
                response=roleService.addRole(roleRequest.getRequest());
                break;
            case FetchRole:
                response=roleService.getAllRole();
                break;
            case DeleteRole:
                response=roleService.deleteRole(baseRequest.getId());
                break;
            case UserLogin:
                Request<UserLoginRequest> userLoginRequest= JsonToObject(requestData, UserLoginRequestData.class);
                response=userRegistrationService.loginUser(userLoginRequest.getRequest());
                break;
            case UserRegister:
                Request<UserRegistrationRequest> userRegistrationRequest= JsonToObject(requestData, UserRegistrationRequestData.class);
                response= userRegistrationService.addUser(userRegistrationRequest.getRequest());
                break;
            case FindUserById:
                response=userRegistrationService.findUserById(baseRequest.getUserId());
                break;
            case AddGroup:
                Request<GroupRequest> groupRequest=JsonToObject(requestData, GroupRequestData.class);
                response=groupService.addGroup(groupRequest.getRequest());
                break;
            case GetAllGroup:
                response=groupService.getAllGroup();
                break;
            case  GetGroupById:
                response=groupService.getGroupById(baseRequest.getId());
                break;
            case AddMember:
                Request<MemberRequest> memberRequest=JsonToObject(requestData, MemberRequestData.class);
                response=memberService.addMember(memberRequest.getRequest());
                break;
            case GetAllMember:
                response=memberService.getAllMembers();
                break;
            case  GetMemberById:
                response=memberService.getMemberById(baseRequest.getId());
                break;
        }
        return response;
    }

    private <R> Request JsonToObject(String data, Class<R> requestData) {
        return MainController.getGson().fromJson(data,(Type)requestData);
    }
}
