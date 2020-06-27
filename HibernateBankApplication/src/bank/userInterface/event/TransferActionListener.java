package bank.userInterface.event;

import bank.model.BankAccount;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TransferActionListener implements ActionListener{

    // BankAccount fromAccount, BankAccount toAccount, double amount
    
    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    private final double amount;

    public TransferActionListener(BankAccount fromAccount, BankAccount toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        BankAccount.transferMOney(fromAccount, toAccount, amount);
    }
    
    
    
}
