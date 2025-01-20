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

    public Treballador(String nom, float salariAnual, int edatIni, int edatFi) {
        super(nom);
        this.sou_anual_brut = salariAnual;
        this.edat_inici_treball = edatIni;
        this.edat_fi_treball = edatFi;
        this.edat_actual = 0;
        this.cobrat = 0.0f;
    }
    public String getNom() { return nom; }
    public int getEdat() { return edat_actual; }
    public float getCobrat() { return cobrat; }

    // acumula a cobrat els sous mensuals nets de deduir-li els impostos
    public void cobra(float souBrut) { this.cobrat += pagaImpostos(souBrut/12); }

    public float pagaImpostos(float salariBrut) {
        // retorna el salari mensual net d'haver pagat el 24% d'impostos
        return salariBrut - (salariBrut * 0.24f);
    }
    @Override
    public void run() {
        
    }

    
}
