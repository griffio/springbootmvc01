package griffio.people.controller;

import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PersonForm implements Serializable {
  static final long serialVersionUID = 1L;

  private UUID formId;

  @Size(min=1, max=99)
  private String firstName;

  @Size(min=1, max=99)
  private String lastName;

  @Email
  @NotEmpty
  private String emailAddress;

  public PersonForm() {
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public UUID getFormId() { return formId; }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() { return emailAddress;
  }
}
