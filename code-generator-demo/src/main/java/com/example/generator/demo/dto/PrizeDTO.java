package com.example.generator.demo.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Title: PrizeDTO
 * @Description: Prize实体类DTO
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 16:56:49
 */
public class PrizeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String grade;
    private String prize;
    private String image;
    private int stock;
    private Date createTime;
    private Date updateTime;


    public PrizeDTO (int id, String grade, String prize, String image, int stock, Date createTime, Date updateTime) { 
        this.id = id; 
        this.grade = grade; 
        this.prize = prize; 
        this.image = image; 
        this.stock = stock; 
        this.createTime = createTime; 
        this.updateTime = updateTime; 
    }
    public PrizeDTO () {
        super(); 
    }

    public int getId(){ return id;} 

    public void setId (int id) {this.id = id;}

    public String getGrade(){ return grade;} 

    public void setGrade (String grade) {this.grade = grade;}

    public String getPrize(){ return prize;} 

    public void setPrize (String prize) {this.prize = prize;}

    public String getImage(){ return image;} 

    public void setImage (String image) {this.image = image;}

    public int getStock(){ return stock;} 

    public void setStock (int stock) {this.stock = stock;}

    public Date getCreateTime(){ return createTime;} 

    public void setCreateTime (Date createTime) {this.createTime = createTime;}

    public Date getUpdateTime(){ return updateTime;} 

    public void setUpdateTime (Date updateTime) {this.updateTime = updateTime;}


}