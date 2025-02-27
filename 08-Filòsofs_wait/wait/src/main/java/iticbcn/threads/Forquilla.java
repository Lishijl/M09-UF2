package iticbcn.threads;

public class Forquilla {
    private int propietari;
    // control de si té propietari
    public static final int LLIURE = -1;
    private int numF;
    public Forquilla() {}
    public Forquilla(int numForquilla) {
        this.numF = numForquilla;
        this.propietari = LLIURE;
    }

    public int getNumF() {
        return numF;
    }

    public void setNumF(int numF) {
        this.numF = numF;
    }

    public int getPropietari() {
        return propietari;
    }

    public void setPropietari(int propietari) {
        this.propietari = propietari;
    }
}
