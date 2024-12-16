package iticbcn.threads;

/* Programa principal que iniciarà 2 instàncies de fil
 * Juan i Pepe, i analitzarem les sortides segons el comportament
 */
public class Principal {
    public static void main(String[] args) {
        // acaba l'execució del fil main
        System.out.println("Termina thread main");

        // creem les instancies de Fil Juan i Pepe
        Fil juan = new Fil("Juan");
        Fil pepe = new Fil("Pepe");

        // iniciem les instàncies dels fils.
        juan.start();
        pepe.start();
    }
}