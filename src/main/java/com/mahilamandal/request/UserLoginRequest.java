package com.mahilamandal.request;

import lombok.Data;

@Data
public class UserLoginRequest{
    private String mobileNo;
    private String password;
}
