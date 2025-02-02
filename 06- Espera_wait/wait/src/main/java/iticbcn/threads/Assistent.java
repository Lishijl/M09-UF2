package iticbcn.threads;

import java.util.Random;

public class Assistent extends Thread {
    private final Esdeveniment ESDEVENIMENT;
    private final Random RND;
    private final int ESPERA_MAX;
    private final float PROBABILITAT;

    public Assistent(String nom, Esdeveniment esdeveniment) {
        super(nom);
        this.ESDEVENIMENT = esdeveniment;
        this.RND = new Random();
        this.ESPERA_MAX = 1000;
        this.PROBABILITAT = 0.5f;
    }
    public Esdeveniment getEsdeveniment() { return this.ESDEVENIMENT; }
    @Override
    public void run() {
        while(true) {
            // PROBABILITAT=0.5f; 50% tant per fer reserva com per cancel·lar reserva
            if (Math.random() < PROBABILITAT) {
                ESDEVENIMENT.ferReserva(this);
            } else {
                ESDEVENIMENT.cancelaReserva(this);
            }
            try {
                sleep(RND.nextInt(ESPERA_MAX));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
