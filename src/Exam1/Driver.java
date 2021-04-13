package Exam1;

/**
 * name: Patrick Miller
 * date: 3/2/2021
 * class: CMPSC 221
 * assignment: Exam 1
 */

public class Driver {
    public static void main(String[] args) {
        Account johnOliver = new Account();
        Account jamieCurtis = new Account();

        // set up John
        johnOliver.setName("John Oliver");
        johnOliver.setBalance(573.25);

        // set up Jamie
        jamieCurtis.setName("Jamie Curtis");
        jamieCurtis.setBalance(200.42);

        // display initial balances
        System.out.println("Bank Account Balances:");
        System.out.println(johnOliver.toString());
        System.out.println(jamieCurtis.toString());

        // empty line
        System.out.println("");

        // Jamie deposit
        System.out.println("Depositing 100.00 in account for Jamie Curtis");
        jamieCurtis.deposit(100.00);

        // empty line
        System.out.println("");
        
        // John deposit (fails)
        System.out.println("Depositing 0.00 in account for John Oliver");
        johnOliver.deposit(0.0);

        // empty line
        System.out.println("");

        // display final balances
        System.out.println("Bank Account Balances");
        System.out.println(johnOliver.toString());
        System.out.println(jamieCurtis.toString());
    }
}
