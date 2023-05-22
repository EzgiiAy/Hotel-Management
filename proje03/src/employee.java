import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class employee extends JFrame {
    private JPanel employeePanel;
    private JTextField txtIdentity;
    private JTextField txtName;
    private JTextField txtSurname;
    private JTextField txtNumber;
    private JTextField txtDepartment;
    private JTextField txtAdress;
    private JButton Save;

    public employee() {
    Save.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String identity=txtIdentity.getText();
            String name=txtName.getText();
            String surname=txtSurname.getText();
            String number=txtNumber.getText();
            String department=txtDepartment.getText();
            String adress=txtAdress.getText();


            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
                String query = "INSERT INTO employee (identity, name, surname, number, department, adress) VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, identity);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, number);
                preparedStatement.setString(5, department);
                preparedStatement.setString(6, adress);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(employeePanel, "Data saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(employeePanel, "Error saving to database: " + ex.getMessage());
            }


        }
    });
}

    public static void main(String[] args) {
        employee h=new employee();
        h.setContentPane(h.employeePanel);
        h.setTitle("Employee Add Page");
        h.setSize(700,700);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
