package com.yyyow.blog.common.config;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XssConfig {

    public static final Logger log = LoggerFactory.getLogger(XssConfig.class);
    private static String key = "and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+";
    private static Set<String> notAllowedKeyWords = new HashSet<String>(0);
    private static String replacedString = "INVALID";

    static {
        String keyStr[] = key.split("\\|");
        for (String str : keyStr) {
            notAllowedKeyWords.add(str);
        }
    }

    public static <T> T getInstance(Object o) {
        Class<?> clazz = o.getClass();

        //map接参
        if (clazz.getName().contains("map") || clazz.getName().contains("Map")) {
            Map<String, Object> map = new HashMap<>();
            for (Map.Entry<String, Object> entry : ((Map<String, Object>) o).entrySet()) {
                if (null != entry.getValue() && !StrUtil.hasBlank(entry.getValue().toString())) {
                    map.put(entry.getKey(), xssEncode(entry.getValue().toString()));
                } else {
                    map.put(entry.getKey(), "");
                }
            }
            return (T) map;
        }

        //jsonobject接参
        if (clazz.getName().contains("object") || clazz.getName().contains("Object")) {
            JSONObject object = new JSONObject();
            for (Map.Entry entry : ((JSONObject) o).entrySet()) {
                if (null != entry.getValue() && !StrUtil.hasBlank(entry.getValue().toString())) {
                    object.put(entry.getKey().toString(), xssEncode(entry.getValue().toString()));
                } else {
                    object.put(entry.getKey().toString(), "");
                }
            }
            return (T) object;
        }

        //实体对象接参
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (null != field.get(o) && !StrUtil.hasBlank(field.get(o).toString()) && field.getType().toString().contains("String")) {
                    field.set(o, xssEncode(field.get(o).toString()));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return (T) o;
    }


    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符
     *
     * @param s
     * @return
     */
    public static String xssEncode(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        s = s.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
        s = s.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        s = s.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        s = s.replaceAll("'", "& #39;");
        s = ignoreCaseReplace(s, "eval\\((.*)\\)", "");
        s = ignoreCaseReplace(s, "[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        s = ignoreCaseReplace(s, "script", "");
        s = cleanSqlKeyWords(s);
        return cleanSqlKeyWords(s);
    }

    public static String cleanSqlKeyWords(String value) {


        String paramValue = value;
        for (String keyword : notAllowedKeyWords) {
            if (paramValue.length() > keyword.length() + 4
                    && (paramValue.contains(" " + keyword) || paramValue.contains(keyword + " ") || paramValue.contains(" " + keyword + " "))) {
                paramValue = StrUtil.replace(paramValue, keyword, replacedString);
                log.error("已被过滤，因为参数中包含不允许sql的关键词(" + keyword
                        + ")" + ";参数：" + value + ";过滤后的参数：" + paramValue);
            }
        }
        return paramValue;
    }

    /**
     * java实现不区分大小写替换
     *
     * @param source
     * @param oldstring
     * @param newstring
     * @return
     */
    public static String ignoreCaseReplace(String source, String oldstring,
                                           String newstring) {
        Pattern p = Pattern.compile(oldstring, Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(source);
        String ret = m.replaceAll(newstring);
        return ret;
    }
}
