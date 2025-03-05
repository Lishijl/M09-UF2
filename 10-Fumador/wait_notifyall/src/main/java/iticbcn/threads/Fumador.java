package iticbcn.threads;

import java.util.Random;

public class Fumador extends Thread {
    private Estanc estanc;
    private int id;

    private Tabac tabac;
    private Llumi llumi;
    private Paper paper;

    private int fumades;
    private Random rnd;

    public Fumador(Estanc est, int id) {
        this.estanc = est;
        this.id = id;
        this.tabac = null;
        this.llumi = null;
        this.paper = null;
        this.fumades = 0;
        this.rnd = new Random();
    }

    public void fuma() {
        if (tabac != null && llumi != null && paper != null) {
            fumades++;
            System.out.printf("Fumador %d fumant\n", id);
            System.out.printf("Fumador %d ha fumat %d vegades\n", id, fumades);
            tabac = null;
            llumi = null;
            paper = null;
            espera(500, 1001);
        }   
    }
    public void espera(int org, int limit) {
        try {
            sleep(rnd.nextInt(org, limit));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public void compraTabac() {
        if (tabac == null) {
            // variable reassignat en la global
            if((tabac = estanc.venTabac()) != null) {
                System.out.printf("Fumador %d comprant Tabac\n", id);
            }
        }
    }
    public void compraPaper() {
        if (paper == null) {
            if ((paper = estanc.venPaper()) != null) {
                System.out.printf("Fumador %d comprant Paper\n", id);
            }
        }
    }
    public void compraLlumi() {
        if (llumi == null) {
            if ((llumi = estanc.venLlumi()) != null) {
                System.out.printf("Fumador %d comprant Llumí\n", id);
            }
        }
    }

    @Override
    public void run() {
        while (fumades < 3) {
            compraTabac();
            compraPaper();
            compraLlumi();
            fuma();
        }
    }

    public int getFumades() {
        return fumades;
    }
}
