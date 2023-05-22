import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class adminMenu extends JFrame {
    private JButton driversButton;
    private JPanel page;
    private JButton roomsButton;
    private JButton employeeButton;
    private JButton newCustomerButton;
public adminMenu() {
    driversButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    });
}

    public static void main(String[] args) {
        adminMenu h=new adminMenu();
        h.setContentPane(h.page);
        h.setTitle("Admın Menu Page");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
