package com.example.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yenanren
 * @date 2022/4/10 0010
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewsVO implements Serializable {

    private int articleId ;

    private int views;

    private static final long serialVersionUID = 1L;
}