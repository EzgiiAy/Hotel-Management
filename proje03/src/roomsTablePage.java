import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class roomsTablePage extends JFrame {

    Connection con;
    PreparedStatement pst;
    private JTable table;
    private JTextField searchField;
    private JButton searchButton;
    private DefaultTableModel tableModel;


    public  roomsTablePage(){
        setTitle("Rooms Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);


        JPanel searchPanel = new JPanel();
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(searchField);
        searchPanel.add(searchButton);


        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Rooms Name");
        tableModel.addColumn("Rooms Size");
        tableModel.addColumn("Rooms Floor");
        tableModel.addColumn("Rooms Price");
        tableModel.addColumn("Bed Type");
        tableModel.addColumn("Availibility");


        table = new JTable(tableModel);


        insertDataIntoTable();



        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        add(searchPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText();
                searchProduct(searchText);

            }
        });

        setVisible(true);
    }


    private void insertDataIntoTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
            String query = "SELECT * FROM rooms";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getInt("id"),
                        resultSet.getString("roomsName"),
                        resultSet.getString("roomsSize"),
                        resultSet.getString("roomsFloor"),
                        resultSet.getString("roomsPrice"),
                        resultSet.getString("bedType"),
                        resultSet.getString("availability"),

                };
                tableModel.addRow(rowData);
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Veritabanına erişim hatası: " + ex.getMessage());
        }
    }





    public void connect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/20190305012", "root","");
            System.out.println("Successful");
        }
        catch (ClassNotFoundException ex)
        {
            ex.printStackTrace();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    private void searchProduct(String searchText) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");
            String query = "SELECT * FROM rooms WHERE roomsName LIKE ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, "%" + searchText + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            tableModel.setRowCount(0);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String roomsName = resultSet.getString("roomsName");
                String roomsSize = resultSet.getString("roomsSize");
                String roomsFloor = resultSet.getString("roomsFloor");
                String roomsPrice = resultSet.getString("roomsPrice");
                String bedType = resultSet.getString("bedType");
                String availability = resultSet.getString("availability");

                Object[] row = { id, roomsName, roomsSize, roomsFloor, roomsPrice, bedType,availability };
                tableModel.addRow(row);
            }

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database access error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new roomsTablePage());






    }
}

