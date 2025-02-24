package iticbcn.threads;

import java.util.Random;

public class Filosof extends Thread {
    private Forquilla forquillaEsquerra, forquillaDreta;
    private int iniciGana;
    private int fiGana;
    private int gana;
    private int nomFil;
    private Random rnd;
    public Filosof() {}
    public Filosof(int numComensal) {
        super("" + numComensal);
        this.nomFil = numComensal;
        this.gana = 0;
        this.rnd = new Random();
    }
    public void menjar() {}
    public void afagarForquilles() {}
    public void agafarForquillaEsquerra() {}
    public void agafarForquillaDreta() {}
    // deixa primer la dreta i després l'esquerra
    public void deixarForquilles() {}
    public void pensar() {
        System.out.printf("Filòsof: fil%s pensant\n", getName());
        espera(1000, 2001);
    }
    public void espera(int org, int limit) {
        try {
            sleep(rnd.nextInt(org, limit));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public int calcularGana() {
        return 0;
    }
    public void resetGana() {}
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
