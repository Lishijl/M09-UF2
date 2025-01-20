package iticbcn.join;

public class Administracio {
    private static final int num_poblacio_activa = 50;
    private static final int poblacio_activa = 50;
    private Treballador[] treballadors;
    public Administracio() {
        this.treballadors = new Treballador[num_poblacio_activa];
        for (int i = 0; i < num_poblacio_activa; i++) {
            treballadors[i] = new Treballador(String.valueOf(i), 25000, 20, 65);
        }
    }

    public static void main(String[] args) {
        // creem les instàncies de treballador amb les seves dades
        // inicialitzant l'administracio
        Administracio adm = new Administracio();

        // fiquem en marxa els treballadors i quan acabin mostra les estadístiques
        for (int i = 0; i < poblacio_activa; i++) {
            adm.treballadors[i].start();
            try {
                adm.treballadors[i].join();
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            // getEdat hauria de mostrar només quan acaben de treballar, 65 anys degut al join()
            System.out.printf("Ciutadà-%-11s-> edat: %d / total: %f", adm.treballadors[i].getName(), adm.treballadors[i].getEdat(), adm.treballadors[i].getCobrat());
        }
    }
}
