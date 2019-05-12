package com.order.mall.util;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;

public class ImageStringUtils {

    public static List<String> getImages(String images){
        if (TextUtils.isEmpty(images)) return null ;
        List<String> imageList = new ArrayList<>();
        String[] is =  images.split(",");
        for (int i = 0 ;i < is.length ;i++){
            imageList.add(is[i]);
        }
        return imageList ;
    }

    /**
     * leve
     * @param level
     * @return
     */
    public static String convertString(int level){
        String contnet ;
        switch (level){
            case 0:
                contnet = "普通会员";
                break;
            case 1:
                contnet = "个体会员";
                break;
            case 2:
                contnet = "社区会员";
                break;
            case 3:
                contnet = "市级会员";
                break;
            case 4:
                contnet = "省级会员";
                break;
                default:
                    contnet = "普通会员";
                    break;
        }
        return contnet ;
    }
}
