package iticbcn.threads;

public class Client {
    private String nom;
    public Client(int id) {
        this.nom = "Client-" + id;
    }
    public void tallarseElCabell() {
        System.out.printf("\nTallant cabell a %s", nom);
    }
    public String getNom() {
        return nom;
    }
}
