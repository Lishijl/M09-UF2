package iticbcn.threads;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class BanyUnisex {
    private final int BANY_BUIT = 0, BANY_AMB_HOMES = -1, BANY_AMB_DONES = 1;
    private int estatActual;

    private int ocupants; // comptador++
    private final int CAPACITAT_MAX = 3;

    private Semaphore capacitat;
    private ReentrantLock lockEstat;
    public BanyUnisex() {
        this.estatActual = BANY_BUIT;
        this.capacitat = new Semaphore(CAPACITAT_MAX, true);
        this.lockEstat = new ReentrantLock(true);
    }
    /* 3 constants, iniciaitzat, estat = buit, ocupants = 0, asignar al semafor max , y reantrant lock tb es fair
     * entrahome es un while true, bloqueig, bloqueja primer el dloc, sino pot passar de homes i dones
     * bloqueja estat en home, ees queda en home, try, o es buit o amb homes.
     * intento adquirir una posicio de lavabo buit, fins true
     * llavos si es buit, passa a lestat buit a home. augmenta ocupants, printejo i trenco amb break
     * finalment try sense catch, pero finaly, si desbloqueja el lockestat amb unlock, i 
     * perque ja no em pertany despres del try, espero 100 milis, per no saturar al tornar
     * a entrar, mentres.. amb el while home a home, espera 100 milisegons
     * 
     * entrar dona= que el de home.
     * 
     * pero sortir locestat.lock() mentres surto si que mantinc lesac
     * igualo si son estat homes, resto ocupants.
     * allibero capacitat amb realise
     * si ocupants son zero passo a estat buit.
     * 
     * finalment desbloqueja amb unlock en el finally.
     * 
     * sigui amb while true o no, fem finally per alliberar sempre el recurs.
     * 
     * la dona pot sortir usant tot i els lavabos siguin buits. es questio de print
     * 
     * 
     */
    public void entraHome() {
        if (estatActual == BANY_AMB_HOMES) {
            if (capacitat.tryAcquire()) {
                ocupants++;
            }
        }
    }
    public void entraDona() {
        if (estatActual == BANY_AMB_DONES) {
            if (capacitat.tryAcquire()) {
                ocupants++;
            }
        }
    }
    public void surtHome() {
        ocupants--;
        if (ocupants == 0) {
            estatActual = BANY_BUIT;
        }
    }
    public void surtDona() {

    }
    public void llancaFilsHomesDones(Home[] homes, Dona[] dones) {
        for (Home h : homes) { h.start(); }
        for (Dona d : dones) {  d.start(); }
    }
    public static void main(String args[]) {
        BanyUnisex banyUnisex = new BanyUnisex();
        Home[] homes = new Home[5];
        Dona[] dones = new Dona[5];
        for (int i = 0; i < homes.length; i++) {
            homes[i] = new Home(banyUnisex, "Home-" + i);
            dones[i] = new Dona(banyUnisex, "Dona-" + i);
        }
        banyUnisex.llancaFilsHomesDones(homes, dones);
    }
}
