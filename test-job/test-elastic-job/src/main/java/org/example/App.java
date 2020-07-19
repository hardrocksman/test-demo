package org.example;

import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.JobRootConfiguration;
import com.dangdang.ddframe.job.config.dataflow.DataflowJobConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.reg.base.CoordinatorRegistryCenter;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperConfiguration;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) throws UnknownHostException {
        System.out.println("Start...");
        System.out.println(InetAddress.getLocalHost());
        new JobScheduler(createRegistryCenter(), createSimpleJobConfiguration()).init();

    }

    private static CoordinatorRegistryCenter createRegistryCenter() {
        //ZookeeperConfiguration构造方法两个参数，serverLists(连接Zookeeper服务器的列表，包括IP地址和端口号，，多个地址用逗号分隔)和namespace(命名空间)
        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(
                new ZookeeperConfiguration("127.0.0.1:2181", "new-elastic-job-demo"));
        regCenter.init();
        return regCenter;
    }

    private static LiteJobConfiguration createSimpleJobConfiguration() {
        //创建简单作业配置构建器，三个参数为：jobName(作业名称)，cron（作业启动时间的cron表达式），shardingTotalCount(作业分片总数)
        // 定义作业核心配置
        JobCoreConfiguration simpleCoreConfig = JobCoreConfiguration.newBuilder("SimpleJobDemo", "0/15 * * * * ?", 2).build();
        // 定义SIMPLE类型配置
        SimpleJobConfiguration simpleJobConfig = new SimpleJobConfiguration(simpleCoreConfig, JavaSimpleJob.class.getCanonicalName());
        // 定义Lite作业根配置
        JobRootConfiguration simpleJobRootConfig = LiteJobConfiguration.newBuilder(simpleJobConfig).overwrite(true).build();

        return (LiteJobConfiguration) simpleJobRootConfig;

    }

    //配置DataflowJob
    private static void setUpDataflowJob(final CoordinatorRegistryCenter registryCenter) {
        JobCoreConfiguration coreConfiguration = JobCoreConfiguration.newBuilder("JavaDataflowJob", "0/10 * * * * ?", 2).build();
        //数据流作业配置，第三个参数为streamingProcess（是否为流式处理）
        DataflowJobConfiguration dataflowJobConfiguration = new DataflowJobConfiguration(coreConfiguration, JavaDataflowJob.class.getCanonicalName(), true);
        new JobScheduler(registryCenter, LiteJobConfiguration.newBuilder(dataflowJobConfiguration).overwrite(true).build()).init();
    }
}
