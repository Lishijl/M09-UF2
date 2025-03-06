package iticbcn.threads;

import java.util.LinkedList;
import java.util.Queue;

public class Barberia {
    // cua de clients en la sala d'espera, per multifils
    private Queue<Client> salaEspera;
    private final int MAX_CADIRES;
    // control de bloqueig especific
    private final Object condBarber;
    public static Barberia barberia;
    public Barberia(int cadires) {
        this.salaEspera = new LinkedList<>();
        this.CADIRES = cadires;
        // consumeix menor recurs al ser Obj
        this.condBarber = new Object();
    }
    public Client seguentClient() {
        // pot retornar null si no queden clients
        return salaEspera.poll();
        //offer() -> false si falla | poll()
    }
    public boolean entrarClient(Client cli) {

    }
}
