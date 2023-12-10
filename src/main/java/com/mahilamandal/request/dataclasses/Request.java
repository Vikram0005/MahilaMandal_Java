package com.mahilamandal.request.dataclasses;

import com.mahilamandal.request.BaseRequest;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Request<T> extends BaseRequest {
    private T request;
}
