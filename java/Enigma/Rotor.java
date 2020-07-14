package CipherSolver.Enigma;

import CipherSolver.Utils;

public class Rotor {
    int[] wiring, inverse;
    int grund, rings;
    int notch1, notch2;
    RotorType type;

    public Rotor(){
        wiring = new int[26];
        inverse = new int[26];
        setWiring(RotorType.RotorI);
        resetPosition();
    }
    public Rotor(RotorType rotorType){
        wiring = new int[26];
        inverse = new int[26];
        setWiring(rotorType);
        resetPosition();
    }

    public void resetPosition(){
        grund = 0; rings = 0;
    }
    public void setWiring(RotorType rotorType){
        String wiringData = rotorType.getValue();
        notch1 = Utils.getCharCode(wiringData.charAt(27));
        notch2 = Utils.getCharCode(wiringData.charAt(28));
        for (int i = 0; i < 26; i++) {
            int code = Utils.getCharCode(wiringData.charAt(i));
            wiring[i] = code;
            inverse[code] = i;
        }
        this.type = rotorType;
    }
    public void step(){
        grund = (grund + 1) % 26;
    }
    public boolean isOnNotch(){
        return grund == notch1 || grund == notch2;
    }
    public int input(int letter, boolean reverse){
        int offset = grund - rings;
        if (!reverse)
            return Utils.mod(wiring[Utils.mod(letter + offset, 26)] - offset, 26);
        return Utils.mod(inverse[Utils.mod(letter + offset, 26)] - offset, 26);
    }
    public String toString(){
        return type.toString();
    }
}
