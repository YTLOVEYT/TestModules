package com.example.modules.android.study.api;

import com.example.modules.android.study.entity.BaseObj;
import com.example.modules.android.study.entity.article.ArticleListData;
import com.example.modules.android.study.entity.banner.BannerData;
import com.example.modules.android.study.entity.knowledge.KnowledgeSystemBean;
import com.example.modules.android.study.entity.navgation.NavigationBean;
import com.example.modules.android.study.entity.profile.ProjectClassifyBean;
import com.example.modules.android.study.entity.profile.ProjectListBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

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

    /** 获取知识体系数据 */
    @GET("tree/json")
    Observable<BaseObj<List<KnowledgeSystemBean>>> getKnowledgeSystemData();

    /** 获取导航数据 */
    @GET("navi/json")
    Observable<BaseObj<List<NavigationBean>>> getNavigationListData();

    /** 项目分类 */
    @GET("project/tree/json")
    Observable<BaseObj<List<ProjectClassifyBean>>> getProjectClassifyData();

    /** 项目类别数据 */
    @GET("project/list/{page}/json")
    Observable<BaseObj<ProjectListBean>> getProjectListData(@Path("page") int page, @Query("cid") int cid);

}
