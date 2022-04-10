package com.example.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yenanren
 * @date 2022/4/8 0008
 * @Description
 */

@Data
public class UserIdAndNameVO implements Serializable {

    private Integer userId;

    private String userName;

    private static final long serialVersionUID = 1L;
}
