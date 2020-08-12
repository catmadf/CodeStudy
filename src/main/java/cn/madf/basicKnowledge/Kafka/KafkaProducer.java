package cn.madf.basicKnowledge.Kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author 烛影鸾书
 * @date 2020/8/12 21:34
 * @copyright© 2020
 */
public class KafkaProducer {
    private static Properties getProperties() {
        Map<String, Object> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("acks", "all");
        config.put("retries", 0);
        config.put("batch.size", 16384);
        config.put("linger.ms", 1);
        config.put("buffer.memory", 33554432);
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.ByteArraySerializer");
        config.put("partitioner.class", "cn.madf.basicKnowledge.Kafka.KafkaPartitioner");
        Properties props = new Properties();
        props.putAll(config);
        return props;
    }

    public static void main(String[] args) {

    }

}
