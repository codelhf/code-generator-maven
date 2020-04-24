package com.example.generator.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Admin
 * @Description: Admin实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/24 22:31:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    //管理员表
    @Id
    @Column(name = "id")
    private Integer id;

    //管理员头像
    @Column(name = "head_img")
    private String headImg;

    //管理员名称
    @Column(name = "username")
    private String username;

    //管理员密码
    @Column(name = "password")
    private String password;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

}
