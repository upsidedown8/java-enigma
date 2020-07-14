package CipherSolver.Enigma;

import CipherSolver.Utils;

public class Plugboard {
    int[] substitution;

    public Plugboard(){
        substitution = new int[26];
        clear();
    }
    public void clear(){
        Utils.fillArrayConsecutive(substitution);
    }
    public void addPlug(int letter1, int letter2){
        plug(letter1, letter2, false);
    }
    public void removePlug(int letter1, int letter2){
        plug(letter1, letter2, true);
    }
    public int input(int letter){
        return substitution[letter];
    }

    public String toString(){
        String data = "";
        for (int i = 0; i < 26; i++)
            if (substitution[i] != i && !Utils.containsChar(data, (char)(i+'A')))
                data += (char)(i + 'A') + Character.toString((char)(substitution[i] + 'A')) + ' ';
        return data;
    }
    void plug(int letter1, int letter2, boolean reverse){
        if (!(Utils.isLegalLetter(letter1) && Utils.isLegalLetter(letter2)))
            throw new IllegalArgumentException("Letters are invalid range");
        if (letter1 == letter2)
            throw new IllegalArgumentException("Plugs cannot have the same value");
        if ((substitution[letter1] == letter1 || substitution[letter2] == letter2) && reverse)
            throw new IllegalArgumentException("One or both of the letters is not plugged to another");
	    else if (!(substitution[letter1] == letter1 || substitution[letter2] == letter2) && !reverse)
            throw new IllegalArgumentException("One or both of the letters is already plugged to another");

        substitution[letter1] = reverse ? letter1 : letter2;
        substitution[letter2] = reverse ? letter2 : letter1;
    }
}
