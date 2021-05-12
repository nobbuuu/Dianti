package com.qt.dtzf.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import com.base.baselib.BaseAPP;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.BaseFragment;
import com.base.baselib.bean.EmptyBean;
import com.base.baselib.bean.EventMessage;
import com.base.baselib.bean.HeardInfo;
import com.base.baselib.bean.LoginInfo;
import com.base.baselib.bean.UserInfo;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.glide.GlideUtils;
import com.base.baselib.model.UserAboutModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.AlbumUtils;
import com.base.baselib.utils.BitmapUtils;
import com.base.baselib.utils.HttpUtils;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.base.baselib.view.AlbumActivity;
import com.google.gson.Gson;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.bean.ImgResultBean;
import com.qt.dtzf.ui.ChangePWDActivity;
import com.qt.dtzf.ui.LoginActivity;
import com.qt.dtzf.ui.PersonnelFilesActivity;
import com.qt.dtzf.ui.SignatureActivity;
import com.qt.dtzf.ui.WebDetailsActivity;
import com.qt.dtzf.user_about.UserAction;

import org.greenrobot.eventbus.EventBus;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;


/**
 * 用户信息界面
 */
public class UserInfoFragment extends BaseFragment {

    private AlbumUtils mAlbumUtils;
    private ImageView mHeadIv;
    private TextView mNameTv;
    private TextView mPhone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.user_info_fragment, null);
            initView();
        }
        return mView;
    }

    private void initView() {
        mAlbumUtils = new AlbumUtils((BaseActivity) getActivity(), mAlbumListener);
        mHeadIv = mView.findViewById(R.id.user_head_iv);
        mHeadIv.setOnClickListener(mClickListener);
        mView.findViewById(R.id.user_info_btn).setOnClickListener(mClickListener);

        mNameTv = mView.findViewById(R.id.user_info_name_tv);
        mPhone = mView.findViewById(R.id.user_info_phone_tv);

        View mCl = mView.findViewById(R.id.user_info_manager_item_cl);
        TextView mManagerItemTv = mView.findViewById(R.id.user_info_manager_item_tv);
        String type = "";
        if ("1".equals(type)) {
            mCl.setVisibility(View.GONE);
        } else if ("2".equals(type)) {
            mCl.setVisibility(View.VISIBLE);
            mManagerItemTv.setText("电梯管理");
        } else if ("3".equals(type)) {
            mCl.setVisibility(View.VISIBLE);
            mManagerItemTv.setText("维保人员");
        }
        mView.findViewById(R.id.user_info_manager_item_bg).setOnClickListener(mClickListener);
        mView.findViewById(R.id.user_info_pwd_item_bg).setOnClickListener(mClickListener);
        mView.findViewById(R.id.user_info_sign_item_bg).setOnClickListener(mClickListener);
        initData();
    }

    private void initData() {
        String phone = SpUtils.getString(SpUtilsConstant.userPhone);
        String userName = SpUtils.getString(SpUtilsConstant.userName);
        String headIcon = SpUtils.getString(SpUtilsConstant.userImage);
        mPhone.setText(phone);
        mNameTv.setText(userName);
        GlideUtils.loadImageCircleCrop(getContext(), headIcon, mHeadIv);
    }

    View.OnClickListener mClickListener = v -> {
        switch (v.getId()) {
            case R.id.user_head_iv:
                //选择头像
                mAlbumUtils.showDialog(getChildFragmentManager());
                break;
            case R.id.user_info_pwd_item_bg:
                //修改密码
                ChangePWDActivity.gotoActivity(getActivity());
                break;
            case R.id.user_info_sign_item_bg:
                gotoSignatureActivity();
                break;
            case R.id.user_info_manager_item_bg:
                break;
            case R.id.user_info_btn:
                loginOut();
                break;

        }
    };

    private void gotoSignatureActivity() {
        SignatureActivity.gotoActivity(getActivity(), "");

      /*  showWaitDialog();
        Observable<Bean<UserInfo>> userInfo = UserAboutModel.getInstance().getUserInfo();
        userInfo.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<UserInfo>>() {
                    @Override
                    public void onSuccess(Bean<UserInfo> userInfoBean) {
                        SignatureActivity.gotoActivity(getActivity(), userInfoBean.data.autograph);
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                    }
                });*/

    }

    AlbumUtils.AlbumListener mAlbumListener = (list, isCamera) -> {
        if (list.size() <= 0) return;
        setAvatar(list.get(0).getFilePath());
    };

    /**
     * 跳转网页 并设置 延时
     *
     * @param url
     */
    private void gotoWebActivity(String url) {
        showWaitDialog();
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext(url);
            }
        }).delay(500, TimeUnit.MILLISECONDS).subscribe(new BlockingBaseObserver<String>() {
            @Override
            public void onNext(String s) {
                hideWaitDialog();
                String key = SpUtils.getString(SpUtilsConstant.apiKey);
                String url = s + "?apikey=" + key;
                WebDetailsActivity.gotoActivity(getActivity(), url);
            }

            @Override
            public void onError(Throwable e) {
                hideWaitDialog();
            }
        });
    }

    /**
     * 设置头像
     *
     * @param url
     */
    private void setAvatar(String url) {
        showWaitDialog();

        File file = BitmapUtils.qualityCompress(url);
        HttpUtils.upLoadImgFile(file, "2", new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                hideWaitDialog();
                Log.d("aaa", "头像上传成功");
                String result = response.body().string();
                Log.d("aaa", "result = " + result);

                ImgResultBean imgResultBean = new Gson().fromJson(result, ImgResultBean.class);
                if (imgResultBean != null){
                    String src = imgResultBean.getData().getQnUrl();
                    APP.getUserAction().setHeadImage(src);
                    EventBus.getDefault().post(new EventMessage(0));
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideUtils.loadImageCircleCrop(getContext(), src, mHeadIv);
                        }
                    });
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                hideWaitDialog();
                Log.d("aaa", "头像上传失败");
            }
        });
    }

    /**
     * 推出登陆接口
     */
    private void loginOut() {
        showWaitDialog();
        Observable<Bean<EmptyBean>> loginOut = UserAboutModel.getInstance().loginOut();
        loginOut.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<EmptyBean>>() {
                    @Override
                    public void onSuccess(Bean<EmptyBean> stringBean) {

                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                        APP.getUserAction().logInOut();
                        gotoLoginActivity();
                    }
                });
    }

    private void gotoLoginActivity() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (mAlbumUtils != null) mAlbumUtils.onActivityResult(requestCode, resultCode, data);
    }

}
