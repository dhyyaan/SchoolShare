package com.think360.schoolshare.model;

/**
 * Created by think360user on 1/2/17.
 */

public class CommentItem {

    private String id;
    private String commenter_name;
    private String time;
    private String comment;
    private int thumbnail;

    public CommentItem() {
    }

    public CommentItem(String id, String commenter_name, String time, String comment, int thumbnail) {
        this.id = id;
        this.commenter_name = commenter_name;
        this.time = time;
        this.comment = comment;
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

   /* public void setId(String id) {
        this.id = id;
    }*/

    public String getCommenterName() {
        return commenter_name;
    }

   /* public void setQuantity(String quantity) {
        this.quantity = quantity;
    }*/

    public String getTime() {
        return time;
    }

   /* public void setprice(String price) {
        this.price = price;
    }*/

    public String getComment() {
        return comment;
    }

   /* public void setTitle(String title) {
        this.title = title;
    }*/

    public int getThumbnail() {
        return thumbnail;
    }

   /* public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }*/
}