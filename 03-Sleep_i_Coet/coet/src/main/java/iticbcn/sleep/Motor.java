package iticbcn.sleep;

import java.util.Random;

public class Motor extends Thread {
    private int potAct;
    private int potObj;
    private int numMotor;
    private boolean aturat = true;
    private String estat;
    private Random random = new Random();

    // el motor s'inicialitza amb potencies actual i objectiu a 0
    public Motor(int numMotor) {
        this.potAct = 0;
        this.potObj = 0;
        this.numMotor = numMotor;
        this.aturat = false;
        this.estat = "Incre.";
    }
    // estableix potencia objectiu
    public void setPotencia(int p) {
        this.potObj = p;
        this.aturat = false;
    }
    @Override
    // quan el motor es posa en marxa i reb una potencia objectiu que no és 0, incrementa 1 a 1
    // al contrari, decrementa fins arribar al 0 i sortir.
    public void run() {
        while (!aturat) {
            
            // en cas de que les potències arribin a ser coincident l'objectiu i l'actual, canvia d'estat
            // if (potAct == potObj) {
            //     estat = "FerRes";
            // }
            // segons el tipus de valor objectiu determino l'estat per incrementar 
            // o decrementar la potencia actual 
            if (potObj != 0 && potAct < potObj) {
                potAct++;
                if (potAct == potObj) {
                    estat = "FerRes";
                }
            } else if (potObj == 0 && potAct > potObj){
                estat = "Decre.";
                potAct--;
                if (potAct == potObj) {
                    estat = "FerRes";
                }
            }
            System .out.printf("Motor %2d: %-7sObjectiu: %d Actual: %d\n", numMotor, estat, potObj, potAct);
            try {
                // entre les desviacions de potències, hi ha un retraç de 1-2 segons
                Thread.sleep(random.nextInt(2000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // if (estat.equalsIgnoreCase("FerRes")) {
                // torna a aturar el motor
                if ((potAct == 0 && potObj == 0) || potAct == potObj) {
                    aturat = true;
                    break;
                }
            // }
        }
    }
}
