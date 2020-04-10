package com.example.generator.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: User
 * @Description: User实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/11 00:24:48
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID
    @Id
    @Column(name = "id")
    private Integer id;

    //管理员id
    @Column(name = "admin_id")
    private Integer adminId;

    //用户所获奖品ID
    @Column(name = "prize_id")
    private Integer prizeId;

    //用户头像
    @Column(name = "head_img")
    private String headImg;

    //用户名或昵称
    @Column(name = "username")
    private String username;

    //用户备注
    @Column(name = "description")
    private String description;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

}
