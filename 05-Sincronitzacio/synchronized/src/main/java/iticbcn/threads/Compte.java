package iticbcn.threads;

public class Compte {
    private float saldo;
    // podria ser interessant usar Volatile per evitar problemes de memoria de caché
    // recurs compartit i únic per tots els socis, com un pool de connexions de serveis
    private static Compte instance = null;

    // és privada perquè no es pot crear noves instàncies si no es desde el propi compte
    private Compte() { 
        this.saldo = 0.0f;
    }

    public float getSaldo() { return this.saldo; }
    // acumula o resta directament el saldo intern del compte sense treure'l per fer la operativa
    public void setSaldo(float aportacio) { this.saldo += aportacio; }

    // un accés concurrent pot generar múltiples instàncies del Singleton, que més endevant 
    // ho solucionarem amb el synchronized
    // mètode static per obtenir una única instància si no existeix el crea per ser singleton
    public static Compte getInstance() {
        if (instance == null) {
            instance = new Compte();
        }
        return instance;
    }
}
