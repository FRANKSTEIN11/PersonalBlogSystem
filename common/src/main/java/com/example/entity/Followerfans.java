package com.example.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * @author 
 * 
 */
@Data
public class Followerfans implements Serializable {
    private Integer followerId;

    private Integer fansId;

    private static final long serialVersionUID = 1L;
}