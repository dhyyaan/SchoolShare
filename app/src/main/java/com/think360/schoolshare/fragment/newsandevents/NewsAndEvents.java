package com.think360.schoolshare.fragment.newsandevents;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by think360 on 27/02/17.
 */

public class NewsAndEvents {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("events")
    @Expose
    private List<Event> events = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public  class Event {


        @SerializedName("id")
        @Expose
        private String id;


        @SerializedName("title")
        @Expose
        private String title;


        @SerializedName("type")
        @Expose
        private String type;


        @SerializedName("description")
        @Expose
        private String description;


        @SerializedName("start_date")
        @Expose
        private String startDate;


        @SerializedName("end_date")
        @Expose
        private String endDate;


        @SerializedName("posted_by")
        @Expose
        private String postedBy;


        @SerializedName("status")
        @Expose
        private String status;

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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStartDate() {
            return startDate;
        }

        public void setStartDate(String startDate) {
            this.startDate = startDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getPostedBy() {
            return postedBy;
        }

        public void setPostedBy(String postedBy) {
            this.postedBy = postedBy;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

    }
}
