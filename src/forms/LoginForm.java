package forms;

import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

public class LoginForm extends JFrame {
        JPanel defaultpnl;
        JTextField tfUser;
        JPasswordField pfPass;
        JButton btnLog;
        JLabel lblWarn;
        char originalEchoChar;
        public String UserId;
        static String userName;

        public LoginForm() {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setUIFont font = new setUIFont();
            font.Font(new FontUIResource("Arial",Font.PLAIN,12));
            setTitle("Login");
            Image icon = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB_PRE);
            setIconImage(icon);
            defaultpnl = (JPanel) getContentPane();
            defaultpnl.setLayout(null);
            setBounds(150, 150, 300, 300);

            JLabel lblUser = new JLabel("Username");
            lblUser.setBounds(10, 0, 70, 30);
            defaultpnl.add(lblUser);
            tfUser = new JTextField();
            tfUser.setBounds(80, 5, 140, 20);
            defaultpnl.add(tfUser);

            JLabel lblPass = new JLabel("Password");
            lblPass.setBounds(10, 30, 70, 30);
            defaultpnl.add(lblPass);
            pfPass = new JPasswordField();
            pfPass.setBounds(80, 35, 140, 20);
            defaultpnl.add(pfPass);
            JRadioButton chkPass = new JRadioButton();
            chkPass.setBounds(230, 30, 30, 30);
            originalEchoChar = pfPass.getEchoChar();
            chkPass.setToolTipText("Show password");
            chkPass.addActionListener(e -> {
                if (chkPass.isSelected()) {

                    pfPass.setEchoChar((char) 0);
                    chkPass.setToolTipText("Hide password");
                } else {
                    pfPass.setEchoChar(originalEchoChar);
                    chkPass.setToolTipText("Show password");
                }
            });
            defaultpnl.add(chkPass);
            btnLog = new JButton("Login");
            btnLog.setBackground(new Color (0,124,253));
            btnLog.setForeground(Color.white);
            btnLog.setBounds(100, 65, 100, 20);
            btnLog.addActionListener(this::actionLogin);
            defaultpnl.add(btnLog);

            lblWarn = new JLabel();
            lblWarn.setBounds(70, 95, 180, 30);
            defaultpnl.add(lblWarn);
            setVisible(true);
        }

        public void actionLogin(ActionEvent e) {
            String username = tfUser.getText();
            String password = pfPass.getText();
            DBConnection db = new DBConnection();
            if (db.check("Select username from employee where username ='"+username+"'")) {
                if (db.check("Select password from employee where username ='"+username+"' and password ='"+password+"'")) {

                    userName = username;
                    UserId = tfUser.getText();
                    tfUser.setText(null);
                    pfPass.setText(null);
                    pfPass.setEchoChar(originalEchoChar);
                    SellForm2 sellForm2=new SellForm2();
                    setVisible(false);

                } else {
                    lblWarn.setText("Wrong password");
                    lblWarn.setForeground(Color.red);
                }
            } else {
                lblWarn.setText("Could not find username");
                lblWarn.setForeground(Color.red);
            }
        }

    public static void main(String[] args) {
        LoginForm loginForm=new LoginForm();
    }

    }

