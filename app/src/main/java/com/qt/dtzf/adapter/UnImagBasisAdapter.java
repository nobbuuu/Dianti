package com.qt.dtzf.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.qt.dtzf.R;

import java.util.List;

public class UnImagBasisAdapter extends RVBaseAdapter<String> {

    public UnImagBasisAdapter(Context context, List<String> data, int layoutIds) {
        super(context, data, layoutIds);
    }

    @Override
    public void onBind(RVBaseHolder holder, String data, int position) {
        holder.setText(R.id.onlyTv,data);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(data)));
            }
        });
    }
}
