import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class rooms extends JFrame {
    private JTextField txtRoomsName;
    private JTextField txtRoomsFloor;
    private JTextField txtRoomsSize;
    private JPanel roomsPanel;
    private JTextField txtRoomsPrice;
    private JButton roomsButton;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JButton backButton;

    public rooms() {
    roomsButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String roomName=txtRoomsName.getText();
            String roomFloor=txtRoomsFloor.getText();
            String roomSize=txtRoomsSize.getText();
            String roomPrice=txtRoomsPrice.getText();
            String bedType=comboBox1.getName();
            String availability=comboBox2.getName();



            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
                String query = "INSERT INTO rooms (roomsName, roomsFloor, roomsSize, roomsPrice,bedType,availability) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, roomName);
                preparedStatement.setString(2, roomFloor);
                preparedStatement.setString(3, roomSize);
                preparedStatement.setString(4, roomPrice);
                preparedStatement.setString(5, bedType);
                preparedStatement.setString(6, availability);

                preparedStatement.executeUpdate();

                JOptionPane.showMessageDialog(roomsPanel, "Data saved successfully!");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(roomsPanel, "Error saving to database: " + ex.getMessage());
            }




        }
    });
        roomsPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

                String[] veriler = {"Single", "Double"};


                for (String veri : veriler) {
                    comboBox1.addItem(veri);
                }

                String[] verilers = {"Available", "Occupied"};


                for (String veri : verilers) {
                    comboBox2.addItem(veri);
                }
            }
        });
    }

    public static void main(String[] args) {

        rooms h=new rooms();
        h.setContentPane(h.roomsPanel);
        h.setTitle("Rooms Add Page");
        h.setSize(500,500);
        h.setVisible(true);
        h.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
