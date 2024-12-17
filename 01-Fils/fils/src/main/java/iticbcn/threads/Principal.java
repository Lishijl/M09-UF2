package iticbcn.threads;

/* Programa principal que iniciarà 2 instàncies de fil
 * Juan i Pepe, i analitzarem les sortides segons el comportament
 */
public class Principal {
    public static void main(String[] args) {
        
        // creem les instancies de Fil Juan i Pepe de tipus Thread a partir de Fil
        Fil juan = new Fil();
        Fil pepe = new Fil();
        
        // com que Fil estén de Threads disposem del mètode setName()
        juan.setName("Juan");
        pepe.setName("Pepe");

        // prioritzem el fil d'en Pepe abans que el del Juan amb setPriority()
        // amb les constants de Thread
        juan.setPriority(Thread.MIN_PRIORITY);
        pepe.setPriority(Thread.MAX_PRIORITY);
        
        // iniciem les instàncies dels fils.
        juan.start();
        pepe.start();

        // acaba l'execució del fil main
        System.out.println("Termina thread main");
    }
}