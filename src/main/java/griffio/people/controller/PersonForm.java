package griffio.people.controller;

import java.io.Serializable;
import java.util.UUID;
import javax.validation.constraints.Size;

public class PersonForm implements Serializable {
  static final long serialVersionUID = 1L;

  final private UUID formId;

  @Size(min=1, max=99)
  final private String firstName;

  @Size(min=1, max=99)
  final private String lastName;

  public PersonForm(UUID formId, String firstName, String lastName) {
    this.formId = formId;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public UUID getFormId() { return formId; }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }
}
