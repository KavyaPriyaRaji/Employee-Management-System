package employee.management.system;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JTextField tfusername;
    JPasswordField passwd;
    Login() {
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblusername = new JLabel("USERNAME");
        lblusername.setBounds(40, 40, 100, 30);
        add(lblusername);
        
        tfusername = new JTextField();
        tfusername.setBounds(150, 40, 150, 30);
        add(tfusername);
        
        JLabel lblpassword = new JLabel("PASSWORD");
        lblpassword.setBounds(40, 90, 100, 30);
        add(lblpassword);
        
        passwd = new JPasswordField();
        passwd.setEchoChar('*');
        passwd.setBounds(150, 90, 150, 30);
        add(passwd);
        
        JButton login = new JButton("LOGIN");
        login.setBounds(150, 140, 150, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(225, 225, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 30, 160, 160);
        add(image);
        
        setSize(600, 300);
        setLocation(450,200);
        setVisible(true);
        
        
    }
    
    public void actionPerformed(ActionEvent ae) {
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    try {
        String username = tfusername.getText();
        String password = new String(passwd.getPassword());

        Conn c = new Conn();
        conn = c.c;
        stmt = conn.createStatement();
        String query = "select * from login where username = '"+username+"' and password = '"+password+"'";
        rs = stmt.executeQuery(query);

        if (rs.next()) {
            setVisible(false);
            new Home();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password");
            setVisible(false);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

    public static void main(String[] args) {
        new Login();
    }
}

