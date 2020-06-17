package CipherSolver;

import java.security.SecureRandom;
import java.util.Random;

public class Utils {
    ///////////////////////////////////
    /////////  MATHEMATICAL  //////////
    ///////////////////////////////////
    public static int mod(int x, int y){
        int result = x % y;
        while (result < 0)
            result += y;
        return result;
    }

    ///////////////////////////////////
    ///////  CHARS AND STRINGS  ///////
    ///////////////////////////////////
    public static int getCharCode(char c){
        return Character.toUpperCase(c) - 'A';
    }
    public static char getCharFromCode(int code){
        return (char)(code + 'A');
    }
    public static boolean isLegalLetter(int letter){
        return 0 <= letter && letter <= 25;
    }
    public static boolean isAlphabeticLetter(char letter){
        return isLegalLetter(getCharCode(letter));
    }
    public static boolean isInRange(int value, int minInclusive, int maxExclusive){
        return minInclusive < value && value <= maxExclusive;
    }
    public static boolean containsChar(String s, char search) {
        if (s.length() == 0)
            return false;
        else
            return s.charAt(0) == search || containsChar(s.substring(1), search);
    }

    ///////////////////////////////////
    ////////////  ARRAYS  /////////////
    ///////////////////////////////////
    public static void shuffleArray(int[] array, Random random){
        for (int i = 0; i < array.length; i++) {
            swap(array, i, mod(random.nextInt(), array.length));
        }
    }
    public static void shuffleArray(int[] array, SecureRandom secureRandom){
        for (int i = 0; i < array.length; i++) {
            swap(array, i, mod(secureRandom.nextInt(), array.length));
        }
    }
    public static void swap(int[] array, int index1, int index2){
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
    public static void fillArrayConsecutive(int[] array){
        fillArrayConsecutive(array, 0);
    }
    public static void fillArrayConsecutive(int[] array, int start){
        for (int i = start; i < start + array.length; i++)
            array[i] = i;
    }
}