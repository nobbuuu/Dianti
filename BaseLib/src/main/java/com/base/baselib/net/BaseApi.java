package com.base.baselib.net;


import com.base.baselib.bean.AffairsTaskDetailBean;
import com.base.baselib.bean.ChoiceTaskListBean;
import com.base.baselib.bean.ChoicedBean;
import com.base.baselib.bean.ComplaintTask;
import com.base.baselib.bean.DevManagerBean;
import com.base.baselib.bean.DeviceTypeItem;
import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.H5ResultBean;
import com.base.baselib.bean.HistotyBean;
import com.base.baselib.bean.InspectionTask;
import com.base.baselib.bean.LoginBean;
import com.base.baselib.bean.MaintainTask;
import com.base.baselib.bean.Menu;
import com.base.baselib.bean.OtherTask;
import com.base.baselib.bean.PerformBean;
import com.base.baselib.bean.Restore;
import com.base.baselib.bean.SignBean;
import com.base.baselib.bean.TaskDetail;
import com.base.baselib.bean.TaskInfo;
import com.base.baselib.bean.TaskInfoItem;
import com.base.baselib.bean.TaskNumber;
import com.base.baselib.bean.UserFiles;
import com.base.baselib.bean.UserInfo;
import com.base.baselib.bean.VersionBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * 基础接口
 */
public interface BaseApi {
    /**
     * 登录接口
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/login")
    Observable<Bean<LoginBean>> login(@FieldMap Map<String, String> map);

    /**
     * 退出登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/logout")
    Observable<Bean<EmptyBean>> loginOut(@FieldMap Map<String, String> map);

    /**
     * 获取个人信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/index.html")
    Observable<Bean<UserInfo>> getUserInfo(@FieldMap Map<String, String> map);


    /**
     * 获取任务信息
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/task.html")
    Observable<Bean<TaskNumber>> getTaskNumber(@FieldMap Map<String, String> map);


    @FormUrlEncoded
    @POST("user/location.html")
    Observable<Bean<String>> upLocation(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/signTask")
    Observable<Bean<SignBean>> userAutograph(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/getTaskDeal")
    Observable<Bean<TaskInfo>> getTaskInfo(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/getTaskList")//待复检任务
    Observable<Bean<TaskInfo>> getTaskList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/getTaskRecheck")
    Observable<Bean<TaskInfo>> getTaskRecheck(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/confirmTask")
    Observable<Bean<TaskInfoItem>> confirmTask(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/submitAffairPoint")//政务: 提交图片或者任务描述
    Observable<Bean<EmptyBean>> submitAffairPoint(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/receiveAffairTask")
    Observable<Bean<TaskInfoItem>> receiveAffairTask(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/getAffairPointList")
    Observable<Bean<HistotyBean>> getAffairPointList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/confirmAffairTask")
    Observable<Bean<EmptyBean>> confirmAffairTask(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/getTaskDetail")
    Observable<Bean<TaskDetail>> getTaskDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/task/getTaskRecheckDetail")
    Observable<Bean<TaskDetail>> getTaskRecheckDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/getAffairTaskDetail")
    Observable<Bean<AffairsTaskDetailBean>> getAffairTaskDetail(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("user/accepttask.html")
    Observable<Bean<String>> acceptTask(@FieldMap Map<String, String> map);

    /**
     * 其他任务列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("user/taskother.html")
    Observable<BeanList<OtherTask>> getOtherTaskList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/api/mobile/user/updateUserPwd")
    Observable<Bean<EmptyBean>> updatePwd(@FieldMap Map<String, String> map);

    /**
     * 获取检查表类目
     *
     */
    @FormUrlEncoded
    @POST("/api/mobile/task/getPointByType")
    Observable<Bean<ChoiceTaskListBean>> getPointByType(@FieldMap Map<String, String> map);

    /**
     *检查完成（药品）
     *
     */
    @FormUrlEncoded
    @POST("/api/mobile/drug/saveDrugAllInfo")
    Observable<Bean<EmptyBean>> saveDrugAllInfo(@FieldMap Map<String, String> map);

