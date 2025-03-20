package iticbcn.threads;

import java.util.Random;

public class Home extends Thread {
    private BanyUnisex banyUnisex;
    private String nom;
    private Random rnd;
    public Home(BanyUnisex banyUnisex, String nom) {
        this.banyUnisex = banyUnisex;
        this.nom = nom;
        this.rnd = new Random();
    }
    @Override
    public void run() {
        // no cal el while, nomes, try, entra i usa i surt i printeja abans de try i final, dona=
        // intenta entrar al lavabo i va proant cada 100 milisegons
        while (true) { 
            entraHome();
            utilitzaLavabo(1000, 2001);
            surtHome();
            // si falla, espera 
            try {
                sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void entraHome() {}
    // l'espera de 1-2seg
    public void utilitzaLavabo(int min, int max) {
        try {
            sleep(rnd.nextInt(min, max));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public void surtHome() {}
}
