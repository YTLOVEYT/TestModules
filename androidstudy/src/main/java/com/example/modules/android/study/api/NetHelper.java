package com.example.modules.android.study.api;

import android.text.TextUtils;
import android.util.SparseArray;

import com.example.modules.android.study.BuildConfig;
import com.example.modules.base.glouble.BaseApplication;
import com.example.modules.base.uitls.NetUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * NetHelper网络初始化
 * Created by YinTao on 2019/1/3.
 */
public class NetHelper
{
    private static final int TIME_OUT = 7000;//网络超时时间
    private static final long CACHE_SIZE = 100 * 1024 * 1024;//缓存大小100M
    private static final long CACHE_STALE_SEC = 2 * 24 * 60 * 60;//缓存超时时间2天
    private static final String CONTENT_TYPE = "application/json";//缓存超时时间2天
    //缓存控制 if-only-cache只查询缓存不请求网络 max-stale设置缓存失效时间
    private static final String CACHE_CONTROL = "public, only-if-cached, max-stale=" + CACHE_STALE_SEC;//缓存控制
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    private static SparseArray<NetHelper> netHelperSparseArray = new SparseArray<>();//保存NetHelper

    private static Retrofit retrofit;
    private OkHttpClient httpClient;

    private NetHelper(int baseUrlType)
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) //1.设置日志拦截
        {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger()
            {
                @Override
                public void log(String message)
                {
                    // FIXME: 2019/1/3 日志打印
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addNetworkInterceptor(loggingInterceptor);
        }
        File cacheFile = new File(BaseApplication.getApp().getCacheDir(), "cache");//2.缓存
        Cache cache = new Cache(cacheFile, CACHE_SIZE);
        httpClient = builder.readTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.MILLISECONDS)
                .cache(cache)
                .addInterceptor(cacheInterceptor)
//                .addNetworkInterceptor(null) //天机Stetho—Android调试神器
                .build();
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'ToastUtils'HH:mm:ssZ").serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(getHost(baseUrlType))
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     * NetHelper--Retrofit
     * @param baseUrlType 主网址类型0
     * @return Retrofit
     */
    public static Retrofit getDefault(int baseUrlType)
    {
        NetHelper netHelper = netHelperSparseArray.get(baseUrlType);
        if (netHelper == null)
        {
            netHelper = new NetHelper(baseUrlType);
            netHelperSparseArray.put(baseUrlType, netHelper);
        }
        return retrofit;
    }


    /**
     * 获取主网址
     * @param baseUrlType 0 主网址
     * @return 主网址
     */
    private static String getHost(int baseUrlType)
    {
        String host = "";
        switch (baseUrlType)
        {
            case 0:
                host = "http://www.wanandroid.com/";
                break;
            case 1:
                host = "http://gank.io/api/"; //干活集中营
                break;
            case 2:
                host = "http://kaku.com/";
                break;
        }
        return host;
    }

    /** 拦截器，Response调用 （application中使用） */
    private final Interceptor cacheInterceptor = new Interceptor()
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();//获取缓存控制
            if (!NetUtils.isNetConnected(BaseApplication.getApp()))//没有网，强制缓存策略
            {
                request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl) ? CacheControl.FORCE_NETWORK : CacheControl.FORCE_CACHE)
                        .build();
            }
            Response proceed = chain.proceed(request);
            if (NetUtils.isNetConnected(BaseApplication.getApp())) //有网
            {
                return proceed.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }
            else                                                    //没网
            {
                return proceed.newBuilder()
                        .header("Cache-Control", CACHE_CONTROL)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /** 消息头(响应数据) */
    private final Interceptor headerInterceptor = new Interceptor()
    {
        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request build = chain.request()
                    .newBuilder()
                    .addHeader("Content-Type", CONTENT_TYPE)
                    .build();
            return chain.proceed(build);
        }
    };

}
