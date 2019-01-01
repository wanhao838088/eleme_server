package com.wanhao.eleme.bean.user;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by LiuLiHao on 2019/1/1 0001 下午 05:53
 * @author : LiuLiHao
 * 描述： 用户信息
 */
@Data
public class UserInfo implements Serializable {
    private String id;
    private String name;
    private Integer age;

}
