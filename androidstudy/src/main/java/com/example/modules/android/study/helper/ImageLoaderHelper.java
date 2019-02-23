package com.example.modules.android.study.helper;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.modules.android.study.R;

/**
 * ImageLoaderHelper
 * Create By 樱桃 on 2019/2/18 21:01
 */
public class ImageLoaderHelper
{
    public static int IMG_LOADING = R.drawable.ic_image_loading;
    public static int IMG_ERROR = R.drawable.ic_empty_picture;
    private static volatile ImageLoaderHelper instance;

    private ImageLoaderHelper()
    {

    }

    public static ImageLoaderHelper getInstance()
    {
        if (instance == null)
        {
            synchronized (ImageLoaderHelper.class)
            {
                if (instance == null)
                {
                    instance = new ImageLoaderHelper();
                }
            }
        }
        return instance;
    }

    /**
     * Glide加载图片
     * @param context   context
     * @param url       url
     * @param imageView imageView
     */
    public void load(Context context, String url, ImageView imageView)
    {
        if (context != null && !TextUtils.isEmpty(url) && imageView != null)
        {
            RequestOptions options = new RequestOptions().placeholder(IMG_LOADING)
                    .centerCrop()
                    .error(IMG_ERROR)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(context).load(url).apply(options).into(imageView);

        }
    }
}
