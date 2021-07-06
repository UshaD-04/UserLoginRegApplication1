package com.usha.userloginregapplication;

import android.graphics.Bitmap;

public class UserModel {
    private int id;
    private String first_name;
    private String last_name;
    private String address;
    private String username;
    private String password;
    private byte[] profilePic;
    private String dob;
    private String questions;
    private String sec_ans;
    UserModel(){}

    public UserModel(int id, String first_name, String last_name, String address, String username,
                     String password, byte[] profilePic, String dob, String questions,String sec_ans) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.address = address;
        this.username = username;
        this.password = password;
        this.profilePic = profilePic;
        this.dob = dob;
        this.questions = questions;
        this.sec_ans = sec_ans;
    }

    public String getSec_ans() {
        return sec_ans;
    }

    public void setSec_ans(String sec_ans) {
        this.sec_ans = sec_ans;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(byte[] profilePic) {
        this.profilePic = profilePic;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getQuestions() {
        return questions;
    }

    public void setQuestions(String questions) {
        this.questions = questions;
    }
}
