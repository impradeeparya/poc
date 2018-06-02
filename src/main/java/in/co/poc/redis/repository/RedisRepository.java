package in.co.poc.redis.repository;

import java.util.Map;

public interface RedisRepository {

  Map<Object, Object> fetchAll(String key);

  void add(String key, Object id, Object o);

  void delete(String key, Object id);

  Object fetchById(String key, Object id);
}
