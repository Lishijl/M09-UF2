package iticbcn.threads;

public class Organitzador {
    private final int NUM_ASSIS;
    private final Assistent[] ASSISTENTS;
    private final Esdeveniment ESDEVENIMENT;
    public Organitzador() {
        this.NUM_ASSIS = 10;
        this.ASSISTENTS = new Assistent[this.NUM_ASSIS];
        this.ESDEVENIMENT = new Esdeveniment(5);
    }
    public void inicialitzaAssistents() {
        for (int i = 0; i < ASSISTENTS.length; i++) {
            ASSISTENTS[i] = new Assistent(String.valueOf(i), ESDEVENIMENT);
        }
    }
    public void arrancaAssistents() {
        for (Assistent assis : ASSISTENTS) { assis.start(); }
    }
    public static void main(String[] args) {
        Organitzador org = new Organitzador();
        org.inicialitzaAssistents();
        org.arrancaAssistents();
    }
}
