package com.order.mall.util;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class FileUploadUtils {

    /**
     * 获取file body
     *
     * @param fileList
     * @return
     */
    public static RequestBody fileBody(List<File> fileList) {
        if (fileList == null || fileList.size() == 0) return null;
        RequestBody requestBody;
        MultipartBody.Builder builder;
        builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (File file : fileList) {
            builder.addFormDataPart("imgs", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        requestBody = builder.build();
        return requestBody;
    }
}
