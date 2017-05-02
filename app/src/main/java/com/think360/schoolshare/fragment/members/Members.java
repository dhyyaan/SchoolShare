package com.think360.schoolshare.fragment.members;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by think360 on 23/02/17.
 */

public class Members {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("role_id")
    @Expose
    private String roleId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("middle_name")
    @Expose
    private String middleName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("nick_name")
    @Expose
    private String nickName;
    @SerializedName("profile_as")
    @Expose
    private String profileAs;
    @SerializedName("mobile_no")
    @Expose
    private String mobileNo;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("marital_status")
    @Expose
    private String maritalStatus;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country_id")
    @Expose
    private String countryId;
    @SerializedName("state_id")
    @Expose
    private String stateId;
    @SerializedName("city_id")
    @Expose
    private String cityId;
    @SerializedName("postal_code")
    @Expose
    private String postalCode;
    @SerializedName("role_in_school")
    @Expose
    private String roleInSchool;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("admission_no")
    @Expose
    private String admissionNo;
    @SerializedName("batch_of")
    @Expose
    private String batchOf;
    @SerializedName("year_of_joining")
    @Expose
    private String yearOfJoining;
    @SerializedName("organization_name")
    @Expose
    private String organizationName;
    @SerializedName("house")
    @Expose
    private String house;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("resume_file")
    @Expose
    private String resumeFile;
    @SerializedName("year_of_leaving")
    @Expose
    private String yearOfLeaving;
    @SerializedName("industry")
    @Expose
    private String industry;
    @SerializedName("additional_infop")
    @Expose
    private String additionalInfop;
    @SerializedName("linkdin_profile_link")
    @Expose
    private String linkdinProfileLink;
    @SerializedName("designation")
    @Expose
    private String designation;
    @SerializedName("chapter_id")
    @Expose
    private String chapterId;
    @SerializedName("verification_code")
    @Expose
    private String verificationCode;
    @SerializedName("user_status")
    @Expose
    private String userStatus;
    @SerializedName("created")
    @Expose
    private String created;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getProfileAs() {
        return profileAs;
    }

    public void setProfileAs(String profileAs) {
        this.profileAs = profileAs;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getRoleInSchool() {
        return roleInSchool;
    }

    public void setRoleInSchool(String roleInSchool) {
        this.roleInSchool = roleInSchool;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAdmissionNo() {
        return admissionNo;
    }

    public void setAdmissionNo(String admissionNo) {
        this.admissionNo = admissionNo;
    }

    public String getBatchOf() {
        return batchOf;
    }

    public void setBatchOf(String batchOf) {
        this.batchOf = batchOf;
    }

    public String getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(String yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getResumeFile() {
        return resumeFile;
    }

    public void setResumeFile(String resumeFile) {
        this.resumeFile = resumeFile;
    }

    public String getYearOfLeaving() {
        return yearOfLeaving;
    }

    public void setYearOfLeaving(String yearOfLeaving) {
        this.yearOfLeaving = yearOfLeaving;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getAdditionalInfop() {
        return additionalInfop;
    }

    public void setAdditionalInfop(String additionalInfop) {
        this.additionalInfop = additionalInfop;
    }

    public String getLinkdinProfileLink() {
        return linkdinProfileLink;
    }

    public void setLinkdinProfileLink(String linkdinProfileLink) {
        this.linkdinProfileLink = linkdinProfileLink;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getChapterId() {
        return chapterId;
    }

    public void setChapterId(String chapterId) {
        this.chapterId = chapterId;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

}