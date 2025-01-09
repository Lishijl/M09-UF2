package iticbcn.sleep;

import java.util.Random;

public class DormAleatori extends Thread {
    private final long inici;
    private Random random = new Random();
    public DormAleatori(String nom) {
        super(nom);
        this.inici = System.currentTimeMillis();
    }
    public long getInici() { return inici; }

    public static long calculaTempsTotal(long tInicial, long tActual) {
        return tActual - tInicial;
    }
    @Override
    public void run() {
        int interval_aleatori;
        for (int i = 0; i < 10; i++) {
            interval_aleatori = random.nextInt(1000);
            System.out.printf("%-4s (%d) a dormir %5dms total %6d\n", this.getName(), i, interval_aleatori, calculaTempsTotal(getInici(), System.currentTimeMillis()));
            try {
                Thread.sleep(interval_aleatori);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        // creació d'instàncies
        DormAleatori joan = new DormAleatori("Joan");
        DormAleatori pep = new DormAleatori("Pep");
        
        // executa els fils
        joan.start();
        pep.start();

        System.out.println("-- Fi de main -----------");
    }

}
