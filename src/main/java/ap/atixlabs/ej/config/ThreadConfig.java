package ap.atixlabs.ej.config;

import ap.atixlabs.ej.beans.MonitoringSystemBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@Configuration
public class ThreadConfig {

    @Bean()
    public MonitoringSystemBean monitoringSystemBean() {
        return new MonitoringSystemBean();
    }
}
