package in.co.poc.redis.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.co.poc.redis.model.Bank;
import in.co.poc.redis.service.BankSvc;

@RestController
@RequestMapping(value = "/poc/bank")
public class BankController {

  @Autowired
  private BankSvc bankSvc;

  @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Bank> add(@RequestBody Bank bank) {
    return new ResponseEntity<>(bankSvc.add(bank), HttpStatus.OK);
  }

  @GetMapping(value = "/{ifsc}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Bank> fetchByIfsc(@PathVariable("ifsc") String ifsc) {
    return new ResponseEntity<>(bankSvc.fetchByIfsc(ifsc), HttpStatus.OK);
  }
}
