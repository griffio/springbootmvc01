package griffio.people.controller;

import griffio.people.Person;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/people")
public class People {

  @RequestMapping(method = RequestMethod.GET)
  public List<Person> list() {
    return Collections.emptyList();
  }

  @RequestMapping(value="person", method=RequestMethod.GET)
  public String showForm(PersonForm form) {
    return "person";
  }

  @RequestMapping(value="person", method=RequestMethod.POST)
  public String submit(@Valid PersonForm form, BindingResult bindingResult) {

    if (bindingResult.hasErrors()) {
      return "person";
    }

    return "redirect:/people";
  }
}
