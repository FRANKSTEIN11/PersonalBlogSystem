package com.example.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Role implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer roleId;

    private String roleName;

    private static final long serialVersionUID = 1L;
}