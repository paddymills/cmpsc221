package BinaryMath;

import java.util.Random;

public class BinaryNumber {
    
    private static final int RANDOM_NUMBER_RANGE = 1000;
    private int[] number;

    public BinaryNumber(int length) {

        // initialize number array at length
        number = new int[length];
        
        // initialize random number generator
        Random randomNumber = new Random();
        
        // generate random binary digits
        for (int i=0; i<length; i++)
            number[i] = randomNumber.nextInt(RANDOM_NUMBER_RANGE) % 2;
    
    }

    public BinaryNumber(int[] num) {
        number = num;
    }

    public String toString() {

        String result = new String();

        for (int digit : number) {
            result += digit;
        }

        return result;
    
    }

    public BinaryNumber add(BinaryNumber other) {
        int[] result = new int[number.length];

        return new BinaryNumber(result);
    }

    public BinaryNumber subtract(BinaryNumber other) {
        int[] result = new int[number.length];

        return new BinaryNumber(result);
    }

    public BinaryNumber multiply(BinaryNumber other) {
        int[] result = new int[number.length];

        return new BinaryNumber(result);
    }

}
