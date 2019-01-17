package com.example.modules.base.event;

/**
 * TestModules
 * Created by YinTao on 2019/1/4.
 */
public class BaseEvent<T>
{
    private int type;
    private T event;

    public BaseEvent(int type, T event)
    {
        this.type = type;
        this.event = event;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public T getEvent()
    {
        return event;
    }

    public void setEvent(T event)
    {
        this.event = event;
    }

    @Override
    public String toString()
    {
        return "BaseEvent{" +
                "type=" + type +
                ", event=" + event +
                '}';
    }
}
