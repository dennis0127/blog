package com.yyyow.blog.common.enums;

public enum FlashEnum {
    CMCC("CMCC","https://api.253.com/open/flashsdk/mobile-query-m"),
    CUCC("CUCC","https://api.253.com/open/flashsdk/mobile-query-u"),
    CTCC("CTCC","https://api.253.com/open/flashsdk/mobile-query-t");

    private String name;
    private String value;

    FlashEnum(String name, String value){
        this.name = name;
        this.value= value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
