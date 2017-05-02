package com.think360.schoolshare.model;

import android.util.Log;

/**
 * Created by think360 on 18/02/17.
 */

public class ProfileModel {
    // @SerializedName("id")
    public  String id;
    // @SerializedName("profile_as")
    public  String profile_as;
    // @SerializedName("first_name")
    public  String first_name;
    // @SerializedName("middle_name")
    public  String middle_name;
    //  @SerializedName("last_name")
    public  String last_name;
    // @SerializedName("nick_name")
    public  String nick_name;
    // @SerializedName("gender")
    public  String gender;
    // @SerializedName("marital_status")
    public  String marital_status;
    // @SerializedName("role_in_school")
    public  String role_in_school;
    //  @SerializedName("date_of_birth")
    public  String date_of_birth;
    //  @SerializedName("addtional_info")
    public String additional_information;
    //  @SerializedName("admission_no")
    public  String admission_no;
    //  @SerializedName("batch_of")
    public  String batch_of;
    // @SerializedName("year_of_joining")
    public  String year_of_joining;
    // @SerializedName("year_of_leaving")
    public  String year_of_leaving;
    //  @SerializedName("house")
    public String house;
    //  @SerializedName("image_id")
    public  String image_id;
    //  @SerializedName("image_url")
    public  String image_url;
    //  @SerializedName("resume_id")
    public  String resume_id;
    //  @SerializedName("resume")
    public  String resume;
    //  @SerializedName("organization_name")
    public  String organization_name;
    // @SerializedName("industry")
    public  String industry;
    // @SerializedName("designation")
    public  String designation;
    //  @SerializedName("additional_informationp")
    public  String additional_informationp;
    //  @SerializedName("linkdin_profile_link")
    public  String linkdin_profile_link;
    //  @SerializedName("additional_infop")
    public  String additional_infop;
    //  @SerializedName("chapter_id")
    public  String chapter_id;
    // @SerializedName("created_at")
    public  String created_at;
    /////////////////////////////
    //  @SerializedName("address")
    public  String address;
    //  @SerializedName("country_id")
    public  String country_id;
    //  @SerializedName("country")
    public  String country;
    //  @SerializedName("city_id")
    public  String city_id;
    //  @SerializedName("city")
    public  String city;
    //  @SerializedName("state_id")
    public  String state_id;
    //  @SerializedName("state")
    public  String state;
    // @SerializedName("postal_code")
    public  String postal_code;
    // @SerializedName("phone_no")
    public  String mobile;
    // @SerializedName("mobile")
    public  String phone_no;
    // @SerializedName("email")
    public  String email;
    //  @SerializedName("type")
    public  String type;
    //  @SerializedName("address")
    public  String paddress;
    //  @SerializedName("country_id")
    public  String pcountry_id;
    //  @SerializedName("country")
    public  String pcountry;
    //  @SerializedName("city_id")
    public  String pcity_id;
    //  @SerializedName("city")
    public  String pcity;
    //  @SerializedName("state_id")
    public  String pstate_id;
    //  @SerializedName("state")
    public  String pstate;
    // @SerializedName("postal_code")
    public  String ppostal_code;
    // @SerializedName("phone_no")
    public  String pmobile;
    // @SerializedName("mobile")
    public  String pphone_no;

    public ProfileModel() {
    }
//For Personal Info Edit Fragment
   public ProfileModel(String first_name, String middle_name,
                 String last_name,String nick_name, String gender, String marital_status,
                 String date_of_birth,String additional_information,String image_url){

       this.first_name= first_name;
       this.middle_name = middle_name;
       this.last_name = last_name;
       this.nick_name = nick_name;
       this.gender = gender;
       this.marital_status= marital_status;
       this.date_of_birth = date_of_birth;
       this.additional_information = additional_information;
       this.image_url = image_url;
    }
    //For Personal Info Edit Fragment
    public ProfileModel(String id,String profile_as,String first_name, String middle_name,
                        String last_name,String nick_name,
                        String date_of_birth,String additional_information,String image_id,String image_url){

        this.id = id;
        this.profile_as = profile_as;
        this.first_name= first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nick_name = nick_name;
       // this.gender = gender;
        //this.role_in_school = role_in_school;
       // this.marital_status= marital_status;
        this.date_of_birth = date_of_birth;
        this.additional_information = additional_information;
        this.image_id = image_id;
        this.image_url = image_url;
    }

