package iticbcn.sleep;

import java.util.Random;

public class Motor extends Thread {
    private int potAct;
    private int potObj;
    private int numMotor;
    private String estat;
    private Random random = new Random();

    // el motor s'inicialitza amb potencies actual i objectiu a 0
    public Motor(int numMotor) {
        this.potAct = 0;
        this.potObj = 0;
        this.numMotor = numMotor;
        this.estat = "Incre.";
    }
    // estableix potencia objectiu
    public void setPotencia(int p) {
        this.potObj = p;
    }
    @Override
    // quan el motor es posa en marxa i reb una potencia objectiu que no és 0, incrementa 1 a 1
    // al contrari, decrementa fins arribar al 0 i sortir.
    public void run() {

        while (true) {
            while (potAct != potObj) {
                // segons les condicions de potencia actual y objectiu determina si l'estat es d'increment o decrement, 
                // per sumar o restar 1 a 1 y finalment comproba la tercera condició que si després de canviar la potencia
                // torna a comprobar si son iguals per canviar a l'estat de fer res.
                estat = potAct < potObj ? "Incre." : "Decre.";
                potAct += potAct < potObj ? +1 : -1;
                if (potAct == potObj) estat = "FerRes";

                System .out.printf("Motor %2d: %-7sObjectiu: %d Actual: %d\n", numMotor, estat, potObj, potAct);
                try {
                    // entre els canvis de potencies, hi ha un retraç de 1-2 segons
                    Thread.sleep(random.nextInt(2000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                // esperes per no sobrecargar el procesador
                this.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // quan potencia actual arriba a 0, surt del while i finalitza el fil del motor
            if (potAct == 0) break;
        }
    }
}
