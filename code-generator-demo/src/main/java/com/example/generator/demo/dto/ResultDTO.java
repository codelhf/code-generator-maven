package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ResultDTO
 * @Description: ResultDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2019-05-09 00:02:24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //
    private String username;

    //
    private String phone;

    //
    private String prize;

    //
    private String grade;

}