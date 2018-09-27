package com.demo.OV;

import lombok.Data;

/**
 * @program: CRCTool
 * @description:
 * @author: xw
 * @create: 2018-09-27 22:24
 */
@Data
public class Result<T> {
    /**
     * 标识码
     */
    private ResultCode code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 数据
     */
    private T data;
}
