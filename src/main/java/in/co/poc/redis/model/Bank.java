package in.co.poc.redis.model;

import java.io.Serializable;

public class Bank implements Serializable {

  private static final long serialVersionUID = 7795259169119467845L;

  private String name;
  private String ifsc;

  public String getName() {
    return name;
  }

  public Bank setName(String name) {
    this.name = name;
    return this;
  }

  public String getIfsc() {
    return ifsc;
  }

  public Bank setIfsc(String ifsc) {
    this.ifsc = ifsc;
    return this;
  }
}
