package com.example.Student_Library_Management_System.DTOs;

public class StudentUpdateMobRequestDto {
    private int  id;
    private  String mobNo;

    //dto depend on the api being called
    //add attributes as required
    public StudentUpdateMobRequestDto()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMobNo() {
        return mobNo;
    }

    public void setMobNo(String mobNo) {
        this.mobNo = mobNo;
    }
}
