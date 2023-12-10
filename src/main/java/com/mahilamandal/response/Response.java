package com.mahilamandal.response;

import lombok.Data;

@Data
public class Response<T>  extends BaseResponse {
    private T response;
}
