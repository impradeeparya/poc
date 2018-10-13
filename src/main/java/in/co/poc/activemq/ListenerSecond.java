package in.co.poc.activemq;

import java.util.Map;

import javax.jms.JMSException;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import in.co.poc.utils.Parser;

@Component
public class ListenerSecond {

  @JmsListener(destination = "queue_2", containerFactory = "jmsListenerContainerFactory")
  public void receiveMessage(Message<String> jsonMessage) throws JMSException {
    System.out.println("ListenerSecond Received message " + jsonMessage.getPayload() + " at "
        + System.currentTimeMillis() + " ms");
    RequestDto requestDto = Parser.fromJson(jsonMessage.getPayload(), RequestDto.class);
    Map map = Parser.fromJson(requestDto.getPayload(), Map.class);
    System.out.println("Hello " + map.get("name"));
  }

}
