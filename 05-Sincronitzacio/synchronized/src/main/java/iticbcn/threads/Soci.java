package iticbcn.threads;

import java.util.Random;

public class Soci extends Thread{
    private final Compte COMPTE;
    private final float APORTACIO;
    private final int ESPERA_MAX;
    private final Random RND;
    private final int MAX_ANYS;

    public Soci() {
        this.COMPTE = Compte.getInstance();
        this.APORTACIO = 10f;
        this.ESPERA_MAX = 100;
        this.RND = new Random();
        this.MAX_ANYS = 10;
    }

    public Compte getCompte() { return this.COMPTE; }

    // el procediment que fara tots els socis al llarg de 10 anys
    @Override
    public void run() {
        float saldoCompte = this.COMPTE.getSaldo();
        for (int any = 0; any < this.MAX_ANYS; any++) {
            for (int mes = 0; mes < 12; mes++) {
                // ingresa quota
                if (mes%2 == 0) {
                    this.COMPTE.setSaldo(this.APORTACIO);
                } else {
                    // treu quota amb una aportació negativa
                    this.COMPTE.setSaldo(-this.APORTACIO);
                }
            }
        }
    }
}
