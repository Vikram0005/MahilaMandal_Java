package com.mahilamandal.response.info;

import com.mahilamandal.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfo {
    private int id;
    private String name;
    private String mobileNo;
    private String emailId;
    private String password;
    private int addedBy;
    private int groupId;
    private Address address;
}
