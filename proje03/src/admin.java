import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class admin extends JFrame {
    private JPanel adminPanel;
    private JTextField txtName;
    private JButton loginButton;
    private JPasswordField txtPassword;

    public admin() {
    loginButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String name=txtName.getText();
            String password=txtPassword.getText();


            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
                String query = "SELECT * FROM admin WHERE name = ? AND password = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, password);
                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    JOptionPane.showMessageDialog(null, "Login successful!");



                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password!");
                }

                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database access error: " + ex.getMessage());
            }

        }
    });


}

    public static void main(String[] args) {
        admin h=new admin();
        h.setContentPane(h.adminPanel);
        h.setTitle("Login Page");
        h.setSize(400,400);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
