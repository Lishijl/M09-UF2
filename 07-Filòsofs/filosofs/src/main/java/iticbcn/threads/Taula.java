package iticbcn.threads;

public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;
    public Taula() {}
    public Taula(int numFilosofs, int numForquilles) {
        this.comensals = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numForquilles];
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
        // per cada comensal, li assignem 2 forquilles.
        for (int d = 0; d < comensals.length; d++) {
            for (int i = indexForquilla; i < forquilles.length; i++) {
                Forquilla esq = forquilles[i];
                // ultima forquilla = 0
                Forquilla dret = forquilles[(i + 1) % forquilles.length];
                comensals[d].setForkL(esq);
                comensals[d].setForkR(dret);
                if (d < comensals.length) {
                    // actualitzem index forquilla pel pròxim comensal
                    indexForquilla = i + 1;
                    break;
                }
            }
        } 
    }
    // mostrar tots els filòsofs que ja té les forquilles assignades i que pot accedir a les instàncies
    public void showTaula() {
        for (Filosof fil : comensals) {
            System.out.printf("\nComensal:fil%s esq:%s dret:%s", 
                                fil.getName(), fil.getForkL().getNumF(), fil.getForkR().getNumF());
        }
        System.out.println("\n--------------------------");
    }
    public void cridarATaula() {
        for (Filosof fil : comensals) { fil.start(); }
    }

    public static void main(String[] args) {
        Taula taula = new Taula(4, 4);
        taula.showTaula();
        taula.cridarATaula();
    }
}