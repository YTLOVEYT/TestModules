package com.example.modules.base.glide;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 优化Glide Gif图片加载优化 ，解决Gif加载卡的问题
 * ModelLoader+ResourceDecoder
 * Created by YinTao on 2018/12/19.
 */
@GlideModule
public class GifGlideModule extends AppGlideModule
{
    @Override
    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry)
    {
        super.registerComponents(context, glide, registry);
    }
}
