package com.think360.schoolshare.utils;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.think360.schoolshare.R;
/**
 * Created by think360 on 20/02/17.
 */

public class ImageDataBinder {

    @BindingAdapter({"app:image_url"})
    public static void loadImage(ImageView view, String url) {

     //   Log.i("III", "Image - " + url);
        Context context = view.getContext();
        Picasso.with(context)
                .load(url)
                  .placeholder(R.mipmap.user)
                // .error(R.drawable.user_placeholder_error)
                .resize(50, 50)
                .into(view);
        //    view.setProfileModel(profile);
    }
}
