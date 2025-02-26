package iticbcn.threads;

import java.util.Random;

public class Filosof extends Thread {
    // moment d'execució i diferència amb fiGana
    private long iniciGana;
    private long fiGana;
    private Forquilla forquillaEsquerra, forquillaDreta;
    private int gana;
    private Random rnd;
    public Filosof() {}
    public Filosof(int numComensal) {
        super("" + numComensal);
        this.gana = 0;
        this.rnd = new Random();
        this.iniciGana = System.currentTimeMillis();
    }
    public void menjar() {
        agafarForquilles();
        System.out.printf("Fil%s menja amb gana %d\n", getName(), calcularGana());
        // tipus d'espera de procediment
        espera(1000, 2001);
        System.out.printf("Fil%s ha acabat de menjar\n", getName());
        resetGana();
        deixarForquilles();
        System.out.printf("Fil%s deixa les forquilles\n", getName());
    }
    // no agafa l'esquerra si no pot agafar la dreta.
    public void agafarForquilles() {
        // el lock té que ajudar aquí
        if (agafarForquillaEsquerra()) {
            if (agafarForquillaDreta()) {
                System.out.printf("Fil%s té forquilles esq(%d) dreta(%d)\n", getName(), forquillaEsquerra.getNum(), forquillaDreta.getNum());
                return;
            } else {
                deixarForquilles();
                espera(500, 1001);
            }
        } else {
            espera(500, 1001);
        }
    }
    public boolean agafarForquillaEsquerra() { 
        forquillaEsquerra.agafar();
        return forquillaEsquerra.getBloqueig().isHeldByCurrentThread();
    }
    public boolean agafarForquillaDreta() {
        forquillaDreta.agafar();
        return forquillaDreta.getBloqueig().isHeldByCurrentThread();
    }
    // deixa primer la dreta i després l'esquerra
    public void deixarForquilles() {
        forquillaDreta.deixar();
        forquillaEsquerra.deixar();
    }
    public void pensar() {
        resetGana();
        System.out.printf("Fil%s pensant\n", getName());
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
        this.fiGana = System.currentTimeMillis();
        return (int) ((this.fiGana - this.iniciGana) / 1000);
    }
    public void resetGana() {
        this.iniciGana = System.currentTimeMillis();
        this.gana = 0;
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
