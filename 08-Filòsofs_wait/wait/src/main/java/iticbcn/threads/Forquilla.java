package iticbcn.threads;

public class Forquilla {
    private int propietari;
    // control de si té propietari
    public final int LLIURE = -1;
    private int numF;
    public Forquilla() {}
    public Forquilla(int numForquilla) {
        this.numF = numForquilla;
        this.propietari = LLIURE;
    }
    // accions/operacions que es fan sobre el recurs forquilla, els comensals 
    // competeixen per agafar-la, apliquem synchronized, wait i notifyAll, 
    // per gestionar la disponibilitat d'aquest
    public synchronized boolean afagar(int idComensal) {
        // mentres no estigui disponible espera
        while (propietari != LLIURE) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // continúa amb l'assignació quan s'ocupa correctament
        propietari = idComensal;
        return true;
    }
    public synchronized boolean deixar() {
        propietari = LLIURE;
        // avisa a tots els fils en espera
        notifyAll();
        return true;
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
