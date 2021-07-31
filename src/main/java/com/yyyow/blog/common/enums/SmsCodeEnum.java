package com.yyyow.blog.common.enums;

public enum SmsCodeEnum {
    REGIS("注册","reg"),
    FIXPASS("找回密码","fix_pass"),
    MODIFYPHONE("修改手机","modify_phone"),
    LOGIN("登录","login");

    private String name;
    private String value;

    SmsCodeEnum(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
