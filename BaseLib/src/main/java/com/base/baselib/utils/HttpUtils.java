package com.base.baselib.utils;

import android.app.Activity;
import android.content.Context;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.base.baselib.AppConstant;
import com.base.baselib.BaseAPP;
import com.base.baselib.net.ApiUrl;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public class HttpUtils {

    public static MultipartBody.Part getRequestBodyPart(String element, File mFile) {
        if (mFile != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            builder.addFormDataPart(element, mFile.getName(), imageBody);//element 后台接收图片流的参数名
            MultipartBody.Part part = builder.build().part(0);
            return part;
        } else {
            return null;
        }
    }

    public static RequestBody getBody(String str) {
        return RequestBody.create(MediaType.parse("text/plain"), str);
    }

    /**
     * @param path 图片路径
     * @return
     * @将图片文件转化为字节数组字符串，并对其进行Base64编码处理
     * @author QQ986945193
     * @Date 2015-01-26
     */
    public static String imageToBase64(String path) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        byte[] data = null;
        // 读取图片字节数组
        try {

            InputStream in = new FileInputStream(path);

            data = new byte[in.available()];

            in.read(data);

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        String encodedString = Base64.encodeToString(data, Base64.DEFAULT);
        return encodedString;

    }

    public static Map getTokenMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("TOKEN", SpUtils.getString(SpUtilsConstant.apiKey));
        return map;
    }

    public static void upLoadImgFile(File mFile, String imagType, Callback callback) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //创建一个Request
        final Request request = new Request.Builder()
                .addHeader("TOKEN", SpUtils.getString(SpUtilsConstant.apiKey))
                .url(ApiUrl.getBaseUrl() + "/mobile/user/uploadSignature")
                .post(getRequestBody(mFile, imagType))
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(callback);
    }

    public static void uploadImg(File mFile, Callback callback) {
        //创建okHttpClient对象
        OkHttpClient mOkHttpClient = new OkHttpClient();

        //创建一个Request
        final Request request = new Request.Builder()
                .addHeader("TOKEN", SpUtils.getString(SpUtilsConstant.apiKey))
                .url(ApiUrl.getBaseUrl() + "/mobile/user/uploadImg")
                .post(getRequestBody(mFile))
                .build();

        //new call
        Call call = mOkHttpClient.newCall(request);

        //请求加入调度
        call.enqueue(callback);
    }

    public static RequestBody getRequestBody(File mFile, String imgType) {
        if (mFile != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            builder.addFormDataPart("file", mFile.getName(), imageBody);//element 后台接收图片流的参数名
            builder.addFormDataPart("imgType", imgType);
            builder.addFormDataPart("token", SpUtils.getString(SpUtilsConstant.apiKey));
            RequestBody part = builder.build();
            return part;
        } else {
            return null;
        }
    }

    public static RequestBody getRequestBody(File mFile) {
        if (mFile != null) {
            MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);//ParamKey.TOKEN 自定义参数key常量类，即参数名
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), mFile);
            builder.addFormDataPart("file", mFile.getName(), imageBody);//element 后台接收图片流的参数名
            builder.addFormDataPart("token", SpUtils.getString(SpUtilsConstant.apiKey));
            builder.addFormDataPart("type", "image");
            RequestBody part = builder.build();
            return part;
        } else {
            return null;
        }
    }
}
