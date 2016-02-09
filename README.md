# springbootmvc01

Basic Spring Boot 1.3.2.RELEASE, Spring MVC, Spring Security

Spring Config setting up some basic overrides for error pages and view mappings for thymeleaf.

~~~
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
  }

  @RequestMapping("/status")
  @ResponseBody String status() {
    return "OK";
  }
}
~~~
