import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class drivers extends JFrame {
    private JPanel driversPanel;
    private JTextField txtIdentity;
    private JTextField txtName;
    private JTextField txtSurname;
    private JButton saveButton;
    private JTextField txtNumber;
    private JTextField txtLocation;
    private JTextField txtCarsNumber;
    private JButton backButton;



    public drivers() {
    saveButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String identity=txtIdentity.getText();
            String name=txtName.getText();
            String surname=txtSurname.getText();
            String number=txtNumber.getText();
            String location= txtLocation.getText();
            String carsNumber= txtCarsNumber.getText();


            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
                String query = "INSERT INTO drivers (identity, name, surname, number, location, carsNumber) VALUES (?, ?, ?, ?, ?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, identity);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, number);
                preparedStatement.setString(5, location);
                preparedStatement.setString(6, carsNumber);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(driversPanel, "Data saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(driversPanel, "Error saving to database: " + ex.getMessage());
            }








        }
    });
}

    public static void main(String[] args) {
        drivers h=new drivers();
        h.setContentPane(h.driversPanel);
        h.setTitle("Drivers Add Page");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
