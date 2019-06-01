package ap.atixlabs.ej.beans;
import ap.atixlabs.ej.models.MonitoringSystem;
import ap.atixlabs.ej.models.Sensor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

import java.util.Scanner;
import java.util.Timer;

public class MonitoringSystemBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemBean.class);

    @Async()
    public void startMonitoringSystem() {
        LOGGER.info("App init");
        System.out.println("Bienvenido al sistema de monitoreo");
        Timer timer = new Timer();

        MonitoringSystem monitoringSystem = new MonitoringSystem();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Para comenzar ingrese el valor de la constanste S deseado:");
        int constantSValue = keyboard.nextInt();
        monitoringSystem.setConstantS(constantSValue);

        System.out.println("Ingrese el valor de la constanste M deseado:");
        int constantMValue = keyboard.nextInt();
        monitoringSystem.setConstantM(constantMValue);

        this.showHelp();

        timer.schedule(monitoringSystem,0,30000);

        while (true) {
            int opcion = keyboard.nextInt();
            switch (opcion) {
                case 1:
                    LOGGER.info("Simulacion de sensores activada");
                    System.out.println("Simulacion de sensores activada");
                    this.scheduleSensors(timer, monitoringSystem);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo valor de la constanste S deseado:");
                    this.setConstantS(keyboard, monitoringSystem);
                    System.out.println("La variable ha sido modificada");
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo valor de la constanste M deseado:");
                    this.setConstantM(keyboard, monitoringSystem);
                    System.out.println("La variable ha sido modificada");
                    break;
                case 4:
                    this.showHelp();
                    break;
            }
        }
    }

    private void setConstantS(Scanner keyboard, MonitoringSystem monitoringSystem) {
        int nuevoValorConstanteS = keyboard.nextInt();
        monitoringSystem.setConstantS(nuevoValorConstanteS);
    }

    private void setConstantM(Scanner keyboard, MonitoringSystem monitoringSystem) {
        int nuevoValorConstanteM = keyboard.nextInt();
        monitoringSystem.setConstantM(nuevoValorConstanteM);
    }

    private void scheduleSensors(Timer timer, MonitoringSystem monitoringSystem) {
        timer.schedule(new Sensor(monitoringSystem), 0, 1000);
        timer.schedule(new Sensor(monitoringSystem), 0, 1000);
        timer.schedule(new Sensor(monitoringSystem), 0, 1000);
        timer.schedule(new Sensor(monitoringSystem), 0, 1000);
    }

    private void showHelp() {
        System.out.println("Ingrese 1 para simular que los sensores generen informacion aleatoria");
        System.out.println("Ingrese 2 para configurar un nuevo valor de la constante S");
        System.out.println("Ingrese 3 para configurar un nuevo valor de la constante M");
        System.out.println("Ingrese 4 para ayuda");
    }
}
