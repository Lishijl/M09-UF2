package iticbcn.futbol;
/* Classe que a partir de la captura del fil actual en execució,
 * mostra la seva prioritat, nom i el toString()
 */
public class MainDemoFil {
    public static void main(String[] args) {
        // capturem el fil actual per mostrar les seves propietats
        Thread filActual = Thread.currentThread();
        System.out.println("MainDemoFil.main:");
        System.out.println("Prioritat -> " + filActual.getPriority() + ", Nom -> " + filActual.getName());
        System.out.println("toString() -> " + filActual.toString());

        // TOTS els programes en java, s'executen desde un fil principal
        // que sempre es dira main, perquè el primer fil que es crea i 
        // s'executa en qualsevol programa sempre serà el del main.
    }
}
