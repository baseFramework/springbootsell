package com.demo.sell.VO;

import lombok.Data;

import javax.management.timer.Timer;

import static javafx.scene.input.KeyCode.T;

/**
 *http 请求返回的最外层对象
 */

@Data
public class ResultVO<T> {

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private  String msg;

    /**
     * 具体内容
     */
    private T data;
}
