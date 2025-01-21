package iticbcn.join;

public class Administracio {
    private static final int num_poblacio_activa = 50;
    private Treballador[] poblacio_activa;
    public Administracio() {
        this.poblacio_activa = new Treballador[num_poblacio_activa];
        for (int i = 0; i < poblacio_activa.length; i++) {
            poblacio_activa[i] = new Treballador(String.valueOf(i), 25000, 20, 65);
        }
    }

    public static void main(String[] args) {
        // creem les instàncies de treballador amb les seves dades
        // inicialitzant l'administracio
        Administracio adm = new Administracio();

        // fiquem en marxa els treballadors
        for (Treballador treballador : adm.poblacio_activa) { treballador.start(); }
        
        // s'espera a que s'acabin els fils
        for (Treballador treballador : adm.poblacio_activa) {
            try {
                treballador.join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
        
        // quan s'acabi tots els fils es mostren les estadístiques
        for (Treballador treballador : adm.poblacio_activa) {
            // mostra només quan acaben de treballar els fils, 65 anys degut al join()
            System.out.printf("\nCiutadà-%-3s-> edat: %d / total: %.2f", treballador.getName(), treballador.getEdat(), Math.round(treballador.getCobrat()) * 1f);
        }
    }
}