/* Classe Fil que esten de Threads i sobreescriu el mètode run()
 * per manipular els comportaments. També es possible amb la
 * implementació de la interficie de Runneable i redefinir run().
 */
package iticbcn.threads;

public class Fil extends Thread {
    private String nom;

    // constructor de Fil
    public Fil(String nom) {
        this.nom = nom;
    }

    // sobreescribim run() per personalitzar el comportament que s'executarà
    @Override
    public void run() {
        // 10 iteracions per fil mostrant les vegades que itera cada Fil
        for(int i = 1; i < 10; i++) {
            System.out.println(nom + " " + i);
        }
        // notifica quan acaba l'execució del fil
        System.out.println("Termina el fil " + nom);
    }
}
