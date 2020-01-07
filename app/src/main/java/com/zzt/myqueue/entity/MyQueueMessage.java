package com.zzt.myqueue.entity;

import java.util.Comparator;

/**
 * @author: zeting
 * @date: 2019/12/26
 * 队列消息实体类
 */
public class MyQueueMessage implements Comparator<MyQueueMessage> {

    private String name; // 对象名称
    private String id; // 对象id
    private int type; // 对象类型

    private int level; // 对象优先级别
    private long createTime; // 创建时间

    private String contextName; // 传入对应的context类名字，在指引对话框中需要使用到这个对象，好比较，是不是需要指引

    private Object object;// 对象实体内容

    public MyQueueMessage() {
        createTime = System.currentTimeMillis();
    }

    public MyQueueMessage(int type) {
        this.type = type;
        createTime = System.currentTimeMillis();
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getContextName() {
        return contextName;
    }

    public void setContextName(String contextName) {
        this.contextName = contextName;
    }

    @Override
    public int compare(MyQueueMessage o1, MyQueueMessage o2) {
        if (o2.getLevel() == o1.getLevel()) {
            return (int) (o2.getCreateTime() - o1.getCreateTime());
        }
        return o2.getLevel() - o1.getLevel();
    }

    @Override
    public String toString() {
        return "MyQueueMessage{" +
                "type=" + type +
                ", level=" + level +
                ", createTime=" + createTime +
                '}';
    }
}
