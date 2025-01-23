package iticbcn.threads;

public class Compte {
    private float saldo;
    private static Compte instance = null;
    
    public Compte() { 
        this.saldo = 0.0f;
    }

    public float getSaldo() { return this.saldo; }
    public void setSaldo(float saldo) { this.saldo = saldo; }

    // mètode static per obtenir una única instància si no existeix el crea per ser singleton
    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }
}
