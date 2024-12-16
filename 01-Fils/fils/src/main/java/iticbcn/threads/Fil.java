/* Classe Fil que esten de Threads i sobreescriu el mètode run()
 * per manipular els comportaments. També es possible amb la
 * implementació de la interficie de Runneable i redefinir run().
 */
package iticbcn.threads;

public class Fil extends Thread {

    // sobreescribim run() per personalitzar el comportament que s'executarà
    @Override
    public void run() {
        // 10 iteracions per fil mostrant les vegades que itera cada Fil
        for(int i = 1; i < 10; i++) {
            // imprimeixo el nom del fil per iteració amb el mètode heredat getName() de Thread
            System.out.println(this.getName() + " " + i);
        }
        // notifica quan acaba l'execució del fil
        System.out.println("Termina el fil " + this.getName());
    }
}
