package com.example.modules.android.study.entity.profile;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectClassifyBean
 * Create By 樱桃 on 2019/2/18 16:14
 */
public class ProjectClassifyBean implements Parcelable
{
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;
    private List<?> children;

    public int getCourseId()
    {
        return courseId;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getOrder()
    {
        return order;
    }

    public void setOrder(int order)
    {
        this.order = order;
    }

    public int getParentChapterId()
    {
        return parentChapterId;
    }

    public void setParentChapterId(int parentChapterId)
    {
        this.parentChapterId = parentChapterId;
    }

    public int getVisible()
    {
        return visible;
    }

    public void setVisible(int visible)
    {
        this.visible = visible;
    }

    public List<?> getChildren()
    {
        return children;
    }

    public void setChildren(List<?> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "ProjectClassifyBean{" + "courseId=" + courseId + ", id=" + id + ", name='" + name + '\'' + ", order=" + order + ", parentChapterId=" + parentChapterId + ", visible=" + visible + ", children=" + children + '}';
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        dest.writeInt(this.courseId);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.order);
        dest.writeInt(this.parentChapterId);
        dest.writeInt(this.visible);
    }

    public ProjectClassifyBean()
    {
    }

    protected ProjectClassifyBean(Parcel in)
    {
        this.courseId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.parentChapterId = in.readInt();
        this.visible = in.readInt();
    }

    public static final Parcelable.Creator<ProjectClassifyBean> CREATOR = new Parcelable.Creator<ProjectClassifyBean>()
    {
        @Override
        public ProjectClassifyBean createFromParcel(Parcel source)
        {
            return new ProjectClassifyBean(source);
        }

        @Override
        public ProjectClassifyBean[] newArray(int size)
        {
            return new ProjectClassifyBean[size];
        }
    };
}
