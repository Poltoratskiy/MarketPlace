//package ru.marketplace;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.builder.SpringApplicationBuilder;
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.web.WebApplicationInitializer;
//import org.springframework.web.context.ContextLoaderListener;
//import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
//import org.springframework.web.servlet.DispatcherServlet;
//import ru.marketplace.config.WebConfig;
//import ru.marketplace.service.UserService;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRegistration;
//
//
//@SpringBootApplication
////@ComponentScan("ru.marketplace")
//public class Application1 extends SpringBootServletInitializer implements WebApplicationInitializer {
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//        return application.sources(Application1.class);
//    }
//
//
//
//
//
//    @Autowired
//    UserService userService;
//
//
//
//    private final static String DISPATCHER = "dispatcher";
//
//    public void onStartup(ServletContext servletContext) throws ServletException {
//        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
//        ctx.register(WebConfig.class);
//        servletContext.addListener(new ContextLoaderListener(ctx));
//
//        ServletRegistration.Dynamic servlet = servletContext.addServlet(DISPATCHER, new DispatcherServlet(ctx));
//
//        servlet.addMapping("/");
//        servlet.setLoadOnStartup(1);
//    }
//
//
//}
