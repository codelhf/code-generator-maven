package com.example.generator.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Title: Prize
 * @Description: Prize实体对象
 * @Company: example
 * @Author: liuhf
 * @CreateTime: 2020/04/13 15:24:00
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prize implements Serializable {
    private static final long serialVersionUID = 1L;

    //奖品表id
    @Id
    @Column(name = "id")
    private Integer id;

    //管理员id
    @Column(name = "admin_id")
    private Integer adminId;

    //奖品等级
    @Column(name = "grade")
    private String grade;

    //奖品名称
    @Column(name = "prize")
    private String prize;

    //奖品排序
    @Column(name = "serial")
    private Integer serial;

    //奖品数量
    @Column(name = "stock")
    private Integer stock;

    //奖品重置数量
    @Column(name = "reset_stock")
    private Integer resetStock;

    //奖品图片
    @Column(name = "image")
    private String image;

    //创建时间
    @Column(name = "create_time")
    private Date createTime;

    //更新时间
    @Column(name = "update_time")
    private Date updateTime;

}
