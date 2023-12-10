package com.mahilamandal.response;

import com.mahilamandal.request.BaseRequest;
import com.mahilamandal.utils.enums.StatusCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseResponse extends BaseRequest {
    protected String message;
    protected int statusCode= StatusCode.Success.ordinal();
}
