package com.example.generator.demo.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: People
 * @Description: People实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:41:38
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class People implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private int id;

    //
    private String username;

    //
    private String phone;

    //
    private int prizeId;

    //
    private Date createTime;

    //
    private Date updateTime;

}
