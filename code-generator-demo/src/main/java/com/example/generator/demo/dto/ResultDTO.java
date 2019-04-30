package com.example.generator.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: ResultDTO
 * @Description: Result实体类DTO
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 16:56:49
 */
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String phone;
    private String prize;
    private String grade;


    public ResultDTO (String username, String phone, String prize, String grade) { 
        this.username = username; 
        this.phone = phone; 
        this.prize = prize; 
        this.grade = grade; 
    }
    public ResultDTO () {
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