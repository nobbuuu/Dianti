package com.base.baselib.net;

import com.base.baselib.bean.HeardInfo;
import com.base.baselib.bean.ImageUrl;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.utils.BitmapUtils;

import java.io.File;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * https://blog.csdn.net/huanglei201502/article/details/81185950 c参考
 */
public interface BaseFileApi {
    @Multipart
    @POST("user/avatar.html")
    Observable<Bean<HeardInfo>> setAvatar(@Part MultipartBody.Part image);


    @Multipart
    @POST("user/uploadimg.html")
    Observable<Bean<ImageUrl>> uploadImg(@Part MultipartBody.Part image);


    @Multipart
    @POST("user/uploadimgs.html")
    Observable<BeanList<ImageUrl>> uploadImgList(@Part MultipartBody.Part[] imageList);


}
