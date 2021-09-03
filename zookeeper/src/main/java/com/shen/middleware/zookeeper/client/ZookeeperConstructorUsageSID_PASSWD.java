package com.shen.middleware.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperConstructorUsageSID_PASSWD implements Watcher {
    private static Logger log = LoggerFactory.getLogger(ZookeeperConstructorUsageSimple.class);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("47.100.79.39:2181", 5000, new ZookeeperConstructorUsageSimple());

        countDownLatch.await();
        long sessionId = zooKeeper.getSessionId();
        byte[] sessionPasswd = zooKeeper.getSessionPasswd();

        zooKeeper = new ZooKeeper("47.100.79.39:2181", 5000, new ZookeeperConstructorUsageSimple(), sessionId, sessionPasswd);

        Thread.sleep(Integer.MAX_VALUE);

    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("Receive event:{}", watchedEvent);

        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }

    }
}
