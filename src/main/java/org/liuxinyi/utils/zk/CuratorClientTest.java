package org.liuxinyi.utils.zk;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2017/6/12 0012.
 */
@Slf4j
public class CuratorClientTest {

    @Test
    public void test() {
        try {

            CuratorFramework client = CuratorFrameworkFactory.newClient(
                    "192.168.52.107:2181",
                    new RetryNTimes(10, 5000)
            );
            client.start();

            //  client.create().creatingParentsIfNeeded()
            //         .forPath("/zkTest", "testData".getBytes());
            List<String> children = client.getChildren().forPath("/");
            log.info(children.toString());
            byte[] data = client.getData().forPath("/zkTest");
            log.info(new String(data));

            client.setData().forPath("/zkTest", "data1234".getBytes());
            data = client.getData().forPath("/zkTest");
            log.info(new String(data));

            client.delete().forPath("/zkTest");

            client.close();

        } catch (Exception e) {
            log.error("failed to test ", e);
        }
    }

}
