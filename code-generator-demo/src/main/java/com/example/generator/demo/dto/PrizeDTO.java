package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PrizeDTO
 * @Description: Prize实体类DTO
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 23:33:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrizeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    
    private String grade;
    
    private String prize;
    
    private String image;
    
    private int stock;
    
    private Date createTime;
    
    private Date updateTime;
    
    
}