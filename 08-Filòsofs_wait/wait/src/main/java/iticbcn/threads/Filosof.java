package iticbcn.threads;

import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra, forquillaDreta;
    private int gana;
    private Random rnd;
    public Filosof() {}
    public Filosof(int numComensal) {
        super(Integer.toString(numComensal));
        this.gana = 0;
        this.rnd = new Random();
    }

    public void menjar() {
        agafarForquilles();
        System.out.printf("Filòsof: fil%s menja\n", getName());
        espera(1000, 2001);
        System.out.printf("Filòsof: fil%s ha acabat de menjar\n", getName());
        deixarForquilles();
        this.gana = 0;
    }

    public void agafarForquilles() {
        // intenta i només surt per menjar si ha agafat els dos
        while (true) { 
            if (Integer.parseInt(getName()) != 3) {
                if (agafaForquillaEsquerra()) {
                    System.out.printf("Filòsof: fil%s agafa la forquilla esquerra %d\n", getName(), forquillaEsquerra.getNumF());
                    if (agafaForquillaDreta()) {
                        System.out.printf("Filòsof: fil%s agafa la forquilla dreta %d\n", getName(), forquillaDreta.getNumF());
                        return;
                    } else {
                        if (forquillaEsquerra.deixar()) {
                            System.out.printf("Filòsof: fil%s deixa l'esquerra (%d) i espera (dreta ocupada)\n", getName(), forquillaDreta.getNumF());
                            espera(500, 1001);
                        }
                        // tornarà a intentar
                    }
                } else {
                    espera(500, 1001);
                }
            } else {
                if (agafaForquillaDreta()) {
                    System.out.printf("Filòsof: fil%s agafa la forquilla dreta %d\n", getName(), forquillaDreta.getNumF());
                    if (agafaForquillaEsquerra()) {
                        System.out.printf("Filòsof: fil%s agafa la forquilla esquerra %d\n", getName(), forquillaEsquerra.getNumF());
                        return;
                    } else {
                        forquillaDreta.deixar();
                        System.out.printf("Filòsof: fil%s deixa la dreta (%d) i espera (esquerra ocupada)\n", getName(), forquillaDreta.getNumF());
                        espera(500, 1001);
                    }
                }
            }
        }
    }
    public boolean agafaForquillaEsquerra() {
        return forquillaEsquerra.afagar(Integer.parseInt(getName()));
    }
    public boolean agafaForquillaDreta() {
        return forquillaDreta.afagar(Integer.parseInt(getName()));
    }
    public void deixarForquilles() {
        forquillaEsquerra.deixar();
        forquillaDreta.deixar();
        System.out.printf("Filòsof: fil%s deixa l'esquerra (%d) i la dreta (%d)\n", getName(), forquillaEsquerra.getNumF(), forquillaDreta.getNumF());
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
}
