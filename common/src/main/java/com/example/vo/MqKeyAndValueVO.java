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
public class MqKeyAndValueVO implements Serializable {

    private String redisKey;

    private String redisValue;

    private static final long serialVersionUID = 1L;
}
