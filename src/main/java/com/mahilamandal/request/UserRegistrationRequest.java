package com.mahilamandal.request;

import com.mahilamandal.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegistrationRequest{
    private int id;
    private int addedBy;
    private String userName;
    private String password;
    private String mobileNo;
    private int roleId;
    private Address address;
}