    /**
     *检查完成（食品）
     *
     */
    @FormUrlEncoded
    @POST("/api/mobile/task/saveFoodAllInfo")
    Observable<Bean<EmptyBean>> saveFoodAllInfo(@FieldMap Map<String, String> map);


    @Multipart
    @POST("/api/mobile/user/uploadSignatureForAndroid")
    Observable<Bean<EmptyBean>> upSign(@Part("token") RequestBody token, @Part("imgType") RequestBody imgType, @Part MultipartBody.Part fdImg_file);

    @Multipart
    @POST("/api/mobile/user/uploadSignatureForAndroid")
    Observable<Bean<EmptyBean>> uploadSignatureForAndroid(@Part("token") RequestBody token, @Part("imgType") RequestBody imgType, @Part MultipartBody.Part fdImg_file);

    @FormUrlEncoded
    @POST("user/reporttasklist.html")
    Observable<BeanList<ComplaintTask>> getComplaintList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/affair/getAffairTaskList")
    Observable<Bean<TaskInfo>> getAffairTaskList(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("/api/mobile/user/getAppMenu")
    Observable<Bean<Menu>> mainMenu(@FieldMap Map<String, Object> map);

    @FormUrlEncoded
    @POST("admindata/modifylist.html")
    Observable<BeanList<Restore>> getRestoreList(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("admindata/tasklist.html")
    Observable<BeanList<Restore>> getManageTaskList(@FieldMap Map<String, String> map);


    /**
     * 获取巡查任务列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("admindata/elevator_list.html")
    Observable<BeanList<InspectionTask>> getInspectionTaskList(@FieldMap Map<String, String> map);

    /**
     * 维保单位-人员档案列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("weibao/user_list.html")
    Observable<BeanList<UserFiles>> getUserList(@FieldMap Map<String, String> map);


    /**
     * 维保单位-人员档案添加
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("weibao/user_add.html")
    Observable<Bean<String>> newWorkerInfo(@FieldMap Map<String, String> map);

    /**
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("weibao/user_del.html")
    Observable<Bean<String>> delWorkerInfo(@FieldMap Map<String, String> map);

    /**
     * 维保单位-电梯使用单位列表
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("weibao/use_list.html")
    Observable<BeanList<DevManagerBean>> getUseList(@FieldMap Map<String, String> map);

    /**
     * 保养任务
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("weibao/elevator_maintain.html")
    Observable<BeanList<MaintainTask>> getMaintainTaskList(@FieldMap Map<String, String> map);

    /**
     * 获取特种设备类别选择
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/getSpecialTypeList")
    Observable<BeanList<DeviceTypeItem>> getSpecialTypeList(@FieldMap Map<String, String> map);

    /**
     * 选择电梯
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/getElevatorList")
    Observable<Bean<ElevatorInfo>> getElevatorList(@FieldMap Map<String, String> map);

    /**
     * 开始抽检
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/saveTaskElevator")
    Observable<Bean<ChoicedBean>> saveTaskElevator(@FieldMap Map<String, String> map);

    /**
     * 监督任务页面h5
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/getSpecialH5Page")
    Observable<Bean<H5ResultBean>> getSpecialH5Page(@Field("id") String id,@Field("token") String token);

    /**
     * 检查完成
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/saveAllInfo")
    Observable<Bean<EmptyBean>> saveAllInfo(@FieldMap Map<String, Object> map);

    /**
     * 保存现场取证图片
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/elevator/saveXCQZImg")
    Observable<Bean<EmptyBean>> saveXCQZImg(@FieldMap Map<String, Object> map);

    /**
     * 获取执法任务列表
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/enforce/getTaskEnforceList")
    Observable<Bean<PerformBean>> getTaskEnforceList(@FieldMap Map<String, Object> map);

    /**
     * 获取最新apk版本
     * @return
     */
    @FormUrlEncoded
    @POST("/api/mobile/getVersion")
    Observable<Bean<VersionBean>> getVersion(@Field("token") String token);

}
