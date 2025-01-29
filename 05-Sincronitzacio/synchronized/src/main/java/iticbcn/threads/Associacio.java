package iticbcn.threads;

public class Associacio {
    private final int NUM_SOCIS;
    private final Soci[] SOCIS;
    public Associacio() {
        this.NUM_SOCIS = 1000;
        this.SOCIS = new Soci[this.NUM_SOCIS];
    }
    public void inicialitzaSocis() {
        for(int i = 0; i < SOCIS.length; i++) {
            SOCIS[i] = new Soci(String.valueOf(i));
        }
    }
    public void iniciaCompteTempsSocis() {
        for (Soci soci: SOCIS) { soci.start(); }        
    }
    public void esperaPeriodeSocis() {
        for (Soci soci: SOCIS) { 
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void mostraBalancComptes() {
        float saldoFinal = SOCIS[0].getCompte().getSaldo();
        System.out.printf("\nSaldo: %.2f", saldoFinal);
    }
    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        // es creen instàncies dels SOCIS
        associacio.inicialitzaSocis();
        // executem els fils de tots els SOCIS i 
        // inicialitza Compte en execució del fil del soci
        associacio.iniciaCompteTempsSocis();
        // els SOCIS esperen
        associacio.esperaPeriodeSocis();
        // es mostra el saldo final del compte
        associacio.mostraBalancComptes();
    }
}