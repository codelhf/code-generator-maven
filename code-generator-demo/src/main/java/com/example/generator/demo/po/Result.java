package com.example.generator.demo.po;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Result
 * @Description: Result实体类
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-04-30 23:33:29
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    
    private String phone;
    
    private String prize;
    
    private String grade;
    
    
}