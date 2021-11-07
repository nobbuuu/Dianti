package com.qt.dtzf.common;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class WaterFallItemDecoration extends RecyclerView.ItemDecoration {

    private int spacing;
    private int spacingV;
    public WaterFallItemDecoration(int spacing, int spacingV) {
        this.spacing = spacing;
        this.spacingV = spacingV;
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = spacing / 2;
        outRect.right = spacing / 2;
        outRect.top = spacingV / 2;
        outRect.bottom = spacingV / 2;
    }
}
