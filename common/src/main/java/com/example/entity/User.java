package com.example.entity;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;

    private String username;

    private String password;

    private String nickname;

    private String realname;

    private Integer age;

    private String telephone;

    /**
     * 1是男,0是女
     */
    private Integer sex;

    private String profile;

    private String area;

    /**
     * 注册时间
     */
    private Date registrationTime;

    private static final long serialVersionUID = 1L;
}