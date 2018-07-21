package in.co.poc.kafka;

import org.springframework.kafka.annotation.KafkaListener;

public class KafkaConsumer {

  @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.id}",
      containerFactory = "kafkaListenerContainerFactory")
  public void listen(String message) {
    System.out.println("Received Messasge in group foo: " + message);
  }

}
