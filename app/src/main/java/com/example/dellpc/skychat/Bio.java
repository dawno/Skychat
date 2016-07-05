package com.example.dellpc.skychat;



import java.util.*;

public class Bio {

    private String title, thumbnailUrl,status;





    public Bio(String name, String thumbnailUrl,  String status) {
        this.title = name;
        this.thumbnailUrl = thumbnailUrl;

        this.status = status;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
