package com.example.modules.android.study.entity.search;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * SearchHistoryBean
 * Create By 樱桃 on 2019/2/23 17:45
 */
@Entity
public class SearchHistoryBean
{
    @Id(autoincrement = true)
    private long _id;
    private String keyword;
    @Generated(hash = 300580982)
    public SearchHistoryBean(long _id, String keyword) {
        this._id = _id;
        this.keyword = keyword;
    }

    @Generated(hash = 1570282321)
    public SearchHistoryBean() {
    }

    public long get_id()
    {
        return _id;
    }

    public void set_id(long _id)
    {
        this._id = _id;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        this.keyword = keyword;
    }

    @Override
    public String toString()
    {
        return "SearchHistoryBean{" +
                "_id=" + _id +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}
