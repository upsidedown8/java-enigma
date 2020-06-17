package CipherSolver.Enigma;

import CipherSolver.Utils;

public class Reflector {
    int[] wiring;
    ReflectorType type;

    public Reflector(){
        wiring = new int[26];
        setWiring(ReflectorType.ReflectorA);
    }
    public Reflector(ReflectorType reflectorType){
        wiring = new int[26];
        setWiring(reflectorType);
    }

    public void setWiring(ReflectorType reflectorType){
        String wiringData = reflectorType.getValue();
        for (int i = 0; i < 26; i++) {
            int code = Utils.getCharCode(wiringData.charAt(i));
            wiring[i] = code;
        }
        this.type = reflectorType;
    }
    public int input(int letter){
        return wiring[letter];
    }
    public String toString(){
        return type.toString();
    }
}
