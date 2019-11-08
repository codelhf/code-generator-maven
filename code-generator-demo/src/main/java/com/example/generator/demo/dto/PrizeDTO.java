package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PrizeDTO
 * @Description: PrizeDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-11-09 01:45:36
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrizeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private int id;

    //
    private String grade;

    //
    private String prize;

    //
    private String image;

    //
    private int stock;

    //
    private Date createTime;

    //
    private Date updateTime;

}
