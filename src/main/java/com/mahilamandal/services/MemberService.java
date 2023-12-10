package com.mahilamandal.services;
import com.mahilamandal.request.MemberRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.response.MemberResponse;
import com.mahilamandal.response.Response;
import com.mahilamandal.response.info.MemberInfo;
import org.springframework.stereotype.Service;

@Service
public interface MemberService {
    BaseResponse addMember(MemberRequest memberRequest);
    Response<MemberResponse> getAllMembers();
    Response<MemberInfo> getMemberById(int memberId);
}
