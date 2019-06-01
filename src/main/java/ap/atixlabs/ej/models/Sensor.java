package ap.atixlabs.ej.models;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Sensor extends TimerTask {

    public Sensor(MonitoringSystem monitoringSystem) {
        this.monitoringSystem = monitoringSystem;
    }

    private MonitoringSystem monitoringSystem;

    public void run() {

        int randomNum1 = ThreadLocalRandom.current().nextInt(0, 500 + 1);
        int randomNum2 = ThreadLocalRandom.current().nextInt(0, 500 + 1);

        this.monitoringSystem.addToList(randomNum1);
        this.monitoringSystem.addToList(randomNum2);
    }
}
