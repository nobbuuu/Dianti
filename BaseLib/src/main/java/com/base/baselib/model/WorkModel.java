package com.base.baselib.model;

import android.text.TextUtils;
import android.util.Log;

import com.base.baselib.AppConstant;
import com.base.baselib.bean.ChoiceTaskListBean;
import com.base.baselib.bean.ChoicedBean;
import com.base.baselib.bean.ComplaintTask;
import com.base.baselib.bean.DevManagerBean;
import com.base.baselib.bean.DeviceTypeItem;
import com.base.baselib.bean.ElevatorInfo;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.H5ResultBean;
import com.base.baselib.bean.HeardInfo;
import com.base.baselib.bean.ImageUrl;
import com.base.baselib.bean.ImgBean;
import com.base.baselib.bean.InspectionTask;
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
import com.base.baselib.bean.VersionBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.bean.base.BeanList;
import com.base.baselib.net.ApiManager;
import com.base.baselib.utils.BitmapUtils;
import com.base.baselib.utils.HttpUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * 工作相关的数据提供类
 */
public class WorkModel {

    private static WorkModel model;

    public static WorkModel getInstance() {
        if (model == null) model = new WorkModel();
        return model;
    }

    private WorkModel() {
    }

    /**
     * 获取消息信息数量
     *
     * @return
     */
    public Observable<Bean<TaskNumber>> getTaskMessage() {
        Map<String, String> map = new HashMap<>();
        return ApiManager.getInstance().getBaseApi().getTaskNumber(map);
    }


    /**
     * 获取消息列表
     *
     * @param taskType      任务类型1是日常监督2是双随机日常监督
     * @param category_type 任务类别1待办任务2复合任务
     * @return
     */
    public Observable<Bean<TaskInfo>> getTaskInfo(String taskType, String category_type, int page) {
        Map<String, Object> map = new HashMap<>();
        map.put("otherId", SpUtils.getInt(SpUtilsConstant.otherId));
        map.put("pageNum", "" + page);
        map.put("pageSize", "" + 5);
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        if (category_type.equals("1")) {
            map.put("category_type", category_type);
            map.put("taskType", taskType);
            return ApiManager.getInstance().getBaseApi().getTaskInfo(map);
        } else if (category_type.equals("2")) {
            map.put("category_type", category_type);
            map.put("taskType", taskType);
            return ApiManager.getInstance().getBaseApi().getTaskRecheck(map);
        } else if (category_type.equals("5")) {//待复检
            map.put("fromType", "1");
            return ApiManager.getInstance().getBaseApi().getTaskList(map);
        } else {//已完成
            map.put("fromType", "2");
            return ApiManager.getInstance().getBaseApi().getTaskList(map);
        }
    }

    /**
     * 上传位置信息
     *
     * @return
     */
    public Observable<Bean<String>> upLocation(String type, String region1, String region2) {
        Map<String, String> map = new HashMap<>();
        map.put("type", type);
        map.put("region1", region1);
        map.put("region2", region2);
        return ApiManager.getInstance().getBaseApi().upLocation(map);
    }

    public Observable<Bean<SignBean>> userAutograph(String id, String longitude, String latitude, String signaddress, int signdate, String taskId) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("longitude", longitude);
        map.put("latitude", latitude);
        map.put("signaddress", signaddress);
        map.put("otherId", SpUtils.getInt(SpUtilsConstant.otherId));
        map.put("signdate", signdate);
        map.put("taskId", taskId);
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().userAutograph(map);
    }


    /**
     * 任务详情
     *
     * @param id
     * @param taskType 1:代办任务；2：复检任务
     * @return
     */
    public Observable<Bean<TaskDetail>> getTaskDetail(String id, String taskId, int taskType) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("taskId", taskId);
        map.put("otherId", SpUtils.getInt(SpUtilsConstant.otherId));
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        if (taskType == 1) {
            return ApiManager.getInstance().getBaseApi().getTaskDetail(map);
        } else {
            return ApiManager.getInstance().getBaseApi().getTaskRecheckDetail(map);
        }
    }

    /**
     * 接受任务
     * <p>
     * uid 	int 	[必须] 监管人员用户id
     * id 	int 	[必须] 任务编号id
     * longitude 	string 	[必须] 经度
     * latitude 	string 	[必须] 纬度
     * img 	string 	[选填] 现场取证
     * category_type 	string 	[必须] 任务类别1待办任务2复检任务
     * isquestion 	string 	[必须] 是否发现1是2否
     * address 	string 	[必须] 签到地址
     * elevatorlist 	json 	[必须] 所选的电梯 [{"id":"27"},{"id":"28"},{"id":"29"}]
     * <p>
     * checklist  [选填] 检查记录（json数据）
     * wenshulist  	[选填] 行政执法规范行文书现场记录表（json数据）
     * questionslist  [选填] 一般问题记录表（json数据）
     * recordlist [选填] 现场笔录表（json数据）
     *
     * @param id
     * @return
     */
    public Observable<Bean<String>> acceptTask(String userID, String id, String longitude,
                                               String latitude, String image, String category_type,
                                               String isQuestion, String address,
                                               String elevatorlist, String checklist,
                                               String wenshulist, String questionslist, String recordlist) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("uid", userID);
