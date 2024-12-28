package iticbcn.futbol;
/* Classe Futbolista que esten Thread, que inicialitza el nombre 
 * de gols i el nombre de tirades a 0 dins del constructor.
 * 
 * A part té 3 constants NUM_JUGADORS 11, NUM_TIRADES 20 i la
 * PROBABILITAT de 0.5f.
 * 
 * Cada jugador simularà quants gols marcarà segons les probabilitats 
 * i nombre de tirades disponibles.
 * 
 * Finalment imprimirem per sortida els resultats de les estadístiques.
 */
public class Futbolista extends Thread {
    
    // constants finals privades i estàtiques utilitzades dins la pròpia classe Futbol
    private static final int NUM_JUGADORS = 11;
    private static final int NUM_TIRADES = 20;
    private static final float PROBABILITAT = 0.5f;

    private int ngols;
    private int ntirades;

    // constructor que crida al constructor heredat super() per instànciar 
    // un fil super amb el nom del jugador actual, al mateix temps 
    // inicialitza les propietats de la instància actual
    public Futbolista(String nom) {
        super(nom);
        this.ngols = 0;
        this.ntirades = 0;
    }

    public void setNgols(int ngols) { this.ngols = ngols; }
    public void setNtirades(int ntirades) { this.ntirades = ntirades; }
    public int getNgols() { return ngols; }
    public int getNtirades() { return ntirades; }

    // redefinir l'execució de fil corresponent al jugador
    @Override
    public void run() {
        for (int i = 0; i < NUM_TIRADES; i++) {
            // acumula els xuts
            ntirades++;
            // marca si random per tirada és menor que PROBABILITAT
            if (Math.random() < PROBABILITAT) {
                ngols++;
            }
        }
    }

    // si mogués el main a una classe a part, hauria de crear
    // un getter per a la constant de visibilitat privada
    public static void main(String[] args) {
        String[] jugadors = {"Piqué", "Vinicius", "Torres", "Ramos", 
                            "Ronaldo", "Lewan", "Belli", "Arnau", 
                            "Aspas", "Messi", "MBapé"};

        // instanciem quantitat de futbolistes en un array de Futbolista
        Futbolista[] futbolistes = new Futbolista[NUM_JUGADORS];

        // creem tots els fils/jugadors
        for (int i = 0; i < NUM_JUGADORS; i++) {
            futbolistes[i] = new Futbolista(jugadors[i]);
        }

        System.out.println("Inici dels xuts --------------------");

        // iniciem l'execució de tots els fils a la vegada per aprofitar la concurrencia
        // dels Threads ja que no depenen entre sí els fils
        for (Futbolista futbolista : futbolistes) {
            futbolista.start();
        }

        // esperem a que tots els fils acabin l'execució en un for diferent i no en el for anterior 
        // per a que sigui més eficient, que es quan els jugadors acaben de xutar tots els tirs
        for (Futbolista futbolista : futbolistes) {
            try {
                futbolista.join();
            } catch (InterruptedException e) {
                System.err.println("Error, espera interrumpuda: " + e.getMessage());
            }
        }

        System.out.println("Fi dels xuts -----------------------");
        System.out.println("--- Estadístiques ------");

        // mostra les estadistiques dels xuts de cada jugador
        for (Futbolista futbolista : futbolistes) {
            System.out.println(futbolista.getName() + " -> " + futbolista.getNgols() + " gols");
        }
    }
}
