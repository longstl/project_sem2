package com.example.hoang.project_demo_3.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Account {
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("avatar")
    @Expose
    private String avatar;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("salt")
    @Expose
    private String salt;
    @SerializedName("role")
    @Expose
    private String role;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("created_At")
    @Expose
    private String created_At;
    @SerializedName("updated_At")
    @Expose
    private String updated_At;

    public Account() {
    }

    public Account(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    public Account(String phone, String password, String salt,String fullname, String email, String gender) {
        this.phone = phone;
        this.password = password;
        this.salt = salt;
        this.fullname = fullname;
        this.email = email;
        this.gender = gender;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCreated_At() {
        return created_At;
    }

    public void setCreated_At(String created_At) {
        this.created_At = created_At;
    }

    public String getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(String updated_At) {
        this.updated_At = updated_At;
    }
}
