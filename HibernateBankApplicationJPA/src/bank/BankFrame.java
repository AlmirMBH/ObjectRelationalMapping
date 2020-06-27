package bank;

import bank.model.Bankaccount;
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
    
    
/*  =================================================================================      
      KREIRANJE POLJA KORISNIČKOG INTERFACE-A (LABEL, BUTTON, TEXTFIELD, COMBOBOX)
    =================================================================================    
*/     
    private JLabel fromLabel = new JLabel("Sa računa:");
    private JLabel toLabel = new JLabel("Na račun:");
    private JLabel amountLabel = new JLabel("Iznos:");
    
    private JTextField amountTextField = new JTextField(10);
    
    private JComboBox<Bankaccount> fromComboBox = new JComboBox<>();
    private JComboBox<Bankaccount> toComboBox = new JComboBox<>();
    
    private JButton transferButton = new JButton("Execute");

/*  =================================================================================      
            KREIRANJE FRAME-A I PANELA ZA SVAKI RED KORISNIČKOG INTERFACE-A
    =================================================================================    
*/     
    public BankFrame() {
        super("Money Transfer System");
        setSize(500, 500);
        setLayout(new GridLayout(4, 1));
        
        JPanel fromPanel = new JPanel();
        JPanel amountPanel = new JPanel();
        JPanel toPanel = new JPanel();
        JPanel transferPanel = new JPanel();

/*  =================================================================================      
            DODAVANJE 4 PANELA U GLAVNI FRAME (KORISNIČKI INTERFACE/WINDOW)
    =================================================================================    
*/         
        add(fromPanel);
        add(amountPanel);
        add(toPanel);
        add(transferPanel);

/*  =================================================================================      
            DODAVANJE POLJA KORISNIČKOG INTERFACE-A U PANELE
    =================================================================================    
*/         
        fromPanel.add(fromLabel);
        List<Bankaccount> bankAccounts = Bankaccount.loadAll();
        bankAccounts.forEach(bankAccount -> fromComboBox.addItem(bankAccount));
        fromPanel.add(fromComboBox);
        
        amountPanel.add(amountLabel);
        amountPanel.add(amountTextField);
        toPanel.add(toLabel);
        
        bankAccounts.forEach(bankAccount -> toComboBox.addItem(bankAccount));
        toPanel.add(toComboBox);
        transferButton.addActionListener(this::executeTransfer);        
        transferPanel.add(transferButton);
    }
    
    private void executeTransfer(ActionEvent e){
        
        Bankaccount fromAccount = (Bankaccount) fromComboBox.getSelectedItem();
        Bankaccount toAccount = (Bankaccount) toComboBox.getSelectedItem();
        Double amount = Double.parseDouble(amountTextField.getText());
        Bankaccount.transferMOney(fromAccount, toAccount, amount);
    }
        
    public void showFrame(){
        pack();
        setVisible(true);
    }

/*  =================================================================================      
            POZIVANJE KORISNIČKOG INTERFACE-A U NOVOM THREAD-U
    =================================================================================    
*/    
    public static void main(String[] args) {
        
        BankFrame bankFrame = new BankFrame();        
        SwingUtilities.invokeLater(bankFrame::showFrame);        
    }
    
}
