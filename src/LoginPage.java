import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class LoginPage extends JFrame {

    JTextField usernameField;
    JPasswordField passwordField;
    JButton loginButton;
    JLabel status;

    Connection con;

    public LoginPage() {

        setTitle("Gym Login");
        setSize(350, 250);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 30);
        add(userLabel);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 120, 30);
        add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 100, 100, 30);
        add(passLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 120, 30);
        add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(120, 150, 100, 30);
        add(loginButton);

        status = new JLabel("");
        status.setBounds(80, 180, 200, 30);
        add(status);

        connectDatabase();

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
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

        } catch(Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
    }

    void login() {

        try {

            String username = usernameField.getText();
            String password = String.valueOf(passwordField.getPassword());

            PreparedStatement pst = con.prepareStatement(
                "SELECT * FROM admin WHERE username=? AND password=?"
            );

            pst.setString(1, username);
            pst.setString(2, password);

            ResultSet rs = pst.executeQuery();

            if(rs.next()) {

                JOptionPane.showMessageDialog(this, "Login Successful");

                new GymManagementSystem().setVisible(true);

                dispose();

            } else {

                status.setText("Invalid Username or Password");

            }

        } catch(Exception e) {

            JOptionPane.showMessageDialog(this, e);

        }
    }

    public static void main(String[] args) {

        new LoginPage().setVisible(true);

    }
}
