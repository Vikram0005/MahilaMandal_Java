package com.mahilamandal.response;

import com.mahilamandal.response.info.MemberInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse{
    private List<MemberInfo> memberList;
}
