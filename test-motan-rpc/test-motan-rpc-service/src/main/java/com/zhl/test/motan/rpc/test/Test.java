package com.zhl.test.motan.rpc.test;

import com.weibo.api.motan.registry.consul.ConsulConstants;
import com.weibo.api.motan.util.MotanSwitcherUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/5/11.
 */
public class Test {
    public static void main(String[] args){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:motan_server.xml");
        MotanSwitcherUtil.setSwitcher(ConsulConstants.NAMING_PROCESS_HEARTBEAT_SWITCHER, true);
        System.out.println("server start...");
    }
}
