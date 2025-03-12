package iticbcn.threads;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia extends Thread {
    // cua de clients en la sala d'espera, per multifils
    private Queue<Client> salaEspera;
    private final int MAX_CADIRES;
    // control de bloqueig especific
    final Object condBarber;
    public static Barberia barberia;
    public Barberia(int cadires) {
        this.salaEspera = new LinkedList<>();
        this.MAX_CADIRES = cadires;
        // consumeix menor recurs al ser Obj
        this.condBarber = new Object();
    }
    
    public Client seguentClient() {
        // elimina el primer retornat o null
        return salaEspera.poll();
    }

    public boolean entrarClient(Client cli) {
        boolean entrat = false;
        if (salaEspera.size() < MAX_CADIRES) {
            // offer() -> true si afegeix, contrariament false
            entrat = salaEspera.offer(cli);
            System.out.printf("\nClient %s en espera", cli.getNom());
            synchronized (condBarber) {
                condBarber.notifyAll();
                // desperta del wait, també es podria condBarber.notify()?
            }
        } else {
            System.out.printf("\nNo queden cadires, client %s se'n va", cli.getNom());
        }
        return entrat;
    }
    @Override
    public void run() {
        int cliCount = 0;
        while (true) { 
            cliCount++;
            barberia.entrarClient(new Client(cliCount));
            espera(500);
            // cada tanda de 10 clients espera 10 segons
            if (cliCount % 10 == 0) {
                espera(10000);
            }
        }
    }
    public void espera(int timeMs) {
        try {
            Thread.sleep(timeMs);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        barberia = new Barberia(3);
        Barber barber = new Barber("Pepe");
        barber.start();
        barberia.start();
    }
}
