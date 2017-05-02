package com.think360.schoolshare.fragment.circular;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by think360 on 17/02/17.
 */

public class Example {


    @SerializedName("circular")
    @Expose
    private List<Circular> circularList = null;


    @SerializedName("status")
    @Expose
    private String status;



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Circular> getCircular() {
        return circularList;
    }

    public void setCircular(List<Circular> example) {
        this.circularList = example;
    }
}

 class Circular {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("posted_by")
    @Expose
    private String postedBy;
    @SerializedName("circular_date")
    @Expose
    private String circularDate;
    @SerializedName("created_at")
    @Expose
    private String createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getCircularDate() {
        return circularDate;
    }

    public void setCircularDate(String circularDate) {
        this.circularDate = circularDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
