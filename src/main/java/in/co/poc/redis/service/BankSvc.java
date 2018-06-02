package in.co.poc.redis.service;

import in.co.poc.redis.model.Bank;

public interface BankSvc {

    Bank add(Bank bank);
    Bank remove(Bank bank);
    Bank fetchByIfsc(String ifsc);
}
