package com.order.mall.util;

import android.content.Context;

import com.order.mall.data.SharedPreferencesHelp;

public class LoginUtils {

    /**
     * 判断是否登录
     * @param context
     * @return
     */
    public static boolean isLogin(Context context){
        return SharedPreferencesHelp.getInstance(context).getUser() != null ;
    }
}
