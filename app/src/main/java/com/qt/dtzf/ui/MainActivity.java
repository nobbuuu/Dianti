package com.qt.dtzf.ui;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.base.baselib.base.BaseActivity;
import com.base.baselib.base.BaseFragment;
import com.base.baselib.bean.PerformBean;
import com.base.baselib.bean.VersionBean;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.LogUtils;
import com.base.baselib.utils.PermissionUtils;
import com.base.baselib.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.qt.dtzf.APP;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.HomeFragmentAdapter;
import com.qt.dtzf.dialog.UpdateVersionDialog;
import com.qt.dtzf.ui.fragment.HomeFragment;
import com.qt.dtzf.ui.fragment.UserInfoFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import update.UpdateAppUtils;

/**
 * 主界面
 * 1 确认权限获取
 * 2 为头像选择添加回调
 * 3 定义底部导航样式
 * 4 显示子fragment
 */
public class MainActivity extends BaseActivity {
    private BottomNavigationView mMainBnv;
    List<BaseFragment> mFragments = new ArrayList<>();
    private long mFirstClick = 0;
    private ViewPager mMainFl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPermissionUtils = new PermissionUtils(this, mPermissionCallBack);
        initFragment();
        initView();
        EventBus.getDefault().register(this);
        getLastVersion();
    }

    private void initFragment() {
        HomeFragment homeFragment = new HomeFragment();
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        mFragments.add(homeFragment);
        mFragments.add(userInfoFragment);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mPermissionUtils.checkSelfPermission();
    }

    private void initView() {

        mMainFl = findViewById(R.id.main_fl);
        HomeFragmentAdapter adapter = new HomeFragmentAdapter(getSupportFragmentManager(), 1, mFragments);
        mMainFl.setAdapter(adapter);
        mMainFl.addOnPageChangeListener(mPageChangeListener);
        mMainFl.setOnTouchListener((v, event) -> true);

        mMainBnv = findViewById(R.id.main_bnv);
        adjustNavigationIcoSize(mMainBnv);
        mMainBnv.setOnNavigationItemSelectedListener(mItemSelectedListener);
    }

    BottomNavigationView.OnNavigationItemSelectedListener mItemSelectedListener = item -> {
        LogUtils.e("mMainBnv");
        switch (item.getItemId()) {
            case R.id.main_tab_home:
                mMainFl.setCurrentItem(0);
                break;
            case R.id.main_tab_user:
                mMainFl.setCurrentItem(1);
                break;
        }
        return true;
    };

    ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            mMainBnv.getMenu().getItem(position).setChecked(true);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    PermissionUtils.PermissionCallBack mPermissionCallBack = (code, b) -> {

    };


    /**
     * 定义底部导航样式
     *
     * @param navigation
     */
    private void adjustNavigationIcoSize(BottomNavigationView navigation) {
        if (navigation == null) return;
        navigation.setItemIconTintList(null);

        navigation.setItemTextAppearanceActive(R.style.bottom_selected_text);
        navigation.setItemTextAppearanceInactive(R.style.bottom_normal_text);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            final View iconView = menuView.getChildAt(i).findViewById(R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onAppExit();
            return true;
        }
        return false;
    }

    public void onAppExit() {
        if (System.currentTimeMillis() - mFirstClick > 2000L) {
            mFirstClick = System.currentTimeMillis();
            Utils.showToast("再按一次退出");
            return;
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UserInfoFragment baseFragment = (UserInfoFragment) mFragments.get(1);
        if (baseFragment != null) baseFragment.onActivityResult(requestCode, resultCode, data);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(String message) {
        if (message.equals("reLogin")) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void getLastVersion() {
        Observable<Bean<VersionBean>> observable = WorkModel.getInstance().getVersion();
        observable.compose(this.bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<VersionBean>>() {
                    @Override
                    public void onSuccess(Bean<VersionBean> data) {
                        VersionBean versionBean = data.data;
                        if (versionBean != null) {
                            try {
                                PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                                if (packageInfo != null) {
                                    String versionName = packageInfo.versionName;
                                    Log.d("aaa","versionName = " + versionName);
                                    Integer curVersion = 0;
                                    Integer lastVersion = 0;
                                    if (versionName.contains(".")) {
                                        String replace = versionName.replace(".", "");
                                        curVersion = Integer.valueOf(replace);
                                    }
                                    String dataVersion = versionBean.getVersion();
                                    if (dataVersion != null && dataVersion.contains(".")) {
                                        String replace = dataVersion.replace(".", "");
                                        lastVersion = Integer.valueOf(replace);
                                    }
                                    String apkUrl = versionBean.getApkUrl();
                                    Log.d("aaa","apkUrl = " + apkUrl);
                                    Log.d("aaa","lastVersion = " + lastVersion);
                                    Log.d("aaa","curVersion = " + curVersion);
                                    if (lastVersion > curVersion && !TextUtils.isEmpty(apkUrl)) {
                                        UpdateAppUtils
                                                .getInstance()
                                                .apkUrl(apkUrl)
                                                .updateTitle("发现新版本V" + versionBean.getVersion())
                                                .updateContent(getResources().getString(R.string.app_name) + versionBean.getVersion() + "升级啦~" +"\n"+
                                                        "1.bug修复" +"\n"+
                                                        "2.性能优化")
                                                .update();
                                    }
                                }
                            } catch (PackageManager.NameNotFoundException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onFail(String code, String msg) {
                    }
                });
    }
}
