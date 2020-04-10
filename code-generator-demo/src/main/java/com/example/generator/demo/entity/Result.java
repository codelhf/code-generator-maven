package com.example.generator.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Result
 * @Description: Result实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/10 23:39:06
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result implements Serializable {
    private static final long serialVersionUID = 1L;

    //用户名或昵称
    @Column(name = "username")
    private String username;

    //奖品名称
    @Column(name = "prize")
    private String prize;

    //奖品等级
    @Column(name = "grade")
    private String grade;

}
