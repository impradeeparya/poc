package in.co.poc.redis.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    RedisStandaloneConfiguration redisStandaloneConfiguration =
        new RedisStandaloneConfiguration("localhost", 6379);
    return new JedisConnectionFactory(redisStandaloneConfiguration);
  }


  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));
    template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Object.class));

    return template;
  }
}
