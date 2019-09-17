package com.company;

import org.openjdk.jmh.runner.Runner;

import java.lang.reflect.Proxy;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(value = 1, jvmArgs = {"-Xms1G", "-Xmx1G"})
public class Main {

    // true class
    OriginalClass original;
    // bad proxy
    IProxy proxy;

    @Setup
    public void setup() {
        original = new OriginalClass();
        ProxyHandler handler = new ProxyHandler(original);
        // make proxy
        proxy = (IProxy) Proxy.newProxyInstance(
                OriginalClass.class.getClassLoader(),
                OriginalClass.class.getInterfaces(),
                handler);

    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testOriginal() {
        original.originalMethod(10000);
    }

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void testProxy() {
        proxy.originalMethod(10000);
    }

    public static void main(String[] args) throws Exception {

        Options opt = new OptionsBuilder()
                .include(Main.class.getSimpleName())
                //.forks(1)
                .build();

        new Runner(opt).run();

    }
}
