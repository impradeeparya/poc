package in.co.poc.activemq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

@Configuration
@EnableJms
public class ActiveMqConfig {

  @Value("${activemq.broker.url}")
  private String activemqBrokerUrl;

  @Value("${activemq.producer.maxThreadPoolSize}")
  private Integer activemqMaxPoolSize;

  @Autowired
  private ConnectionFactory connectionFactory;

  private ActiveMQConnectionFactory activeMQConnectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(activemqBrokerUrl);
    activeMQConnectionFactory.setMaxThreadPoolSize(activemqMaxPoolSize);
    activeMQConnectionFactory.setAlwaysSyncSend(true);
    activeMQConnectionFactory.setNonBlockingRedelivery(true);
    return activeMQConnectionFactory;
  }

  @Bean
  public PooledConnectionFactory connectionFactory() {
    PooledConnectionFactory pooledConnectionFactory =
        new PooledConnectionFactory(activeMQConnectionFactory());
    return pooledConnectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    JmsTemplate template = new JmsTemplate();
    template.setConnectionFactory(connectionFactory());
    template.setMessageConverter(jacksonJmsMessageConverter());
    return template;
  }

  @Bean
  public MessageConverter jacksonJmsMessageConverter() {
    MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.TEXT);
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
    converter.setObjectMapper(objectMapper);
    return converter;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory);
    factory.setConcurrency("1-1");
    return factory;
  }

}
