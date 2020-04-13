package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: UserDTO
 * @Description: UserDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 15:24:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //ID
    private Integer id;

    //管理员id
    private Integer adminId;

    //用户所获奖品ID
    private Integer prizeId;

    //用户头像
    private String headImg;

    //用户名或昵称
    private String username;

    //用户备注
    private String description;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}
