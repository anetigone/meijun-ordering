package com.mo.common.context;

public class BaseContext {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

    public static void setCurrentId(String id) {
        threadLocal.set(id);
    }

    public static String getCurrentId() {
        return threadLocal.get();
    }

    public static void removeCurrentId() {
        threadLocal.remove();
    }
}
