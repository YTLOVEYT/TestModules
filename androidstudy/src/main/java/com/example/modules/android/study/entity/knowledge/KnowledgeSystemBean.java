package com.example.modules.android.study.entity.knowledge;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * KnowledgeSystemBean
 * Create By 樱桃 on 2019/2/15 16:48
 */
public class KnowledgeSystemBean implements Parcelable
{
    private int courseId;
    private int id;
    private String name;
    private int order;
    private int parentChapterId;
    private int visible;
    private List<ChildrenBean> children;

    public KnowledgeSystemBean()
    {
    }

    public static class ChildrenBean implements Parcelable
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

        public ChildrenBean()
        {
        }

        protected ChildrenBean(Parcel in)
        {
            this.courseId = in.readInt();
            this.id = in.readInt();
            this.name = in.readString();
            this.order = in.readInt();
            this.parentChapterId = in.readInt();
            this.visible = in.readInt();
        }

        public static final Parcelable.Creator<ChildrenBean> CREATOR = new Parcelable.Creator<ChildrenBean>()
        {
            @Override
            public ChildrenBean createFromParcel(Parcel source)
            {
                return new ChildrenBean(source);
            }

            @Override
            public ChildrenBean[] newArray(int size)
            {
                return new ChildrenBean[size];
            }
        };
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
        dest.writeTypedList(this.children);
    }


    protected KnowledgeSystemBean(Parcel in)
    {
        this.courseId = in.readInt();
        this.id = in.readInt();
        this.name = in.readString();
        this.order = in.readInt();
        this.parentChapterId = in.readInt();
        this.visible = in.readInt();
        this.children = in.createTypedArrayList(ChildrenBean.CREATOR);
    }

    public static final Parcelable.Creator<KnowledgeSystemBean> CREATOR = new Parcelable.Creator<KnowledgeSystemBean>()
    {
        @Override
        public KnowledgeSystemBean createFromParcel(Parcel source)
        {
            return new KnowledgeSystemBean(source);
        }

        @Override
        public KnowledgeSystemBean[] newArray(int size)
        {
            return new KnowledgeSystemBean[size];
        }
    };

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

    public List<ChildrenBean> getChildren()
    {
        return children;
    }

    public void setChildren(List<ChildrenBean> children)
    {
        this.children = children;
    }

    @Override
    public String toString()
    {
        return "KnowledgeSystemBean{" + "courseId=" + courseId + ", id=" + id + ", name='" + name + '\'' + ", order=" + order + ", parentChapterId=" + parentChapterId + ", visible=" + visible + ", children=" + children + '}';
    }
}
