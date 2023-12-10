package com.mahilamandal.servicesimplementation;

import com.mahilamandal.entity.GroupEntity;
import com.mahilamandal.entity.MemberEntity;
import com.mahilamandal.entity.UserRegistrationEntity;
import com.mahilamandal.repository.GroupRepository;
import com.mahilamandal.repository.MemberRepository;
import com.mahilamandal.repository.UserRegistrationRepository;
import com.mahilamandal.request.MemberRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.MemberResponse;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.info.MemberInfo;
import com.mahilamandal.services.MemberService;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImplementation implements MemberService {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private UserRegistrationRepository userRegistrationRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Override
    public BaseResponse addMember(MemberRequest memberRequest) {
        BaseResponse baseResponse = null;

        if (memberRequest != null) {
            Optional<UserRegistrationEntity> userOptional = userRegistrationRepository.findById(memberRequest.getAddedBy());
            if (!userOptional.isPresent()) {
                return new BaseResponse("Invalid AddedBy !!", StatusCode.Failed.ordinal());
            }

            Optional<GroupEntity> groupOptional = groupRepository.findById(memberRequest.getGroupId());
            if (!groupOptional.isPresent()) {
                return new BaseResponse("Invalid GroupId !!", StatusCode.Failed.ordinal());
            }

            MemberEntity memberEntity = MemberEntity.builder()
                    .name(memberRequest.getName())
                    .emailId(memberRequest.getEmailId())
                    .mobileNo(memberRequest.getMobileNo())
                    .password(memberRequest.getPassword())
                    .addedBy(userOptional.get())
                    .group(groupOptional.get())
                    .address(memberRequest.getAddress())
                    .build();

            memberRepository.save(memberEntity);
            return new BaseResponse("Member Added Successfully !!", StatusCode.Success.ordinal());

        } else {
            return new BaseResponse("Invalid Request !!", StatusCode.Failed.ordinal());
        }
    }

    @Override
    public Response<MemberResponse> getAllMembers() {
        Response<MemberResponse> response=new Response<>();
        List<MemberEntity> memberEntities=memberRepository.findAll();

        if (memberEntities.stream().count()>0) {
            memberEntities.forEach(memberEntity -> {
                MemberInfo memberInfo = MemberInfo.builder()
                        .name(memberEntity.getName())
                        .emailId(memberEntity.getEmailId())
                        .mobileNo(memberEntity.getMobileNo())
                        .password(memberEntity.getPassword())
                        .addedBy(memberEntity.getAddedBy().getId())
                        .groupId(memberEntity.getGroup().getId())
                        .address(memberEntity.getAddress())
                        .build();
                response.getResponse().getMemberList().add(memberInfo);
            });

            response.setMessage("Fetched Successfully!!");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        else
        {
            response.setMessage("No record found!!");
            response.setStatusCode(StatusCode.Success.ordinal());
        }
        return response;
    }

    @Override
    public Response<MemberInfo> getMemberById(int memberId) {
        Response<MemberInfo> response = new Response<>();
        Optional<MemberEntity> option = memberRepository.findById(memberId);
        if (option.isPresent()) {
            MemberEntity memberEntity = option.get();
            MemberInfo memberInfo = MemberInfo.builder()
                    .name(memberEntity.getName())
                    .emailId(memberEntity.getEmailId())
                    .mobileNo(memberEntity.getMobileNo())
                    .password(memberEntity.getPassword())
                    .addedBy(memberEntity.getAddedBy().getId())
                    .groupId(memberEntity.getGroup().getId())
                    .address(memberEntity.getAddress())
                    .build();
            response.setResponse(memberInfo);
            response.setMessage("Fetched Successfully!!");
            response.setStatusCode(StatusCode.Success.ordinal());
        } else {
            response.setMessage("Invalid memberId!!");
            response.setStatusCode(StatusCode.Failed.ordinal());
        }
        return response;
    }
}
