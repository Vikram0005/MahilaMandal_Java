package com.mahilamandal.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mahilamandal.request.BaseRequest;
import com.mahilamandal.response.BaseResponse;
import com.mahilamandal.utils.Logger;
import com.mahilamandal.utils.enums.PrintType;
import com.mahilamandal.utils.enums.RequestType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
    private static Gson gson;

    @Value("${application-version}")
    String version;

    public MainController() {

        gson=new GsonBuilder()
               // .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
    }

    public static Gson getGson()
    {
        return gson;
    }

    @PostMapping("/processRequest/")
    private String PostData(@RequestBody String requestBody) {

        String data = requestBody;

//        String encryptedData=AES.encrypt(requestBody);
//        System.out.println(encryptedData);
//        //Decryption
//        String data=AES.decrypt(encryptedData);
//        System.out.println(data);

        BaseResponse response = new BaseResponse();

        BaseRequest request = gson.fromJson(data, BaseRequest.class);
        if (request != null) {
            Logger.printMessage(data, PrintType.Request, RequestType.values()[request.getRequestType()]);

            response.setUserId(request.getUserId());
            response.setRequestType(request.getRequestType());
            response.setVersion(request.getVersion());

            //Checking Version
            if (!version.equalsIgnoreCase(request.getVersion())) {
                response.setMessage("Invalid Version "+request.getVersion());
                return ConvertObjectToJson((response));
            }

            if (request.getRequestType() != RequestType.None.ordinal()) {
                return callServices(request, data);
            } else {
                response.setMessage("Invalid RequestType : " + request.getRequestType());
            }
        } else {
            response.setMessage("Requested object is null");
        }
        return ConvertObjectToJson(response);
    }

    @Autowired
    private ServiceHandler serviceHandler;

    private String callServices(BaseRequest baseRequest, String requestData) {
        Object response= serviceHandler.callServicesBasedOnRequestType(baseRequest.getRequestType(),requestData);

        BaseResponse res=(BaseResponse)response;
        res.setUserId(baseRequest.getUserId());
        res.setRequestType(baseRequest.getRequestType());
        res.setVersion(baseRequest.getVersion());

        String finalResponse= ConvertObjectToJson(response);
        Logger.printMessage(finalResponse,PrintType.Response,RequestType.values()[baseRequest.getRequestType()]);
        //String encryptData=AES.encrypt(finalResponse);
        //return encryptData;
        return finalResponse;
    }

    private  String ConvertObjectToJson(Object object) {
        return  gson.toJson(object);
    }
}