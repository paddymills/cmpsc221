package Exam1;

/**
 * name: Patrick Miller
 * date: 3/2/2021
 * class: CMPSC 221
 * assignment: Exam 1
 */

public class Account {
    
    private String name;
    private double balance;

    public Account() {
        name = "New Account Holder";
        balance = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void setBalance(double newBalance) {
        balance = newBalance;
    }

    public void deposit(double depositAmount) {
        if (depositAmount > 0.0)
            balance += depositAmount;

        else
            System.out.println("Deposit amount is invalid");
    }

    public String toString() {
        return name + String.format(": $%.2f", balance);
    }

}
