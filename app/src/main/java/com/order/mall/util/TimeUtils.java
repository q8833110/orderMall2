package com.order.mall.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {

    public static String timeConvertText(long endTime){
        if (endTime <= 0) return "0分";
        long diffTime = endTime / 1000 ;
        long hour = diffTime / (60 * 60) ;
        long miute = diffTime / 60 ;
        return hour + "小时" + miute + "分";
    }

    public static String getDate(long timeStamp){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM月dd日");
        Date date = new Date(timeStamp);
        return simpleDateFormat.format(date);
    }

}
