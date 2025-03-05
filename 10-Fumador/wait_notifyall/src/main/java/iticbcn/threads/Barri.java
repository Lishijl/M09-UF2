package iticbcn.threads;

public class Barri {
    private Estanc estanc;
    private Fumador[] fumadors;

    public Barri() {
        this.estanc = new Estanc();
        this.fumadors = new Fumador[3];
        inicialitzaFumadors();
    }
    public void inicialitzaFumadors() {
        for (int i = 0; i < fumadors.length; i++) { fumadors[i] = new Fumador(estanc, i); }
    }
    public void fumadorsCompren() {
        for (Fumador fum : fumadors) { fum.start(); }
    }
    public void obrirEstanc() {
        estanc.start();
        System.out.println("Estanc obert");
    }
    // valida si els 3 han arribat a fumar 3 cops
    public boolean waitSmokersDone() {
        int done = -1;
        while (done < 3) {
            done = 0;
            for (Fumador fum : fumadors) {
                if (fum.getFumades() == 3) {
                    done++;
                }
            }
        }
        return done == 3;
    }
    public void compresFinalitzades() {
        estanc.tancarEstanc();
    }
    public static void main(String[] args) {
        Barri barri = new Barri();
        barri.fumadorsCompren();
        barri.obrirEstanc();
        if (barri.waitSmokersDone()) {
            barri.compresFinalitzades();
        }
        System.err.println("Estanc tancat");
    }
}
