package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: ResultDTO
 * @Description: ResultDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 16:00:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户名或昵称
    private String username;

    //奖品名称
    private String prize;

    //奖品等级
    private String grade;

}
