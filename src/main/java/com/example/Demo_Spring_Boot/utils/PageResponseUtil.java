package com.example.Demo_Spring_Boot.utils;

public final class PageResponseUtil {
    PageResponseUtil() {}
    public static int normalizePage(int page){
        return Math.max(1, page);
    }
    public static int normalizePageSize(int pageSize){
        return Math.clamp(pageSize, 1, 10);
    }
}
