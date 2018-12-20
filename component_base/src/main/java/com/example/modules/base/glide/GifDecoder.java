package com.example.modules.base.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.bumptech.glide.load.Options;
import com.bumptech.glide.load.ResourceDecoder;
import com.bumptech.glide.load.engine.Resource;

import java.io.IOException;

/**
 * TestModules
 * Created by YinTao on 2018/12/19.
 */
public class GifDecoder implements ResourceDecoder
{
    /** return true */
    @Override
    public boolean handles(@NonNull Object source, @NonNull Options options) throws IOException
    {
        return false;
    }

    /** 解码操作 */
    @Nullable
    @Override
    public Resource decode(@NonNull Object source, int width, int height, @NonNull Options options) throws IOException
    {

        return null;
    }
}
