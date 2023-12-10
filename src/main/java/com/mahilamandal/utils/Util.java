package com.mahilamandal.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Util {

    public static Timestamp getTimeStamp(LocalDateTime localDateTime){
        return  Timestamp.valueOf(localDateTime);
    }

    public static LocalDateTime getLocalDateTime(Timestamp timestamp){
        return timestamp.toLocalDateTime();
    }
}
