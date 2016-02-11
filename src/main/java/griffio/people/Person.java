package griffio.people;

import java.io.Serializable;

public class Person implements Serializable {

  static final long serialVersionUID = 1L;

  private final Long id;
  private final String firstName;
  private final String lastName;
  private final String emailAddress;

  public Person(Long id, String firstName, String lastName, String emailAddress) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
  }

  public Long getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }
}
