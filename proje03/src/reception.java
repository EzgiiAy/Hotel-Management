import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class reception extends JFrame {

    private JTextField nameField, surnameField, numberField, roomsField, dateField;
    private JButton addButton, refreshButton;
    private JTable table;
    private JPanel receptionPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JComboBox comboBox1;
    private JSpinner spinner1;
    private JButton receptionButton;
    private JTable table1;


    public reception() {
        setTitle("Reception App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField();
        JLabel surnameLabel = new JLabel("Surname:");
        surnameField = new JTextField();
        JLabel numberLabel = new JLabel("Number:");
        numberField = new JTextField();
        JLabel roomsLabel = new JLabel("Room:");
        roomsField = new JTextField();
        JLabel dateLabel = new JLabel("Date:");
        dateField = new JTextField();

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(surnameLabel);
        formPanel.add(surnameField);
        formPanel.add(numberLabel);
        formPanel.add(numberField);
        formPanel.add(roomsLabel);
        formPanel.add(roomsField);
        formPanel.add(dateLabel);
        formPanel.add(dateField);

        addButton = new JButton("Add Entry");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                addEntryToDatabase();
            }
        });

        refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                refreshTable();
            }
        });

        formPanel.add(addButton);
        formPanel.add(refreshButton);

        panel.add(formPanel, BorderLayout.NORTH);

        // Table Panel
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane, BorderLayout.CENTER);

        add(panel);
        setVisible(true);

        refreshTable();
    }

    private void addEntryToDatabase() {
        String name = nameField.getText();
        String surname = surnameField.getText();
        String number = numberField.getText();
        String room = roomsField.getText();
        String date = dateField.getText();

        try {

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");

            String query = "INSERT INTO reservations (Name, Surname, Number, Room, Date) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, surname);
            statement.setString(3, number);
            statement.setString(4, room);
            statement.setString(5, date);
            statement.executeUpdate();

            statement.close();
            connection.close();

            clearForm();


            refreshTable();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void refreshTable() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/20190305012", "root", "");

            String query = "SELECT * FROM reservations";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Name");
            tableModel.addColumn("Surname");
            tableModel.addColumn("Number");
            tableModel.addColumn("Room");
            tableModel.addColumn("Date");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String number = resultSet.getString("number");
                String rooms = resultSet.getString("room");
                String date = resultSet.getString("date");

                tableModel.addRow(new String[]{id, name, surname, number, rooms, date});
            }


            table.setModel(tableModel);

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearForm() {
        nameField.setText("");
        surnameField.setText("");
        numberField.setText("");
        roomsField.setText("");
        dateField.setText("");
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new reception();
            }
        });

    }
}
