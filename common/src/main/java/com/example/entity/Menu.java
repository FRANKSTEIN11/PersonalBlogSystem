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
public class Menu implements Serializable {
    /**
     * 菜单
     */
    @TableId(type = IdType.AUTO)
    private Integer menuId;

    private String menuName;

    private String menuUrl;

    /**
     * 权限字符串
     */
    private String menuAuth;

    /**
     * 菜单的层级，按钮是0，列表就是1
     */
    private Integer parentId;

    private static final long serialVersionUID = 1L;
}