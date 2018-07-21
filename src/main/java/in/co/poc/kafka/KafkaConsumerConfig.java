package in.co.poc.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import static in.co.poc.kafka.KafkaConstants.KAFKA_GROUP_ID;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

  @Autowired
  private Environment environment;


  private Supplier<List<String>> bootStrapServers = () -> {
    String serverStr = environment.getProperty(KafkaConstants.BOOTSTRAP_SERVERS_CONFIG);
    String[] serverArr = serverStr.split(",");
    List<String> list = new ArrayList<>();
    for (String str : serverArr) {
      str = str.trim();
      if (StringUtils.isNotBlank(str)) {
        list.add(str);
      }
    }
    if (list.size() == 0) {
      throw new RuntimeException("No Kafka servers listed");
    }
    return list;
  };

  @Bean
  public ConsumerFactory<String, String> consumerFactory() {
    Map<String, Object> props = new HashMap<>();
    props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers.get());
    props.put(ConsumerConfig.GROUP_ID_CONFIG, environment.getProperty(KAFKA_GROUP_ID));
    props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    return new DefaultKafkaConsumerFactory<>(props);
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {

    ConcurrentKafkaListenerContainerFactory<String, String> factory =
        new ConcurrentKafkaListenerContainerFactory<>();
    factory.setConsumerFactory(consumerFactory());
    return factory;
  }

  @Bean
  public KafkaConsumer kafkaConsumer() {
    return new KafkaConsumer();
  }
}
