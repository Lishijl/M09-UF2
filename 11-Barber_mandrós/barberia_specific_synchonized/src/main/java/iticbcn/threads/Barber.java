package iticbcn.threads;

import java.util.Random;

public class Barber extends Thread {
    private Random rnd;
    public Barber(String nom) {
        super(nom);
        this.rnd = new Random();
    }
    @Override
    public void run() {
        while (true) { 
            Client cliActual = Barberia.barberia.seguentClient();
            if (cliActual == null) {
                System.out.println("\nNingú en espera");
                System.out.printf("Barber %s dormint", getName());
                synchronized (Barberia.barberia.condBarber) {
                    try {
                        Barberia.barberia.condBarber.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                System.out.printf("\nLi toca al client %s", cliActual.getNom());
                cliActual.tallarseElCabell();
                duracio(900, 101);
            }
        }
    }
    public void duracio(int minimMs, int rndMs) {
        try {
            sleep(minimMs + rnd.nextInt(rndMs));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
