package com.example.generator.demo.dto;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * @Title: PrizeDTO
 * @Description: PrizeDTO对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/24 22:31:44
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrizeDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    //奖品表id
    private Integer id;

    //管理员id
    private Integer adminId;

    //奖品等级
    private String grade;

    //奖品名称
    private String prize;

    //奖品排序
    private Integer serial;

    //奖品数量
    private Integer stock;

    //奖品重置数量
    private Integer resetStock;

    //奖品图片
    private String image;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

}
