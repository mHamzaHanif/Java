import java.util.Scanner;

class Account {
    private int balance;

    public Account(int balance) 
        { this.balance=balance; }

    public boolean isSufficientBalance(int amt) {
        if (this.balance > amt)
            return(true);
        else 
            return(false);
    }

    public void withdraw(int amt) {
        this.balance = this.balance - amt;

        System.out.println("Withdrawl money: "+ amt);
        System.out.println("Current balance: " + this.balance);
    }
}

class Customer implements Runnable
{
    private String name;
    private Account account;

    public Customer(String name, Account account)
        {this.account = account; this.name = name; }
    
    public void run()
    {
        Scanner input = new Scanner(System.in);

        synchronized(account) 
        {
            System.out.println("Enter amount to withdraw: ");
            int amt = input.nextInt();
            
            if (account.isSufficientBalance(amt)) {
                System.out.println(name);
                account.withdraw(amt);
            }
            else 
                System.out.println("Insufficient Balance!");
        }
    } 
}

public class Synchronization {
    public static void main(String[] args) {
        Account a1 = new Account(1000);
        
        Customer c1=new Customer("Remeo", a1), c2=new Customer("Juluit", a1);
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        
        t1.start();
        t2.start();
    }    
}