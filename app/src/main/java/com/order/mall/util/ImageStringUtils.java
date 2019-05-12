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
}
