package com.example.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author yenanren
 * @date 2022/4/10 0010
 * @Description
 */

@Data
public class MqKeyAndValueVO implements Serializable {

    private String redisKey;

    private String redisValue;

    private static final long serialVersionUID = 1L;
}
