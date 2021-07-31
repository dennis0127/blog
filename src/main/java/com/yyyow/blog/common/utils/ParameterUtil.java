package com.yyyow.blog.common.utils;

import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * 参数/xss sql过滤
 */
public class ParameterUtil {

    private final static Logger log = LoggerFactory.getLogger(ParameterUtil.class);

    private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
    private static String replacedString="INVALID";
    static {
        String keyStr[] = key.split("\\|");
        for (String str : keyStr) {
            notAllowedKeyWords.add(str);
        }
    }

    public static String cleanXSS(String valueP) {
        if (StrUtil.hasEmpty(valueP))
            return "";
        // You'll need to remove the spaces from the html entities below
        String value = valueP.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        value = cleanSqlKeyWords(value);
        return value;
    }

    private static String cleanSqlKeyWords(String value) {
        String paramValue = value;
        for (String keyword : notAllowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" "+keyword)||paramValue.contains(keyword+" ")||paramValue.contains(" "+keyword+" "))) {
                paramValue = StrUtil.replace(paramValue, keyword, replacedString);
                log.error("已被过滤，因为参数中包含不允许sql的关键词(" + keyword
                        + ")"+";参数："+value+";过滤后的参数："+paramValue);
            }
        }
        return paramValue;
    }

}
