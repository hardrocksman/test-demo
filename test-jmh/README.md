为了能够更好地使用 JMH 的各项功能，下面对 JMH 的基本概念进行讲解：

@BenchmarkMode
用来配置 Mode 选项，可用于类或者方法上，这个注解的 value 是一个数组，可以把几种 Mode 集合在一起执行，如：@BenchmarkMode({Mode.SampleTime, Mode.AverageTime})，还可以设置为 Mode.All，即全部执行一遍。

Throughput：整体吞吐量，每秒执行了多少次调用，单位为 ops/time
AverageTime：用的平均时间，每次操作的平均时间，单位为 time/op
SampleTime：随机取样，最后输出取样结果的分布
SingleShotTime：只运行一次，往往同时把 Warmup 次数设为 0，用于测试冷启动时的性能
All：上面的所有模式都执行一次

@State
通过 State 可以指定一个对象的作用范围，JMH 根据 scope 来进行实例化和共享操作。@State 可以被继承使用，如果父类定义了该注解，子类则无需定义。由于 JMH 允许多线程同时执行测试，不同的选项含义如下：

Scope.Benchmark：所有测试线程共享一个实例，测试有状态实例在多线程共享下的性能
Scope.Group：同一个线程在同一个 group 里共享实例
Scope.Thread：默认的 State，每个测试线程分配一个实例
@OutputTimeUnit
为统计结果的时间单位，可用于类或者方法注解

@Warmup
预热所需要配置的一些基本测试参数，可用于类或者方法上。一般前几次进行程序测试的时候都会比较慢，所以要让程序进行几轮预热，保证测试的准确性。参数如下所示：

iterations：预热的次数
time：每次预热的时间
timeUnit：时间的单位，默认秒
batchSize：批处理大小，每次操作调用几次方法
为什么需要预热？
因为 JVM 的 JIT 机制的存在，如果某个函数被调用多次之后，JVM 会尝试将其编译为机器码，从而提高执行速度，所以为了让 benchmark 的结果更加接近真实情况就需要进行预热。


@Measurement
实际调用方法所需要配置的一些基本测试参数，可用于类或者方法上，参数和 @Warmup 相同。

@Threads
每个进程中的测试线程，可用于类或者方法上。

@Fork
进行 fork 的次数，可用于类或者方法上。如果 fork 数是 2 的话，则 JMH 会 fork 出两个进程来进行测试。

@Param
指定某项参数的多种情况，特别适合用来测试一个函数在不同的参数输入的情况下的性能，只能作用在字段上，使用该注解必须定义 @State 注解。

在介绍完常用的注解后，让我们来看下 JMH 有哪些陷阱。

JMH 陷阱
在使用 JMH 的过程中，一定要避免一些陷阱。

比如 JIT 优化中的死码消除，比如以下代码：

@Benchmark
public void testStringAdd(Blackhole blackhole) {
    String a = "";
    for (int i = 0; i < length; i++) {
        a += i;
    }
}
JVM 可能会认为变量 a 从来没有使用过，从而进行优化把整个方法内部代码移除掉，这就会影响测试结果。

JMH 提供了两种方式避免这种问题，一种是将这个变量作为方法返回值 return a，一种是通过 Blackhole 的 consume 来避免 JIT 的优化消除。

其他陷阱还有常量折叠与常量传播、永远不要在测试中写循环、使用 Fork 隔离多个测试方法、方法内联、伪共享与缓存行、分支预测、多线程测试等，感兴趣的可以阅读 https://github.com/lexburner/JMH-samples 了解全部的陷阱。

JMH 插件
大家还可以通过 IDEA 安装 JMH 插件使 JMH 更容易实现基准测试，在 IDEA 中点击 File->Settings...->Plugins，然后搜索 jmh，选择安装 JMH plugin：


JMH plugin
这个插件可以让我们能够以 JUnit 相同的方式使用 JMH，主要功能如下：

自动生成带有 @Benchmark 的方法
像 JUnit 一样，运行单独的 Benchmark 方法
运行类中所有的 Benchmark 方法
比如可以通过右键点击 Generate...，选择操作 Generate JMH benchmark 就可以生成一个带有 @Benchmark 的方法。

还有将光标移动到方法声明并调用 Run 操作就运行一个单独的 Benchmark 方法。

将光标移到类名所在行，右键点击 Run 运行，该类下的所有被 @Benchmark 注解的方法都会被执行。

JMH 可视化
除此以外，如果你想将测试结果以图表的形式可视化，可以试下这些网站：

JMH Visual Chart：http://deepoove.com/jmh-visual-chart/
JMH Visualizer：https://jmh.morethan.io/
比如将上面测试例子结果的 json 文件导入，就可以实现可视化：


总结

本文主要介绍了性能基准测试工具 JMH，它可以通过一些功能来规避由 JVM 中的 JIT 或者其他优化对性能测试造成的影响。

只需要将待测的业务逻辑用 @Benchmark 注解标识，就可以让 JMH 的注解处理器自动生成真正的性能测试代码，以及相应的性能测试配置文件。