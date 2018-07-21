package in.co.poc.kafka;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/poc/kafka")
public class kafkaController {

  @Autowired
  private KafkaProducer kafkaProducer;

  @Value("${kafka.topic.name}")
  private String topicName;

  @PostMapping(value = "/produce")
  public ResponseEntity<String> product(@RequestBody Map<String, String> requestData) {

    kafkaProducer.sendMessage(topicName, requestData.toString());

    return new ResponseEntity<>("data produced", HttpStatus.OK);
  }
}
