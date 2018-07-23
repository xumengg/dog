package com.github.xm.common.util;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author: XuMeng
 * @create: 2018/7/17 10:04
 * @description:
 **/
@Data
public class DataScope {

    /**
     * 限制范围的字段名称
     */
    private String scopeName="dept_id";

    /**
     * 具体的限制范围
     */
    private Set<Integer> scopes;

}
