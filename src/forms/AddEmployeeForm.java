package forms;

import models.entities.employee;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class AddEmployeeForm extends JDialog {
    JTextField tfUsername,tfName,tfPhone,tfEmail,tfAddress;
    JPasswordField pfPassword;
    public AddEmployeeForm (){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setUIFont.Font(new FontUIResource("Arial",Font.PLAIN,12));
        setResizable(false);// hide icon java at the corner
        setTitle("Register");
        setBounds(100,100,500,400);
        setLayout(null);
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBounds(30,30,120,25);
        add(lblUsername);
        tfUsername = new JTextField();
        tfUsername.setBounds(120,30,200,25);
        add(tfUsername);
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setBounds(30,70,120,25);
        add(lblPassword);
        pfPassword = new JPasswordField();
        pfPassword.setBounds(120,70,200,25);
        add(pfPassword);
        JRadioButton chkPass = new JRadioButton();
        chkPass.setBounds(330, 67, 30, 30);
        char originalEchoChar = pfPassword.getEchoChar();
        chkPass.setToolTipText("Show password");
        chkPass.addActionListener(e -> {
            if (chkPass.isSelected()) {

                pfPassword.setEchoChar((char) 0);
                chkPass.setToolTipText("Hide password");
            } else {
                pfPassword.setEchoChar(originalEchoChar);
                chkPass.setToolTipText("Show password");
            }
        });
        add(chkPass);
        JLabel lblName = new JLabel("Full Name");
        lblName.setBounds(30,110,120,25);
        add(lblName);
        tfName = new JTextField();
        tfName.setBounds(120,110,200,25);
        add(tfName);
        JLabel lblPhone = new JLabel("Phone Number");
        lblPhone.setBounds(30,150,120,25);
        add(lblPhone);
        tfPhone = new JTextField();
        tfPhone.setBounds(120,150,200,25);
        add(tfPhone);
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(30,190,120,25);
        add(lblEmail);
        tfEmail = new JTextField();
        tfEmail.setBounds(120,190,200,25);
        add(tfEmail);
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setBounds(30,230,120,25);
        add(lblAddress);
        tfAddress = new JTextField();
        tfAddress.setBounds(120,230,200,25);
        add(tfAddress);
        JLabel lblStoreid = new JLabel("Store");
        lblStoreid.setBounds(30,270,120,25);
        add(lblStoreid);
        DBConnection db = new DBConnection();
        String[] strStore = db.getComboboxString("Select storeid from store").split(",");
        JComboBox comboBox = new JComboBox(strStore);
        ComboboxToolTipRender render = new ComboboxToolTipRender();
        comboBox.setRenderer(render);
        String [] storeToolTipArr = db.getStoreAddress().split(",");
        List<String> storeTooltip = Arrays.asList(storeToolTipArr);
        render.setTooltips(storeTooltip);
        ComboboxDecorator.decorate(comboBox,true);
        comboBox.setBounds(120, 270, 200, 25);
        add(comboBox);
        JButton btnAdd = new JButton("Register");
        btnAdd.setForeground(Color.white);
        btnAdd.setBounds(150,315,100,25);
        btnAdd.setOpaque(true);
        btnAdd.setBackground(new Color(0,124,253));
        btnAdd.addActionListener(e -> {
            String userName = tfUsername.getText();
            String fullName = tfName.getText();
            String phone = tfPhone.getText();
            String email = tfEmail.getText();
            String address = tfAddress.getText();
            String password =pfPassword.getText();
            int store = Integer.parseInt(comboBox.getSelectedItem().toString());
            if(userName.isEmpty()||fullName.isEmpty()||phone.isEmpty()||email.isEmpty()||address.isEmpty()||password.isEmpty())
            {
                JOptionPane.showMessageDialog(rootPane,"Please enter all required information");
            }
            else
            {
                if(!db.check("Select username from employee where username ='"+userName+"'"))
                {
                    employee emp = new employee(fullName,phone,email,address,userName,password,store);
                    if(db.Create(emp))
                    {
                        JOptionPane.showMessageDialog(rootPane,"Successfully registered");
                        tfUsername.setText(null);
                        tfName.setText(null);
                        tfPhone.setText(null);
                        tfEmail.setText(null);
                        tfAddress.setText(null);
                        pfPassword.setText(null);
                        pfPassword.setEchoChar(originalEchoChar);
                    };
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Username is already exists");
                }
            }
        });
        add(btnAdd);
        setVisible(true);
    }
}
