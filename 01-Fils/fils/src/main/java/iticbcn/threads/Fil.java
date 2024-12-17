/* Classe Fil que esten de Threads i sobreescriu el mètode run()
 * per manipular els comportaments. També es possible amb la
 * implementació de la interficie de Runneable i redefinir run().
 */
package iticbcn.threads;

public class Fil extends Thread {

    // VARIABLES OBLIGATORIAMENT STATICS, per a que sigui accessible desde l'altre instància del fil
    // objecte monitor que ens servirà sincronitzar el comportament i notificar al de l'altre fil
    private static final Object lock = new Object();
    // utilitzaré aquesta variable per controlar el fil de cada execució, començant pel del Juan
    private static String filNom = "Juan";

    @Override
    public void run() {
        for(int i = 1; i < 10; i++) {
            // recurs compartit que sincronitza i alternar les execucions amb el monitor lock
            synchronized (lock) {
                // itera mentres el fil actual no sigui el del filNom
                while (!filNom.equals(this.getName())) {
                    try {
                        // fil actual espera al seu torn
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // mentres el while es trobi que els noms dels fils siguin iguals, 
                // imprimirà l'execució actual, sinó el posara en espera si són diferents.
                System.out.println(this.getName() + " " + i);

                // controlem que si el fil actual, si es verifica, passa a dir-se amb el nom de l'altre
                // si no es verifica, li tocarà al fil de Juan a la següent execució
                // com que només tenim 2 fils, ens despreocupem si hi han altres cadenes 
                filNom = filNom.equals("Juan") ? "Pepe" : "Juan";
                
                // avisa l'altre fil
                lock.notifyAll();
            }
        }
        System.out.println("Termina el fil " + this.getName());
    }
}
