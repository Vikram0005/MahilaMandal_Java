package com.mahilamandal.request;

import com.mahilamandal.entity.Address;
import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MemberRequest {
    private int id;
    private String name;
    private String mobileNo;
    private String emailId;
    private String password;
    private int addedBy;
    private int groupId;
    private Address address;
}
