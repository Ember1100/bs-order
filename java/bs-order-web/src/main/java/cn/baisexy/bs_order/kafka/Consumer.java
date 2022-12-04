package cn.baisexy.bs_order.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

public class Consumer {

    //kafka的监听器，topic为"zhTest"，消费者组为"zhTestGroup"
    @KafkaListener(topics = "lanqiao", groupId = "zhTestGroup")
    public void listenLanqiao(ConsumerRecord<String, String> record, Acknowledgment ack) {
        String value = record.value();
        System.out.println("----------");
        System.out.println(value);
        System.out.println("=======");
        System.out.println(record);
        //手动提交offset
        ack.acknowledge();
    }
}
