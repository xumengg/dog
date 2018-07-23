package com.github.xm.common.util;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author: XuMeng
 * @create: 2018/7/20 16:34
 * @description:
 **/
@Data
public class TreeNode<T>  implements Serializable {
    private static final long serialVersionUID = 2839349763065331350L;

    private Integer id;
    private String name;
    private T data;
    private Map params;
    private List<TreeNode> children;

}
