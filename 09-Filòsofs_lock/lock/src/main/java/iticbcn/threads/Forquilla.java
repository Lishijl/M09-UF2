package iticbcn.threads;

import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private ReentrantLock bloqueig;
    private int num;
    public Forquilla() {}
    public Forquilla(int numForquilla) {
        this.num = numForquilla;
        // en principi hauria de ser més just, passa el que porta més temps esperant
        this.bloqueig = new ReentrantLock(true);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void agafar() { bloqueig.lock(); }
    public void deixar() { 
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
        }
    }

    public ReentrantLock getBloqueig() {
        return bloqueig;
    }

    public void setBloqueig(ReentrantLock bloqueig) {
        this.bloqueig = bloqueig;
    }
}
