package iticbcn.threads;

public class Taula {
    private Filosof[] comensals;
    private Forquilla[] forquilles;
    public Taula() {}
    public Taula(int numFilosofs, int numForquilles) {
        this.comensals = new Filosof[numFilosofs];
        this.forquilles = new Forquilla[numForquilles];
        crearFilosofs();
        crearAssignaForquilles();
    }
    // mostrar tots els filòsofs i les forquilles que pot accedir
    public void showTaula() {}
    // inicia l'execució dels fils dels filòsofs existents
    public void cridarATaula() {}

    public static void main(String[] args) {
        // completar aquesta part
        // showTaula();
        // cridarATaula();
    }
}
