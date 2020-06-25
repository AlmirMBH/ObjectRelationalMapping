package bank.userInterface;

import bank.model.BankAccount;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BankFrame extends JFrame{

    private JLabel fromLabel = new JLabel("Sa računa:");
    private JLabel toLabel = new JLabel("Na račun:");
    private JLabel amountLabel = new JLabel("Iznos:");
    
    private JTextField amountTextField = new JTextField(10);
    
    private JComboBox<BankAccount> fromComboBox = new JComboBox<>();
    private JComboBox<BankAccount> toComboBox = new JComboBox<>();
    
    private JButton transferButton = new JButton("Execute");

    public BankFrame() {
        super("Money Transfer System");
        setSize(500, 500);
        setLayout(new GridLayout(4, 1));
        
        JPanel fromPanel = new JPanel();
        JPanel amountPanel = new JPanel();
        JPanel toPanel = new JPanel();
        JPanel transferPanel = new JPanel();
        
        add(fromPanel);
        add(amountPanel);
        add(toPanel);
        add(transferPanel);
        
        fromPanel.add(fromLabel);
        List<BankAccount> bankAccounts = BankAccount.loadAll();
        bankAccounts.forEach(bankAccount -> fromComboBox.addItem(bankAccount));
        
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        
        fromPanel.add(fromComboBox);
        toPanel.add(toLabel);
        bankAccounts.forEach(bankAccount -> toComboBox.addItem(bankAccount));
        toPanel.add(toComboBox);
        
        transferButton.addActionListener((event)->executeTransfer(event));
        transferPanel.add(transferButton);
    }
    
    private void executeTransfer(ActionEvent event){
        
        BankAccount fromAccount = (BankAccount) fromComboBox.getSelectedItem();
        BankAccount toAccount = (BankAccount) toComboBox.getSelectedItem();
        Double amount = Double.parseDouble(amountTextField.getText());
        BankAccount.transferMOney(fromAccount, toAccount, amount);
        
    }
    
    public void showFrame(){
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args) {
        
        BankFrame bankFrame = new BankFrame();        
        SwingUtilities.invokeLater(bankFrame::showFrame);        
    }
}
