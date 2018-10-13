package in.co.poc.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/poc/queue/")
public class ActiveMqController {

  @Autowired
  private MessageProducer messageProducer;

  @PostMapping(value = "push")
  public void push(@RequestBody RequestDto requestDto) {
    messageProducer.produce(requestDto);
  }
}
