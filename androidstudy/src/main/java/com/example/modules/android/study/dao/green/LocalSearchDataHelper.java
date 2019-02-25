package com.example.modules.android.study.dao.green;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.modules.android.study.dao.DaoMaster;
import com.example.modules.android.study.dao.DaoSession;
import com.example.modules.android.study.dao.ISearchHistoryDbHelper;
import com.example.modules.android.study.dao.SearchHistoryBeanDao;
import com.example.modules.android.study.entity.search.SearchHistoryBean;
import com.example.modules.base.glouble.BaseApplication;

import org.greenrobot.greendao.AbstractDaoMaster;

import java.util.Iterator;
import java.util.List;

/**
 * LocalSearchDataHelper
 * Create By 樱桃 on 2019/2/25 16:01
 */
public class LocalSearchDataHelper implements ISearchHistoryDbHelper
{
    private static volatile LocalSearchDataHelper instance;
    private DaoSession daoSession;
    private static String DB_NAME = "SearchHistory";

    private LocalSearchDataHelper()
    {
    }

    public static LocalSearchDataHelper getInstance()
    {
        if (instance == null)
        {
            synchronized (LocalSearchDataHelper.class)
            {
                if (instance == null)
                {
                    instance = new LocalSearchDataHelper();
                }
            }
        }
        return instance;
    }

    private void initGreenDao(Context context)
    {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(context, DB_NAME);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    private DaoSession getDaoSession()
    {
        if (daoSession == null)
        {
            initGreenDao(BaseApplication.getApp());
        }
        return daoSession;
    }

    @Override
    public List<SearchHistoryBean> addHistoryData(String input)
    {
        SearchHistoryBeanDao historyBeanDao = getDaoSession().getSearchHistoryBeanDao();
        List<SearchHistoryBean> historyBeans = historyBeanDao.loadAll();
        SearchHistoryBean newHistoryBean = new SearchHistoryBean(System.currentTimeMillis(), input);
        for (SearchHistoryBean bean : historyBeans)
        {
            if (bean.getKeyword().equals(input))
            {
                historyBeans.remove(bean);
                historyBeans.add(newHistoryBean);
                historyBeanDao.deleteAll();
                historyBeanDao.insertInTx(historyBeans);
                return historyBeans;
            }
        }
        if (historyBeans.size() < 10)
        {
            historyBeanDao.insert(newHistoryBean);
        }
        else
        {
            historyBeans.remove(0);
            historyBeans.add(newHistoryBean);
            historyBeanDao.deleteAll();
            historyBeanDao.insertInTx(historyBeans);
        }
        return historyBeans;
    }

    @Override
    public void clearHistoryData()
    {
        SearchHistoryBeanDao beanDao = getDaoSession().getSearchHistoryBeanDao();
        beanDao.deleteAll();
    }

    @Override
    public List<SearchHistoryBean> loadAllHistoryData()
    {
        SearchHistoryBeanDao beanDao = getDaoSession().getSearchHistoryBeanDao();
        return beanDao.loadAll();
    }
}
