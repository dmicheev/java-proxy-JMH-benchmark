package com.company;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ProxyHandler implements InvocationHandler {
    private final OriginalClass original;

    public ProxyHandler(OriginalClass original) {
        this.original = original;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {
        return method.invoke(original, args);
    }
}
