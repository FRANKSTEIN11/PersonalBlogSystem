package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class RefRoleMenu implements Serializable {
    private Integer roleId;

    private Integer menuId;

    private static final long serialVersionUID = 1L;
}