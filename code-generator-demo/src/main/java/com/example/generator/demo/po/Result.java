package com.example.generator.demo.po;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: Result
 * @Description: Result实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 16:56:49
 */
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String phone;
    private String prize;
    private String grade;


    public Result (String username, String phone, String prize, String grade) { 
        this.username = username; 
        this.phone = phone; 
        this.prize = prize; 
        this.grade = grade; 
    }
    public Result () {
        super(); 
    }

    public String getUsername(){ return username;} 

    public void setUsername (String username) {this.username = username;}

    public String getPhone(){ return phone;} 

    public void setPhone (String phone) {this.phone = phone;}

    public String getPrize(){ return prize;} 

    public void setPrize (String prize) {this.prize = prize;}

    public String getGrade(){ return grade;} 

    public void setGrade (String grade) {this.grade = grade;}


}