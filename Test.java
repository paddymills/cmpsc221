
import java.util.Random;
import java.lang.Math;

public class Test {
    public static void main(String[] args) {
        Random generator = new Random();
        int doubleDigits = generator.nextInt(90) + 10;
        System.out.println(doubleDigits);
    }
}
