package iticbcn.threads;

import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private List<Assistent> assistents;
    private int numMaxPlaces = 10;
    private static Esdeveniment instance = null;
    private int placesDisponibles;

    public Esdeveniment(int numPlaces) {
        this.numMaxPlaces = numPlaces;
        this.assistents = new ArrayList<>();
        this.placesDisponibles = this.numMaxPlaces;
    }
    synchronized public void ferReserva(Assistent assis) {
        if (placesDisponibles > 0) {
            assistents.add(assis);
            placesDisponibles--;
            mostraMissatge(assis);
        } else {
            // l'assistent espera per que una plaça s'alliberi
            assis.wait();
            mostraMissatge(assis);
        }
    }
    // només elimina si troba assistent coincident i busquem operació atòmica
    synchronized public void cancelaReserva(Assistent assis) {
        if (assistents.contains(assis)) {
            assistents.remove(assis);
            placesDisponibles++;
            mostraMissatge(assis);
        } else {
            mostraMissatge(assis);
        }
    }
    public static Esdeveniment getInstance(int numPlaces) {
        if (instance == null) instance = new Esdeveniment(numPlaces);
        return instance;
    }
    public void mostraMissatge(Assistent assis) { 
        System.out.printf("\nAssistent-%s no ha pogut fer una reserva, places esgotades. Places disponibles: %d", assis.getName(), placesDisponibles);
    }
}
