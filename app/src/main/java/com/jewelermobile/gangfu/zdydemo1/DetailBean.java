package com.jewelermobile.gangfu.zdydemo1;


/**
 * Created by User on 2017/7/21.
 */

public class DetailBean {
    private boolean isTitle;
    private String name;
    private String tag=null;

    public DetailBean(boolean isTitle, String name, String tag) {
        this.isTitle = isTitle;
        this.name = name;
        this.tag = tag;
    }



    public boolean isTitle() {
        return isTitle;
    }

    public void setTitle(boolean title) {
        isTitle = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}