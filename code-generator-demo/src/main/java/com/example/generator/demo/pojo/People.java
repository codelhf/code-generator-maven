package com.example.generator.demo.pojo;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: People
 * @Description: People实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-05-09 00:02:24
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