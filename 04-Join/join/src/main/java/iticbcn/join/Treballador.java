package iticbcn.join;

import java.util.Random;

public class Treballador extends Thread {
    // sou assignat
    private float sou_anual_brut;
    private int edat_inici_treball;
    private int edat_fi_treball;
    private String nom;
    private int edat_actual;
    // diners guanyats en net durant tot els anys que s'ha treballat
    private float cobrat;
    // espera aleatoria del cobra i paga, amb 100 ms
    private Random rnd = new Random();

    // acumula a cobrat els sous mensuals nets de deduir-li els impostos
    public void cobra(float souBrut) { this.cobrat += souBrut/12; }

    public double pagaImpostos(float salariBrut) {
        // retorna el salari mensual net d'haver pagat el 34% d'impostos
        return salariBrut - (salariBrut * 0.24);
    }
}
