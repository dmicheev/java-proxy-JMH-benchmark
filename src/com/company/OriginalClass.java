package com.company;

public class OriginalClass implements IProxy {
    public long originalMethod(int delta) {
        // some payload
        long l1 = System.currentTimeMillis();
        long l2 = System.currentTimeMillis()+delta;
        double d1 = l1 / l2;
        return ((long) d1);

    }
}
