package com.example.modules.android.study.api;

import com.example.modules.android.study.entity.BaseObj;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * ModelService 负责M层具体业务
 * 1.初始化网络
 * 2.发送网络请求
 * 3.返回被观察者
 * Created by YinTao on 2019/1/3.
 */
public class ModelService<T>
{

    public ModelService()
    {

    }

    public interface MethodSelect<T>
    {
        Observable<BaseObj<T>> selectMethod(ApiService service);
    }

    public static <T> Observable<BaseObj<T>> getRemoteData(MethodSelect<T> select, int baseUrlType)
    {
        return select.selectMethod(NetHelper.getDefault(baseUrlType).create(ApiService.class))      //请求数据
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }
}
