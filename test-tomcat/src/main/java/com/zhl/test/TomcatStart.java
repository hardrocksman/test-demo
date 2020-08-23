package com.zhl.test;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;

public class TomcatStart {

    public static void main(String[] args) throws Exception {
        String userDir = System.getProperty("user.dir") + File.separatorChar + "test-tomcat"; // 项目目录
        String tomcatBaseDir = userDir + File.separatorChar + "tomcat";
        String webappDir = userDir + File.separatorChar + "target" + File.separatorChar + "classes";

        Tomcat tomcat = new Tomcat();
        tomcat.setBaseDir(tomcatBaseDir);

        Connector connector = new Connector();
        connector.setPort(9999); // 端口号
        connector.setURIEncoding("UTF-8");
        tomcat.getService().addConnector(connector);

        tomcat.addWebapp("/", webappDir);

        tomcat.start();
        tomcat.getServer().await();
    }
}
