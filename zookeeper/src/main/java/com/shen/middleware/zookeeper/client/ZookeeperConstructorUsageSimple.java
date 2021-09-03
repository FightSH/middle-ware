package com.shen.middleware.zookeeper.client;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZookeeperConstructorUsageSimple implements Watcher {
    private static Logger log = LoggerFactory.getLogger(ZookeeperConstructorUsageSimple.class);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);


    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("47.100.79.39:2181", 5000, new ZookeeperConstructorUsageSimple());

        log.info(String.valueOf(zooKeeper.getState()));

        countDownLatch.await();

        log.info("Zookeeper session established");



    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        log.info("receive event:{}",watchedEvent);
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }
    }
}
