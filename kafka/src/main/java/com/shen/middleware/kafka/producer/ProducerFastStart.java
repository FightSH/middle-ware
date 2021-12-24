package com.shen.middleware.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerFastStart {
    public static final String brokerList = "localhost:9092";
    public static final String topic = "topic-demo";


    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        return properties;

    }

    public static void main(String[] args) {
        Properties properties = initConfig();

        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        ProducerRecord<String, String> record = new ProducerRecord<>(topic, "hello.kafka");

        try {
            producer.send(record);
        } catch (Exception e) {
            e.printStackTrace();
        }

        producer.close();

    }
}
