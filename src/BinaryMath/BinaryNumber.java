package BinaryMath;

import java.util.Arrays;
import java.util.Random;

import java.lang.Math;

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

    public BinaryNumber(String num) {
        // create empty BinaryNumber
        this(new int[num.length()]);
        
        // copy number string to object
        //  string must be reversed
        for (int i = num.length(); i > 0; i--) {
            number[num.length() - i] = Character.getNumericValue(num.charAt(i-1));
        }
    }

    public String toString() {

        String result = new String();

        for (int i = number.length - 1; i >= 0; i--)
            result += number[i];

        return result;
    }

    public boolean equals(BinaryNumber other) {
        return this.toString().equals(other.toString());
    }

    public int getLength() {
        return number.length;
    }

    public int digitAt(int index) {
        return number[index];
    }

    public void setDigit(int index, int value) {
        number[index] = value;
    }

    public boolean greaterThan(BinaryNumber other) {
        // equal to -> false
        if (this.equals(other))
            return false;

        // iterate from highest order digit to lowest order digit
        //  if at any point other's digit is greater than this's digit,
        //  other is greater
        int maxLength = Math.max(number.length, other.getLength());
        for (int i = maxLength; i > 0; i--) {
            // mismatched lengths
            if (i >= number.length) {
                // other is longer and has a 1 (not zero padded)
                if (other.digitAt(i) > 0)
                    return false;

                // else -> next iteration
            } 
            else if (i >= other.getLength()) {
                // this is longer and has a 1 (not zero padded)
                if (number[i] > 0)
                    return true;

                // else -> next iteration
            }

            else if (number[i-1] < other.digitAt(i-1))
                // this < other;
                return false;
        }

        // this >= other;
        return true;
    }

    public BinaryNumber add(BinaryNumber other) {
        // mismatch lengths -> add must be called from longer number
        if (number.length < other.getLength())
            return other.add(this);


        int[] result = new int[number.length + 1];
        int carry = 0;
        int sum;

        for (int i=0; i<number.length; i++) {

            // mismatched number lengths -> other has no more digits
            if (i >= other.getLength()) {
                result[i] = number[i];
            }

            sum = number[i] + other.digitAt(i) + carry;

            /* 
                *************************
                *  sum | result | carry *
                * --------------------- *
                *   3  |    1   |   1   * 
                *   2  |    0   |   1   * 
                *   1  |    1   |   0   * 
                *   0  |    0   |   0   * 
                *************************
            */

            if (sum > 1) {
                result[i] = sum - 2;
                carry = 1;
            }
            else {
                result[i] = sum;
                carry = 0;
            }
        }

        return new BinaryNumber(result);
    }

    public BinaryNumber subtract(BinaryNumber other) {

        // make sure top number is greater than bottom number
        if (other.greaterThan(this))
            return other.subtract(this);
        
        int[] result = new int[number.length];

        // create copy of number
        //  subtraction can cause mutations, and we should preserver the original number
        int[] num = Arrays.copyOf(number, number.length);

        for (int i=0; i<num.length; i++) {
            //   0      1      1
            // - 0 OR - 0 OR - 1
            // ---    ---    ---
            //   0      1      0
            if (num[i] - other.digitAt(i) > 0)
                result[i] = num[i];

            //   0
            // - 1
            // ---
            //   ?
            else {
                // any time you carry a number,
                // you are basically doing 2-1=1
                result[i] = 1;

                // find next 1 digit
                for (int j = i + 1; j < num.length; j++) {
                    if (num[j] == 1) {
                        num[j] = 0;
                        break;
                    }

                    // 0 encountered
                    //  will require moving to the next digit
                    //  would require recursive subtraction
                    //  but we can shorten this to make it equal to 1
                    num[j] = 1;
                }
                
            }
        }

        return new BinaryNumber(result);
    }

    public BinaryNumber multiply(BinaryNumber other) {
        BinaryNumber result = new BinaryNumber(number.length * 2);
        BinaryNumber product = new BinaryNumber(number.length * 2);

        for (int i = 0; i < other.getLength(); i++) {
            if (other.digitAt(i) == 0)
                continue;

            // add preceding zeros
            for (int j = 0; j < i; j++)
                product.setDigit(j, 0);

            // add number to product
            for (int j = 0; j < number.length; j++) {
                product.setDigit(i+j, number[i]);
            }

            // add row to result
            result = result.add(product);
        }

        return result;
    }

}
