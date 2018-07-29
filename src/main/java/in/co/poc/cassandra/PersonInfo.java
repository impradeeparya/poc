package in.co.poc.cassandra;

import java.sql.Timestamp;

public class PersonInfo {

  private String firstName;
  private String lastName;
  private double salary;
  private long dob;

  public PersonInfo() {

  }

  public PersonInfo(Person person) {
    this.firstName = person.getKey().getFirstName();
    this.lastName = person.getLastName();
    this.salary = person.getSalary();
    this.dob = Timestamp.valueOf(person.getKey().getDateOfBirth()).getTime();
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public double getSalary() {
    return salary;
  }

  public void setSalary(double salary) {
    this.salary = salary;
  }

  public long getDob() {
    return dob;
  }

  public void setDob(long dob) {
    this.dob = dob;
  }
}
