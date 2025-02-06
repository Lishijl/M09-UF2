package iticbcn.threads;

public class Forquilla {
    private boolean enUs;
    private int numF;
    
    public Forquilla() {}
    
    public Forquilla(int numForquilla) {
        this.numF = numForquilla;
        this.enUs = false;
    }

    public boolean isEnUs() {
        return enUs;
    }

    public void setEnUs(boolean enUs) {
        this.enUs = enUs;
    }

    public int getNumF() {
        return numF;
    }

    public void setNumF(int numF) {
        this.numF = numF;
    }
}
