package iticbcn.threads;

import java.util.Random;

public class Dona extends Thread {
    private BanyUnisex banyUnisex;
    private String nom;
    private Random rnd;
    public Dona(BanyUnisex banyUnisex, String nom) {
        this.banyUnisex = banyUnisex;
        this.nom = nom;
        this.rnd = new Random();
    }
    @Override
    public void run() {
        // intenta entrar al lavabo i va proant cada 7 milisegons
        while (true) { 
            entraDona();
            utilitzaLavabo(2000, 3001);
            surtDona();
            // si falla, espera 
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void entraDona() {}
    // l'espera de 1-2seg
    public void utilitzaLavabo(int min, int max) {
        try {
            sleep(rnd.nextInt(min, max));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public void surtDona() {}
}
