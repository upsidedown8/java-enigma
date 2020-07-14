package CipherSolver;

import CipherSolver.Enigma.Enigma;

public class Main {

    public static void main(String[] args) {
        Enigma enigma = new Enigma();

        System.out.println(enigma);
        enigma.randomise();
        String plaintext = "Secret message";
        String ciphertext = enigma.run(plaintext);
        System.out.println(enigma);
        System.out.println(ciphertext);
    }
}