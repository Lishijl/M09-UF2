package iticbcn.threads;

public class Associacio {
    private final int NUM_SOCIS;
    private Soci[] socis;
    public Associacio() {
        this.NUM_SOCIS = 1000;
        this.socis = new Soci[this.NUM_SOCIS];
    }
    public void inicialitzaSocis() {
        for(int i = 0; i < socis.length; i++) {
            socis[i] = new Soci(String.valueOf(i));
        }
    }
    public void iniciaCompteTempsSocis() {
        for (Soci soci: socis) { soci.start(); }        
    }
    public void esperaPeriodeSocis() {
        for (Soci soci: socis) { 
            try {
                soci.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void mostraBalancComptes() {
        float saldoFinal = socis[0].getCompte().getSaldo();
        System.out.printf("\nSaldo: %.2f", saldoFinal);
    }
    public static void main(String[] args) {
        Associacio associacio = new Associacio();
        // es creen instàncies dels socis
        associacio.inicialitzaSocis();
        // executem els fils de tots els Socis i 
        // inicialitza Compte en execució del fil del soci
        associacio.iniciaCompteTempsSocis();
        // els socis esperen
        associacio.esperaPeriodeSocis();
        // es mostra el saldo final del compte
        associacio.mostraBalancComptes();
    }
}