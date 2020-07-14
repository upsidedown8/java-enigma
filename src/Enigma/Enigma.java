package CipherSolver.Enigma;

import CipherSolver.Utils;

import java.security.SecureRandom;

public class Enigma {
    //Components
    SecureRandom secureRandom;
    Plugboard plugboard;
    Reflector reflector;
    Rotor left, middle, right;

    //Parts of encryption/decryption process
    int rotorPass(int letter, boolean reverse){
        if (reverse)
            return right.input(middle.input(left.input(letter, reverse), reverse), reverse);
        return left.input(middle.input(right.input(letter, reverse), reverse), reverse);
    }
    void stepRotors(){
        if (middle.isOnNotch()) {
            left.step();
            middle.step();
        }
        else if (right.isOnNotch())
            middle.step();
        right.step();
    }

    //Functionality
    public Enigma(){
        secureRandom = new SecureRandom();
        resetAll();
    }
    public String run(String text){
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char letter = text.charAt(i);
            if (Utils.isAlphabeticLetter(letter)) {
                boolean isLower = Character.isLowerCase(letter);
                int letterValue = Utils.getCharCode(letter);
                stepRotors();
                letterValue = plugboard.input(letterValue);
                letterValue = rotorPass(letterValue, false);
                letterValue = reflector.input(letterValue);
                letterValue = rotorPass(letterValue, true);
                letterValue = plugboard.input(letterValue);
                letter = Utils.getCharFromCode(letterValue);
                if (isLower)
                    letter = Character.toLowerCase(letter);
            }
            stringBuilder.append(letter);
        }
        return stringBuilder.toString();
    }

    //Settings
    public String toString(){
        return "Enigma M3 1942 Naval Simulator Settings\n" +
                "UKW: " + reflector.toString() + '\n' +
                "Left: " + left.toString() + '\n' +
                "Middle: " + middle.toString() + '\n' +
                "Right: " + right.toString() + '\n' +
                "Grund: " +
                left.grund + ' ' +
                middle.grund + ' ' +
                right.grund + ' ' + '\n' +
                "Rings: " +
                left.rings + ' ' +
                middle.rings + ' ' +
                right.rings + ' ' + '\n' +
                "Plugboard: " + plugboard.toString();
    }
    public void randomise(){
        setRotorGrund(Utils.mod(secureRandom.nextInt(), 26), Utils.mod(secureRandom.nextInt(), 26), Utils.mod(secureRandom.nextInt(), 26));
        setRotorRings(Utils.mod(secureRandom.nextInt(), 26), Utils.mod(secureRandom.nextInt(), 26), Utils.mod(secureRandom.nextInt(), 26));

        int[] rotorValues = new int[8];
        Utils.fillArrayConsecutive(rotorValues);
        Utils.shuffleArray(rotorValues, secureRandom);
        RotorType[] rotorTypes = {
                RotorType.RotorI, RotorType.RotorII, RotorType.RotorIII, RotorType.RotorIV, RotorType.RotorV,
                RotorType.RotorVI, RotorType.RotorVII, RotorType.RotorVIII };
        setRotorOrder(rotorTypes[rotorValues[0]], rotorTypes[rotorValues[1]], rotorTypes[rotorValues[2]]);

        ReflectorType[] reflectorTypes = { ReflectorType.ReflectorA, ReflectorType.ReflectorB, ReflectorType.ReflectorC };
        setReflector(reflectorTypes[Utils.mod(secureRandom.nextInt(), 3)]);

        resetPlugboard();

        int[] values = new int[26];
        Utils.fillArrayConsecutive(values);
        Utils.shuffleArray(values, secureRandom);
        int numberOfPlugs = Utils.mod(secureRandom.nextInt(), 8) + 5;
        for (int i = 0; i < numberOfPlugs; i++) {
            plugboard.addPlug(values[i*2], values[i*2+1]);
        }
    }

    public void resetPlugboard(){
        plugboard.clear();
    }
    public void resetRotorPositions(){
        left.resetPosition();
        middle.resetPosition();
        right.resetPosition();
    }
    public void resetAll(){
        reflector = new Reflector(ReflectorType.ReflectorB);
        left = new Rotor(RotorType.RotorI);
        middle = new Rotor(RotorType.RotorII);
        right = new Rotor(RotorType.RotorIII);
        plugboard = new Plugboard();
    }

    public void setRotorGrund(int left, int middle, int right){
        if (Utils.isInRange(left, 0, 26) ||
                Utils.isInRange(middle, 0, 26) ||
                Utils.isInRange(right, 0, 26))
            throw new IllegalArgumentException("Positions must be in the range 0-25");
        this.left.grund = left;
        this.middle.grund = middle;
        this.right.grund = right;
    }
    public void setRotorRings(int left, int middle, int right){
        if (Utils.isInRange(left, 0, 26) ||
                Utils.isInRange(middle, 0, 26) ||
                Utils.isInRange(right, 0, 26))
            throw new IllegalArgumentException("Positions must be in the range 0-25");
        this.left.rings = left;
        this.middle.rings = middle;
        this.right.rings = right;
    }
    public void setRotorOrder(RotorType left, RotorType middle, RotorType right){
        if (left == middle || left == right || middle == right)
            throw new IllegalArgumentException("Rotors can only be used once");
        this.left.setWiring(left);
        this.middle.setWiring(middle);
        this.right.setWiring(right);
    }
    public void setReflector(ReflectorType reflector){
        this.reflector.setWiring(reflector);
    }

    public void addPlug(char plug1, char plug2){
        if (!Utils.isAlphabeticLetter(plug1) || !Utils.isAlphabeticLetter(plug2))
            throw new IllegalArgumentException("Plugs must be alphabetic");
        plugboard.addPlug(Utils.getCharCode(plug1), Utils.getCharCode(plug2));
    }
    public void removePlug(char plug1, char plug2){
        if (!Utils.isAlphabeticLetter(plug1) || !Utils.isAlphabeticLetter(plug2))
            throw new IllegalArgumentException("Plugs must be alphabetic");
        plugboard.removePlug(Utils.getCharCode(plug1), Utils.getCharCode(plug2));
    }
}
