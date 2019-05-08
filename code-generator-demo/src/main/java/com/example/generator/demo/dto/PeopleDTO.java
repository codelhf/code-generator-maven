package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PeopleDTO
 * @Description: PeopleDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-05-09 00:02:24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PeopleDTO implements Serializable {
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