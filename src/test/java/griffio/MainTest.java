package griffio;

import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Main.class)
@WebAppConfiguration
public class MainTest {

  @Resource
  WebApplicationContext applicationContext;
  private MockMvc mockMvc;

  @Before
  public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.applicationContext).build();
  }

  @Test
  public void status_route_ok_content() throws Exception {
    this.mockMvc.perform(get("/status"))
        .andExpect(status().isOk());
  }

  @Test
  public void message_route_ok_content() throws Exception {
    this.mockMvc.perform(get("/message"))
        .andExpect(status().isOk())
        .andExpect(content().string(new Main().message()));
  }


  @Test
  public void webjars_route_ok() throws Exception {
    this.mockMvc.perform(get("/webjars/skeleton-css/2.0.4/css/skeleton.css"))
        .andExpect(status().isOk());
  }

  @Test
  public void route_is_404() throws Exception {
    this.mockMvc.perform(get("/bogus"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void index_route_ok() throws Exception {
    this.mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(view().name("index"));
  }

  @Test
  public void get_people_route_ok() throws Exception {
    this.mockMvc.perform(get("/people"))
        .andExpect(status().isOk());
  }

  @Test
  public void post_person_route_ok() throws Exception {
    this.mockMvc.perform(post("/people/person")
        .param("firstName", "First")
        .param("lastName", "Last")
        .param("emailAddress", "Email"))
        .andExpect(status().isOk());
  }
  @Test
  public void post_person_route_has_error() throws Exception {
    this.mockMvc.perform(post("/people/person")
        .param("firstName", "")
        .param("lastName", "")
        .param("emailAddress", ""))
        .andDo(print())
        .andExpect(model().
            attributeHasFieldErrors("personForm", "emailAddress", "firstName", "lastName"))
        .andExpect(status().isOk());
  }
}