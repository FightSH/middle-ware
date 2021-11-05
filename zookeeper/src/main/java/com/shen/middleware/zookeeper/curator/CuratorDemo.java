package com.shen.middleware.zookeeper.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

public class CuratorDemo {

    public static void main(String[] args) throws Exception {
        createSession();
    }

    public static void createSession() throws Exception {
        // 重试策略
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
//        CuratorFramework client = CuratorFrameworkFactory.newClient("47.100.79.39:2181", 5000, 3000, retryPolicy);
//        client.start();

        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("47.100.79.39:2181")
                .sessionTimeoutMs(5000)
                .namespace("own-rpc")    // 命名空间，实现业务隔离
                .retryPolicy(retryPolicy)
                .build();

        client.start();

        byte[] path = client.getData().forPath("/");
        String res = new String(path);
        System.out.println(res);


    }


}
