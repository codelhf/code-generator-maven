package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: AdminDTO
 * @Description: AdminDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 15:24:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //管理员表
    private Integer id;

    //管理员头像
    private String headImg;

    //管理员名称
    private String username;

    //管理员密码
    private String password;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}
