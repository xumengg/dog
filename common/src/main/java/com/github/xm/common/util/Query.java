package com.github.xm.common.util;

import com.baomidou.mybatisplus.plugins.Page;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * @author: XuMeng
 * @create: 2018/7/17 14:57
 * @description:
 **/
public class Query<T> extends Page<T> {

    private static final String PAGE = "page";
    private static final String LIMIT = "limit";
    private static final String ORDER_BY_FIELD = "order";
    private static final String IS_ASC = "isAsc";

    public Query(Map params){
        super(Integer.parseInt(params.getOrDefault(PAGE, "1").toString()),
                Integer.parseInt(params.getOrDefault(LIMIT, "10").toString()));

        if (StringUtils.isNotEmpty((String)params.get(ORDER_BY_FIELD))) {
            this.setOrderByField((String)params.get(ORDER_BY_FIELD));
        }

        boolean is_asc = Boolean.parseBoolean(params.getOrDefault(IS_ASC, Boolean.TRUE).toString());
        this.setAsc(is_asc);

        params.remove(PAGE);
        params.remove(LIMIT);
        params.remove(ORDER_BY_FIELD);
        params.remove(IS_ASC);

        /**
         * 查询参数（ 不会传入到 xml 层，这里是 Controller 层与 service 层传递参数预留 ）
         */
        this.setCondition(params);

        /**
         * 查询总记录数
         */
        this.setSearchCount(true);
    }
}
