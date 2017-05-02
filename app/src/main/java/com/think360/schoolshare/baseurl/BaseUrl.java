package com.think360.schoolshare.baseurl;

/**
 * Created by think360user on 24/12/16.
 */
public class BaseUrl {

    //Base Url to get data from server
    public static final String BASE_URL2="http://think360.in/alumni/alumni_api/index.php/12345/";
    public static final String BASE_URL="http://think360.co/prabhat/alumni/alumni_api/index.php/12345/";

     //Constent to faetch desired data
    public static final String get_country = "get-country";
    public static final String get_state = "get-state?";
    public static final String country_id = "country_id=";
    public static final String get_city = "get-city?";
    public static final String state_id = "state_id=";

    public static final String get_chapter = "get-chapter";
    public static final String registration = "user-register";
    public static final String upload_image = "upload-image";
    public static final String upload_resume = "upload-resume";

    public static final String get_industry = "get-industry";
    public static final String login = "login";

//geting current user by id

    public static final String get_member_detail = "get-member-detail?";
    public static final String user_id = "user_id=";

   //all member by chapter id
    public static final String get_member = "get-member?";
    public static final String chapter_id = "chapter_id=";

    public static final String GET_EVENTS = BASE_URL2+"get-events";
    //get event detail
    public static final String get_event_detail = "get-event-detail?";
    public static final String event_id = "event_id=";
    //for posting abuse report
    public static final String abuse_report = "post-abuse-report";
    public static final String edit_personal_info = "post-abuse-report";
}
