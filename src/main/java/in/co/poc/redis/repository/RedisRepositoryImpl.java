package in.co.poc.redis.repository;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class RedisRepositoryImpl implements RedisRepository {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  private HashOperations hashOperations;

  @PostConstruct
  public void init() {
    hashOperations = redisTemplate.opsForHash();
  }

  @Override
  public Map<Object, Object> fetchAll(String Key) {
    return null;
  }

  @Override
  public void add(String key, Object id, Object o) {
    hashOperations.put(key, id, o);
  }

  @Override
  public void delete(String key, Object id) {
    hashOperations.delete(key, id);
  }

  @Override
  public Object fetchById(String key, Object id) {
    return hashOperations.get(key, id);
  }
}
