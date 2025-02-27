package iticbcn.threads;

import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra, forquillaDreta;
    private int gana;
    private Random rnd;
    private int numFil;
    public Filosof() {}
    public Filosof(int numComensal) {
        super("" + numComensal);
        this.gana = 0;
        this.rnd = new Random();
        this.numFil = numComensal;
    }

    public void menjar() {
        agafarForquilles();
        System.out.printf("Filòsof: fil%s menja\n", getName());
        espera(1000, 2001);
        System.out.printf("Filòsof: fil%s ha acabat de menjar\n", getName());
        deixarForquilles();
        System.out.printf("Filòsof: fil%s deixa l'esquerra (%d) i la dreta (%d)\n", getName(), forquillaEsquerra.getNumF(), forquillaDreta.getNumF());
        this.gana = 0;
    }

    public synchronized void agafarForquilles() {
        // intenta i només surt per menjar si ha agafat els dos
        do {
            if (agafaForquillaEsquerra()) {
                if (agafaForquillaDreta()) {
                } else {
                    deixarForquilles();
                    System.out.printf("Filòsof: fil%s deixa l'esquerra (%d) i espera (dreta ocupada)\n", getName(), forquillaEsquerra.getNumF());
                    passarGana();
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    espera(500, 1001);
                }
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                espera(500, 1001);
            }
        } while (forquillaEsquerra.getPropietari() != getNumFil() && forquillaDreta.getPropietari() != getNumFil());
    }
    public boolean agafaForquillaEsquerra() {
        if (forquillaEsquerra.getPropietari() == Forquilla.LLIURE) {
            forquillaEsquerra.setPropietari(getNumFil());
            System.out.printf("Filòsof: fil%s agafa la forquilla esquerra %d\n", getName(), forquillaEsquerra.getNumF());
            return true;
        } else { 
            return false; 
        }
    }
    public boolean agafaForquillaDreta() {
        if (forquillaDreta.getPropietari() == Forquilla.LLIURE) {
            forquillaDreta.setPropietari(getNumFil());
            System.out.printf("Filòsof: fil%s agafa la forquilla dreta %d\n", getName(), forquillaDreta.getNumF());
            return true;
        } else {
            return false;
        }
    }
    public synchronized void deixarForquilles() {
        // deixa les forquilles si són el propietari
        if (forquillaEsquerra.getPropietari() == getNumFil()) {
            forquillaEsquerra.setPropietari(forquillaEsquerra.LLIURE);
            notifyAll();
        }
        if (forquillaDreta.getPropietari() == getNumFil()) {
            forquillaDreta.setPropietari(forquillaDreta.LLIURE);
            notifyAll();
        }
    }
    public void pensar() {
        System.out.printf("Filòsof: fil%s pensant\n", getName());
        espera(1000, 2001);
    }

    public void passarGana() {
        gana++;
        System.out.printf("Filòsof: fil%s gana=%d\n", getName(), gana);
    }

    public void espera(int org, int limit) {
        try {
            sleep(rnd.nextInt(org, limit));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) { 
            menjar();
            pensar();   
        }
    }

    public Forquilla getForquillaEsquerra() {
        return forquillaEsquerra;
    }

    public void setForquillaEsquerra(Forquilla forquillaEsquerra) {
        this.forquillaEsquerra = forquillaEsquerra;
    }

    public Forquilla getForquillaDreta() {
        return forquillaDreta;
    }

    public void setForquillaDreta(Forquilla forquillaDreta) {
        this.forquillaDreta = forquillaDreta;
    }

    public int getNumFil() {
        return numFil;
    }

    public void setNumFil(int numFil) {
        this.numFil = numFil;
    }
}
