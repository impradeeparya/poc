package in.co.poc.activemq;

public class RequestDto {

  private String payload;

  public String getPayload() {
    return payload;
  }

  public RequestDto setPayload(String payload) {
    this.payload = payload;
    return this;
  }

  @Override
  public String toString() {
    return "RequestDto{" + "payload=" + payload + '}';
  }
}
