package ap.atixlabs.ej.models;

import ap.atixlabs.ej.beans.MonitoringSystemBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.TimerTask;

@RestController("/")
public class MonitoringSystem extends TimerTask {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSystemBean.class);

    private int constantS;
    private int constantM;
    private List<Integer> valuesList = new LinkedList<>();

    public int getConstantS() {
        return constantS;
    }

    public void setConstantS(int constantS) {
        this.constantS = constantS;
        LOGGER.info("La variable S ha sido modificada (" + constantS +")");
    }

    public int getConstantM() {
        return constantM;
    }

    public void setConstantM(int constantM) {
        this.constantM = constantM;
        LOGGER.info("La variable M ha sido modificada (" + constantM +")");
    }

    public List<Integer> getValuesList() {
        return valuesList;
    }

    public void setValuesList(List<Integer> valuesList) {
        this.valuesList = valuesList;
    }

    @PostMapping(path = "/value", consumes = "application/json")
    public void addValuesList(@RequestBody int number) {
        this.addToList(number);
    }

    public void addToList(int number) {
        this.valuesList.add(number);
    }

    public void run(){
        processValuesList();
    }

    private void processValuesList() {
        LOGGER.info("El sistema de monitereo procesa los valores recibidos");
        if(!this.valuesList.isEmpty()){
            int sumValue = this.valuesList.stream().mapToInt(i->i).sum();
            LOGGER.info("La suma total es: " + sumValue);
            int cantValues = this.valuesList.size();
            LOGGER.info("La cantidad total es: " + cantValues);
            int average = sumValue / cantValues;
            LOGGER.info("El promedio es: " + average);

            if(average > this.getConstantM()){
                LOGGER.error("El promedio es mayor al valor de la constante M(" + this.getConstantM() + ")");
            }

            int max = this.valuesList.stream().max(Integer::compare).get();
            LOGGER.info("El maximo valor es: " + max);
            int min = this.valuesList.stream().min(Integer::compare).get();
            LOGGER.info("El minimo es: " + min);
            int diff = max - min;
            LOGGER.info("La diferencia entre el maximo y minimo es: " + diff);

            if(diff > this.getConstantS()){
                LOGGER.error("La diferencia entre el maximo y el minimo es mayor al valor de la constante S(" + this.getConstantS() + ")");
            }
            this.valuesList.clear();
        } else {
            LOGGER.error("La lista de valores a procesar se encuentra vacia");
        }
    }
}
