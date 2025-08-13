package cafemanagementsystem;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SignUpOnePage extends JFrame implements ActionListener {
    
    JTextField fullNameTextField, employeeIdTextField, emailTextField,
               phoneTextField, addressTextField;
    JPasswordField passwordField;
    JButton next;
    JRadioButton male, female, other;

    public SignUpOnePage() {
        setTitle("Employee Registration");
        setSize(850, 750);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(240, 245, 250));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(0, 51, 102));
        headerPanel.setBounds(0, 0, 850, 100);
        headerPanel.setLayout(null);
        add(headerPanel);

        JLabel formnum = new JLabel("Employee Registration Form", SwingConstants.CENTER);
        formnum.setFont(new Font("Segoe UI", Font.BOLD, 28));
        formnum.setForeground(Color.WHITE);
        formnum.setBounds(0, 20, 850, 40);
        headerPanel.add(formnum);

        JLabel personalDetails = new JLabel("Employee DETAILS", SwingConstants.CENTER);
        personalDetails.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        personalDetails.setForeground(new Color(200, 220, 255));
        personalDetails.setBounds(0, 60, 850, 30);
        headerPanel.add(personalDetails);

        // Content Panel
        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBounds(50, 120, 750, 500);
        contentPanel.setLayout(null);
        contentPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(220, 230, 240), 1),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        add(contentPanel);

        // Full Name
        JLabel fullName = new JLabel("Full Name:");
        fullName.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fullName.setBounds(30, 30, 200, 30);
        contentPanel.add(fullName);

        fullNameTextField = new JTextField();
        fullNameTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fullNameTextField.setBounds(250, 30, 450, 30);
        contentPanel.add(fullNameTextField);

        // Employee ID
        JLabel empId = new JLabel("Employee ID:");
        empId.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        empId.setBounds(30, 80, 200, 30);
        contentPanel.add(empId);

        employeeIdTextField = new JTextField();
        employeeIdTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        employeeIdTextField.setBounds(250, 80, 450, 30);
        contentPanel.add(employeeIdTextField);

        // Email
        JLabel email = new JLabel("Email Address:");
        email.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        email.setBounds(30, 130, 200, 30);
        contentPanel.add(email);

        emailTextField = new JTextField();
        emailTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        emailTextField.setBounds(250, 130, 450, 30);
        contentPanel.add(emailTextField);

        // Password
        JLabel password = new JLabel("Password:");
        password.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        password.setBounds(30, 180, 200, 30);
        contentPanel.add(password);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBounds(250, 180, 450, 30);
        contentPanel.add(passwordField);

        // Phone
        JLabel phone = new JLabel("Phone Number:");
        phone.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phone.setBounds(30, 230, 200, 30);
        contentPanel.add(phone);

        phoneTextField = new JTextField();
        phoneTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        phoneTextField.setBounds(250, 230, 450, 30);
        contentPanel.add(phoneTextField);

        // Gender
        JLabel gender = new JLabel("Gender:");
        gender.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gender.setBounds(30, 280, 200, 30);
        contentPanel.add(gender);

        female = new JRadioButton("Female");
        female.setBounds(250, 280, 80, 30);
        female.setBackground(Color.WHITE);
        contentPanel.add(female);

        male = new JRadioButton("Male");
        male.setBounds(350, 280, 80, 30);
        male.setBackground(Color.WHITE);
        contentPanel.add(male);

        other = new JRadioButton("Other");
        other.setBounds(450, 280, 80, 30);
        other.setBackground(Color.WHITE);
        contentPanel.add(other);

        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(female);
        genderGroup.add(male);
        genderGroup.add(other);

        // Address
        JLabel address = new JLabel("Address:");
        address.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        address.setBounds(30, 330, 200, 30);
        contentPanel.add(address);

        addressTextField = new JTextField();
        addressTextField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        addressTextField.setBounds(250, 330, 450, 30);
        contentPanel.add(addressTextField);

        // Next Button
        next = new JButton("NEXT");
        next.setBackground(new Color(0, 102, 204));
        next.setForeground(Color.WHITE);
        next.setFont(new Font("Segoe UI", Font.BOLD, 14));
        next.setBounds(550, 400, 150, 40);
        next.setFocusPainted(false);
        next.addActionListener(this);
        contentPanel.add(next);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String fullName = fullNameTextField.getText();
        String employeeId = employeeIdTextField.getText();
        String email = emailTextField.getText();
        String password = new String(passwordField.getPassword());
        String phone = phoneTextField.getText();
        String gender = female.isSelected() ? "Female" :
                        male.isSelected() ? "Male" :
                        other.isSelected() ? "Other" : null;
        String address = addressTextField.getText();

        try {
            if (fullName.isEmpty() || employeeId.isEmpty() || email.isEmpty() ||
                password.isEmpty() || gender == null || phone.isEmpty() || address.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all required fields");
                return;
            }

            Conn c = new Conn();
            String query = "INSERT INTO employee_registration " +
                           "(full_name, employee_id, email, password, phone, gender, address) " +
                           "VALUES('" + fullName + "', '" + employeeId + "', '" + email + "', '" +
                           password + "', '" + phone + "', '" + gender + "', '" + address + "')";
            c.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Registration Successful!");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new SignUpOnePage();
    }
}
