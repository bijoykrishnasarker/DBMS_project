package cafemanagementsystem;

import com.mysql.cj.protocol.Resultset;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener{
    
    JButton signin, signup;
    JTextField cardTextField;
    JPasswordField pinTextField;
    JLabel background;
    
        Login() {
        setTitle("Cafe Management System");
        setLayout(null);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(30, 30, 36));

        ImageIcon bgImage = new ImageIcon(ClassLoader.getSystemResource("icons/CAFE.png"));
        Image bg = bgImage.getImage().getScaledInstance(1200, 800, Image.SCALE_SMOOTH);
        background = new JLabel(new ImageIcon(bg));
        background.setBounds(0, 0, 1200, 800);
        add(background);
        
        int centerX = getWidth() / 2;

        ImageIcon logoIcon = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
        Image logoImg = logoIcon.getImage().getScaledInstance(150, 130, Image.SCALE_SMOOTH);
        JLabel logoLabel = new JLabel(new ImageIcon(logoImg));
        logoLabel.setBounds(centerX - 75, 50, 150, 130);
        background.add(logoLabel);
        
        JLabel welcomeText = new JLabel("Welcome to CAFE");
        welcomeText.setFont(new Font("Osward", Font.BOLD, 45));
        welcomeText.setForeground(new Color(0, 0, 139));
        welcomeText.setBounds(  395, 200, 500, 50);
        background.add(welcomeText);
        
        JLabel employeeIdLabel = new JLabel("Employee ID: ");
        employeeIdLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        employeeIdLabel.setForeground(Color.BLACK);
        employeeIdLabel.setBounds(centerX - 250, 300, 200, 40);
        background.add(employeeIdLabel); 
        
        cardTextField = new JTextField();
        cardTextField.setBounds(centerX - 50, 300, 300, 40);
        cardTextField.setForeground(Color.WHITE);
        cardTextField.setBackground(new Color(46, 46, 46));
        cardTextField.setCaretColor(Color.WHITE);
        background.add(cardTextField); 
        
        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Raleway", Font.BOLD, 30));
        passwordLabel.setForeground(Color.BLACK);
        passwordLabel.setBounds(centerX - 250, 370, 200, 40);
        background.add(passwordLabel); 
        
        pinTextField = new JPasswordField();
        pinTextField.setBounds(centerX - 50, 370, 300, 40);
        pinTextField.setForeground(Color.WHITE);
        pinTextField.setBackground(new Color(46, 46, 46));
        pinTextField.setCaretColor(Color.WHITE);
        background.add(pinTextField);
        
        int buttonWidth = 140;
        int buttonHeight = 40;
        int buttonSpacing = 20;
        int buttonStartX = centerX - (buttonWidth + buttonSpacing / 2);
        
        signup = new JButton("SIGN UP");
        signup.setBounds(buttonStartX, 450, buttonWidth, buttonHeight);
        signup.setBackground(new Color(0, 102, 204));       
        signup.setForeground(Color.BLACK);
        signup.setFont(new Font("Arial", Font.BOLD, 18));
        signup.addActionListener(this);
        background.add(signup);

        signin = new JButton("SIGN IN");
        signin.setBounds(buttonStartX + buttonWidth + buttonSpacing, 450, buttonWidth, buttonHeight);
        signin.setBackground(new Color(34, 153, 84));
        signin.setForeground(Color.GREEN);
        signin.setFont(new Font("Arial", Font.BOLD, 18));
        signin.addActionListener(this);
        background.add(signin);

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    @Override
public void actionPerformed(ActionEvent ae) {
    if(ae.getSource() == signup){
        setVisible(false);
        new SignUpOnePage().setVisible(true);
    }
    else if(ae.getSource() == signin){
        Conn conn = new Conn();
        String employeeId = cardTextField.getText();  // changed variable name
        String password = new String(pinTextField.getPassword());
        String query = "select * from employee_registration where employee_id = '"+employeeId+"' and password = '"+password+"'";
        try{
            ResultSet rs = conn.s.executeQuery(query);

            if(rs.next()){
                setVisible(false);
                new Dashboard(employeeId).setVisible(true);  // pass employeeId now
            }
            else{
                JOptionPane.showMessageDialog(null, "Incorrect Employee ID or Password");
            }
        }catch (Exception e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }
}
    
    public static void main(String args[]){
        new Login();
    }
}