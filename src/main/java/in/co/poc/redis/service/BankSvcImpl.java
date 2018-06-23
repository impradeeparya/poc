package in.co.poc.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.poc.redis.model.Bank;
import in.co.poc.redis.repository.RedisRepository;
import in.co.poc.utils.Parser;

@Service
public class BankSvcImpl implements BankSvc {

  private final String BANK_KEY = "banks";

  @Autowired
  private RedisRepository redisRepository;

  @Override
  public Bank add(Bank bank) {
    redisRepository.add(BANK_KEY, bank.getIfsc(), bank);
    return bank;
  }

  @Override
  public Bank remove(Bank bank) {
    redisRepository.delete(BANK_KEY, bank.getIfsc());
    return bank;
  }

  @Override
  public Bank fetchByIfsc(String ifsc) {
    return Parser.fromJson(redisRepository.fetchById(BANK_KEY, ifsc).toString(), Bank.class);
  }
}
