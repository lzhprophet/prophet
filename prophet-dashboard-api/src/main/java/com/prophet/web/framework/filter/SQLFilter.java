package com.prophet.web.framework.filter;

import org.apache.commons.lang3.StringUtils;

import com.prophet.web.framework.exception.BusinessException;
import com.prophet.web.framework.global.model.ResultEnum;

/**
 * SQL过滤
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str) throws BusinessException {
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new BusinessException(ResultEnum.SYS00001);
            }
        }

        return str;
    }
}
