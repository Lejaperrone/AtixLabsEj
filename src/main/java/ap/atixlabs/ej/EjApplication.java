package ap.atixlabs.ej;

import ap.atixlabs.ej.beans.MonitoringSystemBean;
import ap.atixlabs.ej.config.ThreadConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EjApplication {

    public static void main(String[] args) {
        SpringApplication.run(EjApplication.class, args);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ThreadConfig.class);
        MonitoringSystemBean bean = context.getBean(MonitoringSystemBean.class);
        bean.startMonitoringSystem();
    }

    @GetMapping("/")
    public String hello() {
        return "Bienvenido al Sistema de Monitoreo!";
    }
}
