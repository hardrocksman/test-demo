package org.example;

import java.io.IOException;

import com.sun.tools.attach.*;

public class AttachTest {

    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        // attach方法参数为目标应用程序的进程号
        VirtualMachine vm = VirtualMachine.attach("2952");
        // 请用你自己的agent绝对地址，替换这个
        vm.loadAgent("D:/work/read_src/test-demo/test-agent/agent-core/target/agent-core-1.0-SNAPSHOT-jar-with-dependencies.jar");
    }
}
