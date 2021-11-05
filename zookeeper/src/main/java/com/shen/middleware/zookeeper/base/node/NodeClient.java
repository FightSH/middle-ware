package com.shen.middleware.zookeeper.base.node;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CountDownLatch;

public class NodeClient implements Watcher {
    private static Logger log = LoggerFactory.getLogger(NodeClient.class);

    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private ZooKeeper zooKeeper;

    public NodeClient() {

    }

    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    public static void main(String[] args) throws Exception {
        NodeClient client = new NodeClient();
        ZooKeeper zooKeeper = new ZooKeeper("47.100.79.39:2181", 5000, client);
        countDownLatch.await();
        client.setZooKeeper(zooKeeper);
        client.createNodeBySyncAPI();
    }
    // 异步创建节点方式
    public void createNodeBySyncAPI() throws InterruptedException, KeeperException {
        String path1 = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL);// 临时节点
        log.info("success create znode:{}", path1);
        String path2 = zooKeeper.create("/zk-test-ephemeral-", "".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL);//临时顺序节点

        log.info("success create znode:{}", path2);

    }

    public void getChildren() {




    }

    public void createNodeByAsyncAPI() throws InterruptedException, KeeperException {
         zooKeeper.create("/zk-test-ephemeral-", "".getBytes(StandardCharsets.UTF_8),
                 ZooDefs.Ids.OPEN_ACL_UNSAFE,
                 CreateMode.EPHEMERAL, new AsyncCallback.StringCallback() {
                     @Override
                     public void processResult(int i, String s, Object o, String s1) {
                         log.info("create path result:    rc:{}---s:{}---ctx:{}---real path name:{}",i,s,o,s1);
                     }
                 },"I am context:");// 临时节点
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (Event.KeeperState.SyncConnected == watchedEvent.getState()) {
            countDownLatch.countDown();
        }

    }
}
