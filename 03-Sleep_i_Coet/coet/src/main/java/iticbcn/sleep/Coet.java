package iticbcn.sleep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Coet {
    private Motor[] motors;

    // quan es crea un coet, s'instancien 4 motors amb el seu num de motor "i"
    public Coet() {
        this.motors = new Motor[4];
        for (int i = 0; i < this.motors.length; i++) {
            this.motors[i] = new Motor(i);
        }
    }
    // valida valor de potencia o llança error
    public void passaAPotencia(int p) {
        if (p >= 0 && p <= 10) {
            for (Motor mot : motors) {
                mot.setPotencia(p);
            }
        } else {
            throw new IllegalArgumentException("Valor de potència invàlid, només entre 0 i 10.");
        }
    }
    // executa els 4 motors
    public void arranca() {
        for(Motor mot : motors) { mot.start(); }
    }
    // bucle que demana potencia mentres estigui true i arranca el coet amb la potencia passada
    public static void main(String[] args) throws IOException {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            Coet coet = new Coet();
            int pot;
            do { 
                pot = Integer.parseInt(input.readLine());
                System.out.printf("Passant a potència %d\n", pot);
                coet.passaAPotencia(pot);
                coet.arranca();
                // condició que serveix per acabar després de desincrementar la potencia dels motors
                if (pot == 0) break;
            } while (true);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
