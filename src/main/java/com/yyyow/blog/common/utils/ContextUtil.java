package com.yyyow.blog.common.utils;

import com.yyyow.blog.common.config.Context;
import com.yyyow.blog.common.config.YYException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 获取登录token，header 等信息
 * @author lxd
 */
public class ContextUtil {

    private static Logger log = LoggerFactory.getLogger(ContextUtil.class);


    /**
     * 取到上下文或 背景上下文
     * @return
     * @throws YYException
     */
    public static Context getContext() throws YYException {
        Context context = ThreadLocalUtil.getContext();
        if (context == null) {
            return Context.backGround();
        }
        return context;
    }
}
