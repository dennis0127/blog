package com.yyyow.blog.common.enums;

public enum PageEnum {
    PAGESIZE(20);

    private int pageSize;

    PageEnum(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }
}
