package com.example.adity.whatsup;

public class Item {
    int iconid;
    String title;
    String descript;

    public Item(int iconid, String title, String descript) {
        this.setIconid(iconid);
        this.setTitle(title);
        this.setDescript(descript);
    }

    public int getIconid() {
        return iconid;
    }

    public void setIconid(int iconid) {
        this.iconid = iconid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescript() {
        return descript;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }
}
