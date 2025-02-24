package iticbcn.threads;

public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;
    public Taula() {}
    public Taula(int numFilosofs) {
        this.comensals = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numFilosofs];
        crearForquilles();
        crearFilosofs();
        assignarForquilles();
    }
    public void crearForquilles() {
        for (int i = 0; i < forquilles.length; i++) forquilles[i] = new Forquilla(i);
    }
    public void crearFilosofs() {
        for (int i = 0; i < comensals.length; i++) comensals[i] = new Filosof(i);
    }
    public void assignarForquilles() {
        int indexForquilla = 0;
        for (int d = 0; d < comensals.length; d++) {
            for (int i = indexForquilla; i < forquilles.length; i++) {
                Forquilla esq = forquilles[i];
                // ultima forquilla = 0
                Forquilla dret = forquilles[(i + 1) % forquilles.length];
                comensals[d].setForquillaEsquerra(esq);
                comensals[d].setForquillaDreta(dret);
                if (d < comensals.length) {
                    indexForquilla = i + 1;
                    break;
                }
            }
        } 
    }
    public void showTaula() {
        for (Filosof fil : comensals) {
            System.out.printf("\nComensal:fil%s esq:%s dret:%s", 
                                fil.getName(), fil.getForquillaEsquerra().getNum(), fil.getForquillaDreta().getNum());
        }
        System.out.println("\n--------------------------");
    }
    public void cridarATaula() {
        for (Filosof fil : comensals) { fil.start(); }
    }
    public static void main(String[] args) {
        Taula taula = new Taula(4);
        taula.showTaula();
        taula.cridarATaula();
    }
}
