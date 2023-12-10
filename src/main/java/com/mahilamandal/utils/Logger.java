package com.mahilamandal.utils;

import com.mahilamandal.utils.enums.PrintType;
import com.mahilamandal.utils.enums.RequestType;

public class Logger {

    private static int loggerStatus=0;

    public static  void EnableLogger(){
        loggerStatus=1;
    }
    public static  void DisableLogger(){
        loggerStatus=0;
    }

    public static void print(Object error){
        System.out.println(error);
    }

    public static void printError(Object error) {
        print(error, PrintType.Error);
    }

    public static void printMessage(Object error) {
        print(error, PrintType.Message);
    }

    public static void printMessage(Object error,PrintType printType) {
        print(error, printType);
    }

    public static void printMessage(Object error, PrintType printType, RequestType requestType) {
        print(error, printType,requestType);
    }


    private static void print(Object error,PrintType type) {
        if (loggerStatus==0)return;
        System.out.println();
        if (type == PrintType.None)
            System.out.println("#####################################################");
        else
            System.out.println("############################    " + type + "    #########################");
        System.out.println();
        System.out.println(error);
    }

    private static void print(Object error,PrintType type,RequestType requestType) {
        if (loggerStatus==0)return;
        System.out.println();
        if (type == PrintType.None)
            System.out.println("#####################################################");
        else
            System.out.println("############################    " + type + " for "+requestType+    "    #########################");
        System.out.println();
        System.out.println(error);
    }

}
