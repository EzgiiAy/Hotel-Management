import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class newCustomer extends JFrame {
    private JPanel newCustomerPanel;
    private JTextField txtIdentity;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtNumber;
    private JTextField txtCountry;
    private JTextField txtDeposit;
    private JComboBox comboBox1;
    private JButton saveButton;
    private JButton backButton;

    public newCustomer() {
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String identity=txtIdentity.getText();
                String name=txtName.getText();
                String surname=txtSurname.getText();
                String number=txtNumber.getText();
                String country=txtCountry.getText();
                String deposit=txtDeposit.getText();
                String gender=comboBox1.getName();


                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
                    String query = "INSERT INTO customer (identity, name, surname, number, country, deposit,gender) VALUES (?, ?, ?, ?, ?,?,?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, identity);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, surname);
                    preparedStatement.setString(4, number);
                    preparedStatement.setString(5, country);
                    preparedStatement.setString(6, deposit);
                    preparedStatement.setString(7, gender);
                    preparedStatement.executeUpdate();

                    JOptionPane.showMessageDialog(newCustomerPanel, "Data saved successfully!");
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(newCustomerPanel, "Error saving to database: " + ex.getMessage());
                }






            }
        });
        newCustomerPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                String[] veriler = {"female", "male"};

                for (String veri : veriler) {
                    comboBox1.addItem(veri);
                }

            }
        });
    }




    public static void main(String[] args) {

        newCustomer h=new newCustomer();
        h.setContentPane(h.newCustomerPanel);
        h.setTitle("New Customer Add Page");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);











    }
}
