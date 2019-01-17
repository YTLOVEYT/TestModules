package com.example.modules.android.study.api;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * TestModules
 * Created by YinTao on 2019/1/3.
 */
public interface ApiService
{

    /** 获取banner数据 */
    @GET("banner/json")
    Observable<BaseObj<List<BannerData>>> getBannerData();

    @GET("article/list/{num}/json")
    Observable<BaseObj<ArticleListData>> getArticleListData(@Path("num") int num);


}
