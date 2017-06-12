package org.liuxinyi.utils.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@Slf4j
public class CuratorWatcherTest {

    private String ZK_PATH = "/zkTest";

    @Test
    public void test() {
        try {

            CuratorFramework client = CuratorFrameworkFactory.newClient(
                    "192.168.52.107:2181",
                    new RetryNTimes(8, 5000)
            );
            client.start();

            // 2.Register watcher
            PathChildrenCache watcher = new PathChildrenCache(
                    client,
                    ZK_PATH,
                    true    // if cache data
            );

            watcher.getListenable().addListener((client1, event) -> {
                ChildData data = event.getData();
                if (data == null) {
                    System.out.println("No data in event[" + event + "]");
                } else {
                    System.out.println("Receive event: "
                            + "type=[" + event.getType() + "]"
                            + ", path=[" + data.getPath() + "]"
                            + ", data=[" + new String(data.getData()) + "]"
                            + ", stat=[" + data.getStat() + "]");
                }
            });
            watcher.start(PathChildrenCache.StartMode.BUILD_INITIAL_CACHE);

            Thread.sleep(15_000);

            client.close();

        } catch (Exception e) {
            log.error("failed to test ", e);
        }
    }

}
