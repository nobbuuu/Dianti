package com.base.baselib.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.base.baselib.R;
import com.trello.rxlifecycle3.components.support.RxFragment;

public class ErrorFragment extends RxFragment {

    private RefreshListener mRefreshListener;

    public static ErrorFragment newInstance() {
        return new ErrorFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = View.inflate(getContext(), R.layout.error_view_layout, null);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.error_refresh_tv).setOnClickListener(mClickListener);
    }

    View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mRefreshListener != null) mRefreshListener.onRefresh();
        }
    };


    public void addRefreshListener(RefreshListener listener) {
        mRefreshListener = listener;
    }

    public interface RefreshListener {
        void onRefresh();
    }
}
