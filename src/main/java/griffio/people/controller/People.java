package griffio.people.controller;

import griffio.people.Person;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@Controller
@RequestMapping("/people")
@SessionAttributes("personForm")
public class People {

  @RequestMapping(method = RequestMethod.GET)
  public List<Person> list() {
    return Collections.emptyList();
  }

  @RequestMapping(value = "person", method = RequestMethod.GET)
  public String showForm(PersonForm form) {
    return "person";
  }

  @RequestMapping(value = "person", method = RequestMethod.POST)
  public String submit(@Valid PersonForm form, BindingResult bindingResult, SessionStatus status) {
    if (bindingResult.hasErrors()) {
      return "person";
    }
    status.setComplete();
    return redirectToPeople();
  }

  @ExceptionHandler(HttpSessionRequiredException.class)
  public String redirectToPeople() {
    return "redirect:/people";
  }
}
