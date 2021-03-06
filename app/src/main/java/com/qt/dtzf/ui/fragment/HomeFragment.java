package com.qt.dtzf.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.base.baselib.base.BaseFragment;
import com.base.baselib.bean.EventMessage;
import com.base.baselib.bean.Menu;
import com.base.baselib.bean.base.Bean;
import com.base.baselib.model.WorkModel;
import com.base.baselib.net.DefaultObserver;
import com.base.baselib.utils.SpUtils;
import com.base.baselib.utils.SpUtilsConstant;
import com.qt.dtzf.R;
import com.qt.dtzf.adapter.HomeAdapter;
import com.qt.dtzf.ui.DevManagerActivity;
import com.qt.dtzf.ui.InspectionTaskActivity;
import com.qt.dtzf.ui.MaintainTaskActivity;
import com.qt.dtzf.ui.PerformTaskListActivity;
import com.qt.dtzf.ui.PersonnelFilesActivity;
import com.qt.dtzf.ui.SingleTaskListActivity;
import com.qt.dtzf.ui.RestoreListActivity;
import com.qt.dtzf.ui.TaskListActivity;
import com.qt.dtzf.ui.WebDetailsActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.internal.observers.BlockingBaseObserver;
import io.reactivex.schedulers.Schedulers;


public class HomeFragment extends BaseFragment {

    private HomeAdapter mAdapter;
    private ImageView mHeadIv;
    private SmartRefreshLayout mHomeSrl;
    private TextView mName;
    private TextView mTaskTitleTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = View.inflate(getContext(), R.layout.home_fragment, null);
            initView();
            EventBus.getDefault().register(this);
        }
        initHead();

        return mView;
    }

    private void initView() {
        mName = mView.findViewById(R.id.home_title_name_tv);
        mTaskTitleTv = mView.findViewById(R.id.home_task_title_tv);

        mHeadIv = mView.findViewById(R.id.home_head_iv);
        mAdapter = new HomeAdapter(getActivity());
        mAdapter.addItemClickListener(mItemClickListener);

        mHomeSrl = mView.findViewById(R.id.home_srl);
        mHomeSrl.setOnRefreshListener(mRefreshListener);

        RecyclerView mHomeRv = mView.findViewById(R.id.home_rv);
        mHomeRv.setLayoutManager(new GridLayoutManager(getContext(), 6));
        mHomeRv.setAdapter(mAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        showWaitDialog();
        initHead();
        getMessage();

    }

    private void getMessage() {
        Observable<Bean<Menu>> message = WorkModel.getInstance().mainMenu();
        message.compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Bean<Menu>>() {
                    @Override
                    public void onSuccess(Bean<Menu> taskBean) {
                        Menu data = taskBean.data;
                        mName.setText(data.getTitleName());
                        mTaskTitleTv.setText(data.getHeaderTitle());
                        mAdapter.setData(data);
                    }

                    @Override
                    public void onStop() {
                        super.onStop();
                        hideWaitDialog();
                        mHomeSrl.finishRefresh();
                    }
                });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onGetMessage(EventMessage message) {
//        if (message.code == 0) {
//            initHead();
//        }
//        if (message.code == 1) {
//            getMessage();
//        }
    }

    private void initHead() {
    }

    OnRefreshListener mRefreshListener = refreshLayout -> {
        getMessage();
    };

    HomeAdapter.ItemClickListener mItemClickListener = item -> {
        Menu.ListBean.MenuSonBean data = item.item;
        switch (data.getId()) {
            case "1":
                //????????????
                TaskListActivity.gotoActivity(getActivity(), "1");
                break;
            case "2":
                //????????????
                TaskListActivity.gotoActivity(getActivity(), "2");
                break;
            case "3":
                //??????
                SingleTaskListActivity.gotoActivity(getActivity(), "3");
                //????????????
//                TaskComplaintListActivity.gotoActivity(getActivity());
                break;
            case "4":
                //????????????
//                gotoWebActivity(data.getUrl(),null);
                break;
            case "5":
                //???????????????
                SingleTaskListActivity.gotoActivity(getActivity(), "5");
//                gotoWebActivity(data.getUrl(),"1");
                break;
            case "6":
                //???????????????
                SingleTaskListActivity.gotoActivity(getActivity(), "6");
//                gotoWebActivity(data.getUrl(),"2");
                break;
            case "7":
                //???????????????
                gotoWebActivity(data.getUrl(),"3");
                break;
            case "8":
                //???????????????
                RestoreListActivity.gotoActivity(getActivity(), "1");
                break;
            case "9":
                //??????????????????
                RestoreListActivity.gotoActivity(getActivity(), "2");
                break;
            case "11":
                //????????????????????????
                InspectionTaskActivity.gotoActivity(getActivity());
                break;
            case "10":
                //????????????
                break;

            case "16":
                //????????????
                gotoWebActivity(data.getUrl(),null);
                break;
            case "17":
                //????????????
                PersonnelFilesActivity.gotoActivity(getActivity());
                break;
            case "18":
                //????????????
                DevManagerActivity.gotoActivity(getActivity());
                break;
            case "19":
                //????????????
                MaintainTaskActivity.gotoActivity(getActivity());
                break;

            case "26":
                PerformTaskListActivity.gotoActivity(getActivity());
                break;


            default:
//
//                Intent intent = new Intent(getActivity(), UploadImageActivity.class);
//                startActivity(intent);

                gotoWebActivity(data.getUrl(),null);
                break;
        }
        // WebDetailsActivity.gotoActivity(getActivity(), mAdapter.mTask.weiexamine);
    };


    /**
     * ???????????? ????????? ??????
     * type 1?????????2?????????3???????????????
     *
     * @param url
     */
    private void gotoWebActivity(String url, String type) {
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
                String url = "";
                if (TextUtils.isEmpty(type)) {
                    url = s + "?apikey=" + key;
                } else {
                    url = s + "?apikey=" + key + "&type=" + type;
                }

                WebDetailsActivity.gotoActivity(getActivity(), url);
            }

            @Override
            public void onError(Throwable e) {
                hideWaitDialog();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