    public ProfileModel(String address, String country, String city, String state,
                        String postal_code,String mobile,String phone_no, String email) {

        this.address = address;
        this.country = country;
        this.city = city;
        this.state = state;
        this.postal_code = postal_code;
        this.mobile = mobile;
        this.phone_no = phone_no;
        this.email = email;

    }

    //contact info submit fragment
    public ProfileModel(String address,String country_id, String country, String state_id, String state,String city_id,String city,
                        String postal_code,String mobile,String phone_no, String email,String paddress, String pcountry_id,String pcountry,String pstate_id, String pstate,
            String pcity_id,String pcity,String ppostal_code,String pmobile,String pphone_no) {

        this.address = address;
        this.country_id = country_id;
        this.country = country;
        this.state_id = state_id;
        this.state = state;
        this.city_id = city_id;
        this.city = city;
        this.postal_code = postal_code;
        this.mobile = mobile;
        this.phone_no = phone_no;
        this.email = email;


        this.paddress = paddress;

        this.pcountry_id = pcountry_id;
        this.pcountry = pcountry;
        this.pstate_id = pstate_id;
        this.pstate = pstate;
        this.pcity_id = pcity_id;
        this.pcity = pcity;
        this.ppostal_code = ppostal_code;
        this.pmobile = pmobile;
        this.pphone_no = pphone_no;


    }
    //For Academic Info Edit Fragment
    public ProfileModel( String admission_no,
                        String batch_of, String year_of_joining, String year_of_leaving,String house,
                         String chapter_id ) {


        this.admission_no = admission_no;
        this.batch_of = batch_of;
        this.year_of_joining = year_of_joining;
        this.year_of_leaving = year_of_leaving;
        this.house = house;
        this.chapter_id = chapter_id;

    }
    //Professional info edit fragment
    public ProfileModel( String organization_name, String industry,String designation,
                         String linkdin_profile_link,String resume_id,String resume,String additional_informationp) {

        this.organization_name = organization_name;
        this.industry = industry;
        this.designation = designation;
        this.linkdin_profile_link = linkdin_profile_link;
         this.resume_id = resume_id;
         this.resume = resume;
        this.additional_informationp = additional_informationp;
    }

    /* public ProfileModel(String id, String profile_as, String first_name, String middle_name,
                        String last_name,String nick_name, String gender, String marital_status,
                        String role_in_school, String date_of_birth,String additional_information, String admission_no,
                        String batch_of, String year_of_joining, String year_of_leaving,String house,
                        String image_id, String resume_id, String organization_name,
                        String industry,String designation, String linkdin_profile_link, String additional_informationp,
                        String chapter_id, String created_at,String address, String country_id, String city_id, String state_id,
                        String postal_code,String phone_no, String email, String type) {

        this.id = id;
        this. profile_as = profile_as;
        this.first_name= first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nick_name = nick_name;
        this.gender = gender;
        this.marital_status= marital_status;
        this.role_in_school = role_in_school;
        this.date_of_birth = date_of_birth;
        this.additional_information = additional_information;
        this.admission_no = admission_no;
        this.batch_of = batch_of;
        this.year_of_joining = year_of_joining;
        this.year_of_leaving = year_of_leaving;
        this.house = house;
        this.image_id = image_id;
        this.resume_id= resume_id;
        this.organization_name = organization_name;
        this.industry = industry;
        this.designation = designation;
        this.linkdin_profile_link = linkdin_profile_link;
        this.additional_informationp = additional_informationp;
        this.chapter_id = chapter_id;
        this.created_at = created_at;
        this.address = address;
        this.country_id = country_id;
        this.city_id = city_id;
        this.state_id = state_id;
        this.postal_code = postal_code;
        this.phone_no = phone_no;
        this.email = email;
        this.type = type;
    }

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }*/

     /* public String getCommenterName() {
        return commenter_name;
    }

  public void setQuantity(String quantity) {
        this.quantity = quantity;
    }*/

  /*  public String getTime() {
        return time;
    }

    public void setprice(String price) {
        this.price = price;
    }*/

  /*  public String getComment() {
        return comment;
    }

    public void setTitle(String title) {
        this.title = title;
    }*/

  /*  public int getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(int thumbnail) {
        this.thumbnail = thumbnail;
    }*/


}
