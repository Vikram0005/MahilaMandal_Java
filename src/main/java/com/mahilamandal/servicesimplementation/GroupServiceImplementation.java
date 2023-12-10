package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.GroupEntity;
import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.GroupRepository;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.GroupRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.GroupResponse;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.info.GroupInfo;
import com.mahilamandal.services.GroupService;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImplementation implements GroupService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRegistrationRepository userRegistrationRepository;

    @Override
    public BaseResponse addGroup(GroupRequest groupRequest) {
        if (groupRequest != null) {

            Optional<UserRegistrationEntity> userRegistrationEntity = userRegistrationRepository.findById(groupRequest.getAddedBy());

            if (userRegistrationEntity.isPresent()) {
                GroupEntity entity = GroupEntity.builder()
                        .groupName(groupRequest.getGroupName())
                        .amount(groupRequest.getAmount())
                        .tenure(groupRequest.getTenure())
                        .addedBy(userRegistrationEntity.get())
                        .build();
                groupRepository.save(entity);
                return new BaseResponse("Group Added Successfully !!",StatusCode.Success.ordinal());
            } else {
                return new BaseResponse("AddedBy value is incorrect!!", StatusCode.Failed.ordinal());
            }
        }
        return new BaseResponse("Invalid data !!",StatusCode.Success.ordinal());
    }

    @Override
    public Response<GroupResponse> getAllGroup() {
        Response<GroupResponse> response=new Response<>();

        GroupResponse groupResponse=new GroupResponse();
        List<GroupEntity> groupEntityList=groupRepository.findAll();

        List<GroupInfo> groupInfos=new ArrayList<>();

        groupEntityList.forEach(group->{
            GroupInfo groupInfo=GroupInfo.builder()
                    .id(group.getId())
                    .groupName(group.getGroupName())
                    .tenure(group.getTenure())
                    .amount(group.getAmount())
                    .addedBy(group.getAddedBy().getId())
                    .build();
            groupInfos.add(groupInfo);
        });

        groupResponse.setGroups(groupInfos);

        response.setResponse(groupResponse);
        response.setMessage("Groups fetched Successfully");
        response.setStatusCode(StatusCode.Success.ordinal());
        return response;
    }

    @Override
    public Response<GroupInfo> getGroupById(int groupId) {
        Response<GroupInfo> response=new Response<>();

        Optional<GroupEntity> groupEntity=groupRepository.findById(groupId);
        if (groupEntity.isPresent()){
            GroupEntity entity=groupEntity.get();

            GroupInfo groupInfo=GroupInfo.builder()
                    .id(entity.getId())
                    .groupName(entity.getGroupName())
                    .tenure(entity.getTenure())
                    .amount(entity.getAmount())
                    .addedBy(entity.getAddedBy().getId())
                    .build();

            response.setResponse(groupInfo);
            response.setMessage("Fetched Successfully");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        else {
            response.setMessage("Invalid group id !!");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }
}