//        map.put("longitude", longitude);
//        map.put("latitude", latitude);
//        map.put("address", address);
        if (!TextUtils.isEmpty(image)) map.put("img", image);
        map.put("category_type", category_type);
        map.put("isquestion", isQuestion);
        map.put("elevatorlist", elevatorlist);

        if (!TextUtils.isEmpty(checklist)) map.put("checklist", checklist);
        if (!TextUtils.isEmpty(wenshulist)) map.put("wenshulist", wenshulist);
        if (!TextUtils.isEmpty(questionslist)) map.put("questionslist", questionslist);
        if (!TextUtils.isEmpty(recordlist)) map.put("recordlist", recordlist);

        return ApiManager.getInstance().getBaseApi().acceptTask(map);
    }


    /**
     * 监管任务确认
     *
     * @param taskId
     * @param id
     * @return
     */
    public Observable<Bean<TaskInfoItem>> confirmTask(String id, String taskId) {
        Map<String, Object> map = new HashMap<>();
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        map.put(SpUtilsConstant.otherId, SpUtils.getInt(SpUtilsConstant.otherId));
        map.put("id", id);
        map.put("taskId", taskId);
        return ApiManager.getInstance().getBaseApi().confirmTask(map);
    }


    /**
     * 上传图片
     *
     * @param url
     * @return
     */
    public Observable<Bean<ImageUrl>> uploadImg(String url) {

        File file = BitmapUtils.qualityCompress(url);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return ApiManager.getInstance().getBaseFileApi().uploadImg(body);
    }

    public Observable<Bean<ImageUrl>> uploadImg(File file) {

        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
        return ApiManager.getInstance().getBaseFileApi().uploadImg(body);
    }


    /**
     * 批量上传图片
     *
     * @param urls
     * @return
     */
    public Observable<BeanList<ImageUrl>> uploadImgList(List<ImgBean> urls) {
        List<MultipartBody.Part> partList = new ArrayList<>();

        MultipartBody.Part[] parts = new MultipartBody.Part[urls.size()];

        Map<String, RequestBody> map = new HashMap<>();
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i).getFilePath();
            File file = BitmapUtils.qualityCompress(url);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part body = MultipartBody.Part.createFormData("img", file.getName(), requestFile);
            parts[i] = body;
        }


        return ApiManager.getInstance().getBaseFileApi().uploadImgList(parts);
    }


    /**
     * 修改密码
     *
     * @param password
     * @return
     */
    public Observable<Bean<EmptyBean>> updatePwd(String oldPwd, String password) {
        Map<String, String> map = new HashMap<>();
        map.put(SpUtilsConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        map.put("oldPsw", oldPwd);
        map.put("newPsw", password);
        return ApiManager.getInstance().getBaseApi().updatePwd(map);
    }

    /**
     * 获取检查列表类目
     *
     * @param type 1:食监，2：药监
     * @return
     */
    public Observable<Bean<ChoiceTaskListBean>> getPointByType(String id, int type) {
        Map<String, String> map = new HashMap<>();
        map.put(SpUtilsConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        map.put("otherId", SpUtils.getInt(SpUtilsConstant.otherId) + "");
        map.put("id", id);
        map.put("type", type + "");
        return ApiManager.getInstance().getBaseApi().getPointByType(map);
    }

    /**
     * 检查完成（药品）
     *
     * @return
     */
    public Observable<Bean<EmptyBean>> saveDrugAllInfo(String id) {
        Map<String, String> map = new HashMap<>();
        map.put(SpUtilsConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        map.put("id", id);
        return ApiManager.getInstance().getBaseApi().saveDrugAllInfo(map);
    }

    /**
     * 检查完成（食品）
     *
     * @return
     */
    public Observable<Bean<EmptyBean>> saveFoodAllInfo(String id) {
        Map<String, String> map = new HashMap<>();
        map.put(SpUtilsConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        map.put("id", id);
        return ApiManager.getInstance().getBaseApi().saveFoodAllInfo(map);
    }


    /**
     * 上传签名
     *
     * @return
     */
    public Observable<Bean<EmptyBean>> upSign(File file) {
        String token = SpUtils.getString(SpUtilsConstant.apiKey);
        return ApiManager.getInstance().getBaseApi().upSign(HttpUtils.getBody(token), HttpUtils.getBody("1"), HttpUtils.getRequestBodyPart("file", file));
    }

    /**
     * 获取其他任务
     *
     * @param page
     * @return
     */
    public Observable<BeanList<OtherTask>> getOtherTaskList(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "" + page);
        return ApiManager.getInstance().getBaseApi().getOtherTaskList(map);
    }


    /**
     * 获取投诉任务列表
     *
     * @return
     */
    public Observable<BeanList<ComplaintTask>> getComplaintList(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "" + page);
        return ApiManager.getInstance().getBaseApi().getComplaintList(map);
    }

    /**
     * 获取首页信息返回
     *
     * @return
     */
    public Observable<Bean<Menu>> mainMenu() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        String typeId = SpUtils.getString(SpUtilsConstant.userType);
        Log.d("aaa", "typeId = " + typeId);
        map.put("typeId", typeId);
        return ApiManager.getInstance().getBaseApi().mainMenu(map);
    }

    /**
     * 监管任务列表
     *
     * @return
     */
    public Observable<BeanList<Restore>> getRestoreList(String type_id, int page) {
        Map<String, String> map = new HashMap<>();
        map.put("type_id", type_id);
        map.put("page", "" + page);
        return ApiManager.getInstance().getBaseApi().getRestoreList(map);
    }

    public Observable<BeanList<Restore>> getManageTaskList(String type_id, int page) {
        Map<String, String> map = new HashMap<>();
        map.put("type_id", type_id);
        map.put("page", "" + page);
        return ApiManager.getInstance().getBaseApi().getManageTaskList(map);
    }

    /**
     * 自检巡查
     *
     * @return
     */
    public Observable<BeanList<InspectionTask>> getInspectionTaskList(int page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", "" + page);
        return ApiManager.getInstance().getBaseApi().getInspectionTaskList(map);
    }


    /**
     * 维保单位-人员档案列表
     *
     * @param tag
     * @param page
     * @return
     */

    public Observable<BeanList<UserFiles>> getUserList(String tag, String page) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(tag)) {
            map.put("nameno", tag);
        }
        map.put("page", page);
        return ApiManager.getInstance().getBaseApi().getUserList(map);
    }


    /**
     * 维保单位-人员档案列表-人员档案删除
     *
     * @param id
     * @return
     */
    public Observable<Bean<String>> delWorkerInfo(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        return ApiManager.getInstance().getBaseApi().delWorkerInfo(map);
    }


    /**
     * 添加工作人员提交
     *
     * @return
     */
    public Observable<Bean<String>> newWorkerInfo(String name, String phone, String cardId, String workId,
                                                  String card_0, String card_1, String workCard_0, String workCard_1, String workCard_2) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        map.put("phone", phone);
        map.put("idcard", cardId);
        map.put("lacard", workId);
        map.put("idimg1", card_0);
        map.put("idimg2", card_1);

        map.put("laimg1", workCard_0);
        map.put("laimg2", workCard_1);
        map.put("holdimg", workCard_2);
        return ApiManager.getInstance().getBaseApi().newWorkerInfo(map);
    }

    /**
     * 维保单位-电梯使用单位列表
     *
     * @param page
     * @return
     */
    public Observable<BeanList<DevManagerBean>> getUseList(String page) {
        Map<String, String> map = new HashMap<>();
        map.put("page", page);
        return ApiManager.getInstance().getBaseApi().getUseList(map);
    }


    /**
     * 维保单位-保养任务列表
     *
     * @param tag
     * @param page
     * @return
     */
    public Observable<BeanList<MaintainTask>> getMaintainTaskList(String tag, String page) {
        Map<String, String> map = new HashMap<>();
        if (!TextUtils.isEmpty(tag)) {
            map.put("numberno", tag);
        }
        map.put("page", page);
        return ApiManager.getInstance().getBaseApi().getMaintainTaskList(map);
    }

    public Observable<BeanList<DeviceTypeItem>> getSpecialTypeList(String taskDistributeId) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "SPECIAL_EQUIPMENT");
        map.put("taskDistributeId", taskDistributeId);
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().getSpecialTypeList(map);
    }

    public Observable<Bean<ElevatorInfo>> getElevatorList(String taskId) {
        Map<String, String> map = new HashMap<>();
        map.put("id", taskId);
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().getElevatorList(map);
    }

    public Observable<Bean<ChoicedBean>> saveTaskElevator(String taskId, String ids) {
        Map<String, String> map = new HashMap<>();
        map.put("id", taskId);
        map.put("ids", ids);
        map.put("otherId", SpUtils.getInt(SpUtilsConstant.otherId) + "");
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().saveTaskElevator(map);
    }

    public Observable<Bean<H5ResultBean>> getSpecialH5Page(String id) {
        return ApiManager.getInstance().getBaseApi().getSpecialH5Page(id, SpUtils.getString(SpUtilsConstant.apiKey));
    }

    public Observable<Bean<EmptyBean>> saveXCQZImg(String id, String xcqzImg) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("xcqzImg", xcqzImg);
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        Log.d("aaa", "id = " + id);
        Log.d("aaa", "xcqzImg = " + xcqzImg);
        return ApiManager.getInstance().getBaseApi().saveXCQZImg(map);
    }

    public Observable<Bean<EmptyBean>> saveAllInfo(String id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("token", SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().saveAllInfo(map);
    }

    public Observable<Bean<PerformBean>> getTaskEnforceList(int pageNum) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", "" + pageNum);
        map.put("pageSize", "" + 5);
        map.put(AppConstant.token, SpUtils.getString(SpUtilsConstant.apiKey));
        return ApiManager.getInstance().getBaseApi().getTaskEnforceList(map);
    }

    public Observable<Bean<VersionBean>> getVersion() {
        return ApiManager.getInstance().getBaseApi().getVersion(SpUtils.getString(SpUtilsConstant.apiKey));
    }

}
