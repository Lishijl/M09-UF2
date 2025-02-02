package iticbcn.threads;

import java.util.ArrayList;
import java.util.List;

public class Esdeveniment {
    private final List<Assistent> ASSISTENTS;
    private int numMaxPlaces = 10;
    private int placesDisponibles;

    public Esdeveniment(int numPlaces) {
        this.ASSISTENTS = new ArrayList<>();
        this.numMaxPlaces = numPlaces;
        this.placesDisponibles = this.numMaxPlaces;
    }
    // Pel control de synchronized en els mètodes, no ens cal controlar l'objecte Esdeveniment
    // i perquè no es una instància única global, !singleton
    public synchronized void ferReserva(Assistent assis) {
        while (placesDisponibles == 0) {
            try {
                // Després de verificar que no hi han places disponibles, el fil s'espera fins ser notificat per notifyAll()
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Mentres hi hagi places disponibles o surti d'esperar en el wait(), afegeix assistent a la llista
        ASSISTENTS.add(assis);
        placesDisponibles--;
        System.out.printf("\nAssistent-%s ha fet una reserva. Places disponibles: %d", assis.getName(), placesDisponibles);

    }
    // Cancel·la reserva si el control de reservas troba assistent coincident, amb una operació atòmica
    public synchronized void cancelaReserva(Assistent assis) {
        if (ASSISTENTS.contains(assis)) {
            ASSISTENTS.remove(assis);
            placesDisponibles++;
            System.out.printf("\nAssistent-%s ha cancel·lat una reserva. Places disponibles: %d", assis.getName(), placesDisponibles);
            // Un cop alliberada una plaça avisa a tots els fils que estan en espera per continuar amb la reserva de ferReserva()
            notifyAll();
        } else {
            System.out.printf("\nAssistent-%s no ha pogut cancel·lar una reserva inexistent. Places disponibles: %d", assis.getName(), placesDisponibles);
        }
    }
}
