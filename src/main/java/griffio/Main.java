package griffio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@RequestMapping
public class Main extends WebMvcConfigurerAdapter implements EmbeddedServletContainerCustomizer {

  @Override public void customize(ConfigurableEmbeddedServletContainer container) {
    container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/404"));
    container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500"));
  }

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Main.class, args);
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("index");
    registry.addViewController("/404").setViewName("404");
    registry.addViewController("/500").setViewName("500");
    registry.addStatusController("/status", HttpStatus.OK);
  }

  @Bean
  public RequestMappingHandlerAdapter synchronizedSession(RequestMappingHandlerAdapter adapter) {
    adapter.setSynchronizeOnSession(true);
    return adapter;
  }

  @RequestMapping("/message")
  @ResponseBody String message() {
    return "OK";
  }

}
