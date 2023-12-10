package com.mahilamandal.error;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class ExceptionHandler {

//    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
//
//    private BaseResponse GlobalException(Exception exception){
//        BaseResponse response= new BaseResponse();
//
//        Logger.printError(exception.getStackTrace().toString());
//
//        response.setMessage(exception.getMessage());
//        response.setStatusCode(StatusCode.InternalServerError.ordinal());
//        return response;
//    }

    @org.springframework.web.bind.annotation.ExceptionHandler(JsonProcessingException.class)
    private BaseResponse HandleJsonException(JsonProcessingException exception){
        BaseResponse response= new BaseResponse();

        Logger.printError(exception.getStackTrace().toString());

        response.setMessage("Invalid json request !!");
        response.setStatusCode(StatusCode.InternalServerError.ordinal());
        return response;
    }

}
