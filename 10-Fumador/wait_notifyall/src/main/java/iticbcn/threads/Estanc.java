package iticbcn.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Estanc extends Thread {
    private List<Tabac> tabacs;
    private List<Llumi> llumins;
    private List<Paper> papers;

    private Random rnd;
    private boolean obert;

    public Estanc() {
        this.tabacs = new ArrayList<>();
        this.llumins = new ArrayList<>();
        this.papers = new ArrayList<>();
        this.rnd = new Random();
        this.obert = false;
    }
    // 1 recurs
    public void nouSubministrament() {
        int recurs = rnd.nextInt(3);
        switch (recurs) {
            case 0 -> addTabac(new Tabac());
            case 1 -> addLlumi(new Llumi()); 
            case 2 -> addPaper(new Paper());
            default -> throw new AssertionError();
        }
    }

    public synchronized void addTabac(Tabac tab) { 
        tabacs.add(tab); 
        System.out.println("Afegint tabac");
        notifyAll();
    }
    public synchronized void addLlumi(Llumi llum) { 
        llumins.add(llum); 
        System.out.println("Afegint Llumí");
        notifyAll();
    }
    public synchronized void addPaper(Paper pap) { 
        papers.add(pap); 
        System.out.println("Afegint Paper");
        notifyAll();
    }

    public synchronized Tabac venTabac() {
        while (tabacs.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return tabacs.remove(0);
    }
    // qualsevol fumador intenta comprar i s'espera si no hi ha
    public synchronized Llumi venLlumi() {
        while (llumins.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return llumins.remove(0);
    }
    public synchronized Paper venPaper() {
        while (papers.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        return papers.remove(0);
    }
    // per sortir de l'execució
    public void tancarEstanc() {
        obert = false;
    }

    @Override
    public void run() {
        obert = true;
        while (obert) { 
            nouSubministrament();
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
}
