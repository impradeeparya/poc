package in.co.poc.activemq;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.jms.TextMessage;

import org.apache.activemq.ScheduledMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import in.co.poc.utils.Parser;

@Component
public class MessageProducer {

  private static Map<Integer, String> queues;

  @Value("${queue.first}")
  private String firstQueue;

  @Value("${queue.second}")
  private String secondQueue;

  @Value("${queue.third}")
  private String thirdQueue;

  @Value("${queue.delay.ms}")
  private long delayMillis;

  private Random random = new Random();

  @PostConstruct
  public void init() {
    queues = new HashMap<>();
    queues.put(0, firstQueue);
    queues.put(1, secondQueue);
    queues.put(2, thirdQueue);
  }

  @Autowired
  private JmsTemplate jmsTemplate;

  public void produce(RequestDto requestDto) {

    String queueName = getQueue(requestDto.getPayload());
    System.out.println("data to produce : " + requestDto + " into queue : " + queueName);
    jmsTemplate.send(queueName, session -> {
      TextMessage message = session.createTextMessage(Parser.toJson(requestDto));
      message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, delayMillis);
      message.setStringProperty("JMSXGroupID", "listener_" + random.nextInt());
      System.out.println("message produced at : " + System.currentTimeMillis() + " ms");
      return message;
    });
  }


  private String getQueue(String payload) {
    int hash = payload.hashCode();
    int key = Math.floorMod(hash, 3);
    System.out.println(" for payload : " + payload + " following hash generated : " + key);
    return queues.get(key);
  }
}
