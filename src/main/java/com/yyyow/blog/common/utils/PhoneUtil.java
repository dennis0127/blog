package com.yyyow.blog.common.utils;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 手机号码处理
 */
public class PhoneUtil {

    private static final Logger log = LoggerFactory.getLogger(PhoneUtil.class);

    /**
     * 是否手机号码格式
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        //正则处理 简单验证
        return ReUtil.isMatch("[1][3,4,5,7,8][0-9]{9}", phone);
    }

    public static boolean isPhoneOrMobile(String phone) {
        if (StrUtil.isEmpty(phone)){
            return false;
        }
        //正则处理 简单验证
        if(ReUtil.isMatch("[1][0-9]{10}", phone)){
            return true;
        }
        if(ReUtil.isMatch("0[0-9]{2,3}(-)?[0-9]{7,8}", phone)){
            return true;
        }
        if (ReUtil.isMatch("[0-9]{7,8}",phone)){
            return true;
        }
        return false;
    }

}
