package iticbcn.threads;

import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forkR;
    private Forquilla forkL;

    private int gana;
    private Random rnd;

    public Filosof(int numFil) {
        super(Integer.toString(numFil));
        this.gana = 0;
        this.rnd = new Random();
    }

    public void menjar() {
        System.out.printf("Filòsof: fil%s menja\n", getName());
        dura(1000, 2001);
        System.out.printf("Filòsof: fil%s ha acabat de menjar\n", getName());
        this.gana = 0;
    }
    public void pensar() {
        System.out.printf("Filòsof: fil%s pensant\n", getName());
        dura(1000, 2001);
    }

    public void dura(int org, int limit) {
        try {
            sleep(rnd.nextInt(org, limit));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public void passarGana() {
        gana++;
        System.out.printf("Filòsof: fil%s gana=%d\n", getName(), gana);
        dura(500, 1001);
    }

    @Override
    public void run() {
        // menja i pensa alternativament
        while (true) {
            if (!forkL.isEnUs()) {
                forkL.setEnUs(true);
                System.out.printf("Filòsof: fil%s agafa la forquilla esquerra %d\n", getName(), forkL.getNumF());
                // cas 1 : pot menjar
                if (!forkR.isEnUs()) {
                    forkR.setEnUs(true);
                    System.out.printf("Filòsof: fil%s agafa la forquilla dreta %d\n", getName(), forkR.getNumF());
                    menjar();
                    // torna a deixar forquilles
                    forkL.setEnUs(false);
                    forkR.setEnUs(false);
                } else {
                    // cas 2: no pot menjar, no serveix 1 sola forquilla
                    forkL.setEnUs(false);
                    System.out.printf("Filòsof: fil%s deixa l'esquerra (%d) i espera (dreta ocupada)\n", getName(), forkL.getNumF());
                    passarGana();
                }
            } else {
                // cas 3: tampoc pot menjar, cap cobert disponible
                dura(500, 1001);
            }
            // si no pot començar a menjar passa a pensar directament
            pensar();
        }
    }

    public Forquilla getForkR() {
        return forkR;
    }

    public void setForkR(Forquilla forkR) {
        this.forkR = forkR;
    }
    
    public Forquilla getForkL() {
        return forkL;
    }

    public void setForkL(Forquilla forkL) {
        this.forkL = forkL;
    }
}