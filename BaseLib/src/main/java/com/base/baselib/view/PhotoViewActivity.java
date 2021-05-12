package com.base.baselib.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.base.baselib.R;
import com.base.baselib.base.BaseActivity;
import com.base.baselib.glide.GlideUtils;
import com.github.chrisbanes.photoview.PhotoView;

public class PhotoViewActivity extends BaseActivity {

    private static String ImageUrl = "ImageUrl";
    private PhotoView mPhotoView;


    public static void gotoActivity(Context context, String image) {
        Intent intent = new Intent(context, PhotoViewActivity.class);
        intent.putExtra(ImageUrl, image);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_view_activity);

        String image = getIntent().getStringExtra(ImageUrl);
        mPhotoView = findViewById(R.id.photo_view);
        GlideUtils.loadImage(this,image,mPhotoView);

    }

}
