package com.yyyow.blog.common.utils;


import com.yyyow.blog.common.config.Context;

/**
 * 取线程本地对象封装 方便context
 * 用户登录验证时，拦截器可以把登录验证成功的用户信息放在线程本地对象里，以方便后续使用
 *
 * @Author: lxd
 */
public final class ThreadLocalUtil {

    private static ThreadLocal<Context> local = ThreadLocal.withInitial(Context::backGround);

    /**
     *
     * @return null 为没有设置
     */
    public static Context getContext(){
        return local.get();
    }

    /**
     * 移除 以保证没有内存泄露
     */
    public static void remove(){
        local.remove();
    }

}