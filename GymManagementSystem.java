
import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class GymManagementSystem extends JFrame {
    JTextField nameField, phoneField;
    JButton addButton, viewButton;
    JTable memberTable;
    DefaultTableModel tableModel;
    JTextArea output;

    Connection con;

   public GymManagementSystem() {

    setTitle("Gym Management Dashboard");
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(null);
    panel.setBackground(new Color(30, 30, 30));

    JLabel title = new JLabel("GYM MANAGEMENT SYSTEM");
    title.setBounds(220, 20, 400, 40);
    title.setFont(new Font("Arial", Font.BOLD, 28));
    title.setForeground(Color.WHITE);
    panel.add(title);

    JLabel nameLabel = new JLabel("Member Name:");
    nameLabel.setBounds(50, 100, 120, 30);
    nameLabel.setForeground(Color.WHITE);
    panel.add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(180, 100, 200, 30);
    panel.add(nameField);

    JLabel phoneLabel = new JLabel("Phone:");
    phoneLabel.setBounds(50, 150, 120, 30);
    phoneLabel.setForeground(Color.WHITE);
    panel.add(phoneLabel);

    phoneField = new JTextField();
    phoneField.setBounds(180, 150, 200, 30);
    panel.add(phoneField);

    addButton = new JButton("Add Member");
    addButton.setBounds(450, 100, 150, 40);
    addButton.setBackground(new Color(0, 153, 76));
    addButton.setForeground(Color.WHITE);
    panel.add(addButton);

    viewButton = new JButton("View Members");
    viewButton.setBounds(450, 160, 150, 40);
    viewButton.setBackground(new Color(0, 102, 204));
    viewButton.setForeground(Color.WHITE);
    panel.add(viewButton);

    String[] columns = {"ID", "Name", "Phone"};

tableModel = new DefaultTableModel(columns, 0);

memberTable = new JTable(tableModel);

JScrollPane scrollPane = new JScrollPane(memberTable);

scrollPane.setBounds(50, 250, 680, 250);

panel.add(scrollPane);
    add(panel);

    connectDatabase();

    addButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            addMember();
        }
    });

    viewButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            viewMembers();
        }
    });
}

      
    

    void connectDatabase() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/gymdb",
                "root",
                "Yashrwt@2006"
            );

        } catch(Exception e){    
            JOptionPane.showMessageDialog(this, e);
        }
    }

    void addMember() {
        try {
            String name = nameField.getText();
            String phone = phoneField.getText();

            PreparedStatement pst = con.prepareStatement(
                "INSERT INTO members(name, phone) VALUES(?, ?)"
            );

            pst.setString(1, name);
            pst.setString(2, phone);

            pst.executeUpdate();

            JOptionPane.showMessageDialog(this, "Member Added Successfully!");
            nameField.setText("");
            phoneField.setText("");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

  void viewMembers() {

    try {

        tableModel.setRowCount(0);

        Statement st = con.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM members");

        while(rs.next()) {

            int id = rs.getInt("id");
            String name = rs.getString("name");
            String phone = rs.getString("phone");

            tableModel.addRow(new Object[]{id, name, phone});
        }

    } catch(Exception e) {

        JOptionPane.showMessageDialog(this, e);

    }
}

    public static void main(String[] args) {
        new GymManagementSystem().setVisible(true);
    }
}
