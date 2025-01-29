package iticbcn.threads;

public class Compte {
    private float saldo;
    // podria ser interessant usar Volatile per evitar problemes de memoria de cache
    // recurs compartit i unic per tots els socis, com un pool de connexions de serveis
    private static Compte instance = null;

    // es privada perque no es pot crear noves instancies si no es desde el propi compte
    private Compte() { this.saldo = 0.0f; }

    public float getSaldo() { return this.saldo; }

    // acumula o resta directament el saldo intern del compte sense treure'l per fer la operativa
    public void setSaldo(float aportacio) { this.saldo += aportacio; }

    // un acces concurrent pot generar multiples instancies del Singleton, que mes endevant 
    // ho solucionarem amb el synchronized
    // metode static per obtenir una unica instancia si no existeix el crea per ser singleton
    public static Compte getInstance() {
        if (instance == null) instance = new Compte();
        return instance;
    }
}
