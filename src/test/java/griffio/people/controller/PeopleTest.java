package griffio.people.controller;

import java.util.Collections;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.MapBindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.support.SimpleSessionStatus;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class PeopleTest {

  private People controller;
  private PersonForm form;
  private MapBindingResult bindingResult;

  @Before
  public void setUp() throws Exception {
    controller = new People();
    form = new PersonForm();
    Map<String, PersonForm> target = Collections.singletonMap("form", form);
    bindingResult = new MapBindingResult(target, "form");
  }

  @Test
  public void submit_redirects_to_people() throws Exception {
    SimpleSessionStatus sessionStatus = new SimpleSessionStatus();
    String actual = controller.submit(form, bindingResult, sessionStatus);
    assertThat(actual, equalTo("redirect:/people"));
  }

  @Test
  public void submit_returns_to_person() throws Exception {
    bindingResult.addError(new ObjectError("form", "fail"));
    SimpleSessionStatus sessionStatus = new SimpleSessionStatus();
    String actual = controller.submit(form, bindingResult, sessionStatus);
    assertThat(actual, equalTo("person"));
  }
}