package iticbcn.threads;

/* Programa principal que iniciarà 2 instàncies de fil
 * Juan i Pepe, i analitzarem les sortides segons el comportament
 */
public class Principal {
    public static void main(String[] args) {
        
        // creem les instancies de Fil Juan i Pepe
        Fil juan = new Fil();
        Fil pepe = new Fil();
        
        // com que Fil estén de Threads disposem del mètode setName()
        juan.setName("Juan");
        pepe.setName("Pepe");
        
        // iniciem les instàncies dels fils.
        juan.start();
        pepe.start();

        // acaba l'execució del fil main
        System.out.println("Termina thread main");
    }
}