
/*
@author Koustav Chanda
@quote
@version kafka 2.10

*/


package kafkaAdmin;

import kafka.admin.AdminUtils;
import kafka.admin.RackAwareMode;

import kafka.utils.ZKStringSerializer$;
import kafka.utils.ZkUtils;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.ZkConnection;
import java.util.Properties;




public class test_create_topic {
    public static void main(String args[]){
        ZkClient zkClient = null;
        ZkUtils zkUtils = null;

        try {
            String zookeeperHost = "127.0.0.1:2181";
            int sessionTimeOut = 15 * 1000;
            int connectionTimeOut = 10 * 1000;
            Properties props1 = new Properties();
            props1.put("zookeeperHost","127.0.0.1:2181");
            props1.put("bootstrap.servers","127.0.0.1:9092");
            //KafkaZkClient kafkaZkClient = KafkaZkClient.apply("127.0.0.1:9092",false,15000,
            //       10000,10,Time.SYSTEM,"testMetricGroup", "testMetricType");
            //AdminZkClient ob1 = new AdminZkClient(kafkaZkClient);

            zkClient = new ZkClient(zookeeperHost,sessionTimeOut,connectionTimeOut, ZKStringSerializer$.MODULE$);
            zkUtils = new ZkUtils(zkClient, new ZkConnection(zookeeperHost), false);

            String topicName = "testTopic2";
            int partitions = 3;
            int replication = 1;
            Properties topicConfiguration = new Properties();
            //topicConfiguration.put("cleanup.policy", "delete");

            AdminUtils.createTopic(zkUtils, topicName, partitions, replication, topicConfiguration,
                    RackAwareMode.Disabled$.MODULE$ );
            //AdminUtils.changeTopicConfig(zkUtils, topicName, topicConfiguration);
            //Map<String,Properties> props = AdminUtils.fetchAllEntityConfigs(zkUtils, ConfigType.Topic());
            //Properties props = ob1.fetchEntityConfig(zkUtils, ConfigType.Topic(), topicName)
            //scala.collection.Map props = ob1.fetchAllEntityConfigs(topicName);
            //AdminClient ob = AdminClient.create(props1);
            //ListTopicsResult l = ob.listTopics();
            //System.out.println(props);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (zkClient != null) {
                zkClient.close();
            }
        }
    }
}
