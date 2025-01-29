package iticbcn.threads;

import java.util.Random;

public class Soci extends Thread{
    private final Compte COMPTE;
    private final float APORTACIO;
    private final int ESPERA_MAX;
    private final Random RND;
    private final int MAX_ANYS;

    public Soci(String nom) {
        super(nom);
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
        for (int any = 0; any < MAX_ANYS; any++) {
            for (int mes = 0; mes < 12; mes++) {
                // faing un control de l'objecte COMPTE, per a que només puguin accedir
                // 1 sol fil per cada execució de ficar i treure quota, no per separat
                // perquè un segon fil podria accedir a treure diners
                synchronized (COMPTE) {
                    if (mes%2 == 0) {
                        // ingresa quota
                        COMPTE.setSaldo(APORTACIO);
                    } else {
                        // treu quota amb una aportació negativa
                        COMPTE.setSaldo(-APORTACIO);
                    }
                }
                try {
                    sleep(RND.nextInt(ESPERA_MAX));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
