package forms;

import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.SliderUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class LoginForm extends JFrame {
        JPanel contentPane;
        JTextField tfUser,tftUsername;
        JPasswordField tpPass;
        JLabel lblWarn;
        char originalEchoChar;

        public String UserId;
        static String userName;

        public LoginForm() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setResizable(false);
            setBounds(0, 0, 484, 557);
            setLocationRelativeTo(null);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JPanel pnlHeader = new JPanel();
            pnlHeader.setBackground(new Color(52, 235, 58));
            pnlHeader.setBounds(0, 0, 470, 147);
            contentPane.add(pnlHeader);
            pnlHeader.setLayout(null);

            JLabel lblLogin = new JLabel("Login");
            lblLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
            lblLogin.setBounds(190, 57, 110, 43);
            pnlHeader.add(lblLogin);

            JLabel lblIcon = new JLabel("");
            lblIcon.setIcon(new ImageIcon("src\\icon\\IconLogin.png"));
            lblIcon.setBounds(194, 170, 90, 90);
            contentPane.add(lblIcon);

            JLabel lblUsername = new JLabel("Username:");
            lblUsername.setFont(new Font("Tahoma", Font.BOLD, 10));
            lblUsername.setBounds(116, 296, 66, 21);
            contentPane.add(lblUsername);

            JLabel lblPass = new JLabel("Password:");
            lblPass.setFont(new Font("Tahoma", Font.BOLD, 10));
            lblPass.setBounds(116, 351, 66, 21);
            contentPane.add(lblPass);

            tftUsername = new JTextField();
            tftUsername.setOpaque(false);
            tftUsername.setBounds(211, 297, 156, 19);
            tftUsername.setBorder(BorderFactory.createMatteBorder(0,0, 1,0,Color.black));
            contentPane.add(tftUsername);
            tftUsername.setColumns(10);

            tpPass = new JPasswordField();
            tpPass.setOpaque(false);
            tpPass.setColumns(10);
            tpPass.setBorder(BorderFactory.createMatteBorder(0,0, 1,0,Color.black));
            tpPass.setBounds(211, 352, 156, 19);
            contentPane.add(tpPass);

            JPanel pnlLogin = new JPanel();
            pnlLogin.setBackground(new Color(52, 235, 58));
            pnlLogin.setBounds(158, 436, 168, 30);
            contentPane.add(pnlLogin);
            pnlLogin.setLayout(null);

            JButton btnLogin = new JButton("Login");
            btnLogin.setContentAreaFilled(false);
            btnLogin.setBorderPainted(false);
            btnLogin.setFocusPainted(false);
            btnLogin.setBounds(0, 0, 168, 30);
            pnlLogin.add(btnLogin);



            lblWarn = new JLabel("");
            lblWarn.setBounds(176, 400, 168, 13);
            contentPane.add(lblWarn);
            btnLogin.addActionListener(this::actionLogin);
            btnLogin.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    super.mouseEntered(e);
                    pnlLogin.setBackground(new Color(27, 114, 28));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    super.mouseExited(e);
                    pnlLogin.setBackground(new Color(52, 235, 58));
                }
            });

            setVisible(true);
        }

        public void actionLogin(ActionEvent e) {
            String username = tftUsername.getText();
            String password = tpPass.getText();
            DBConnection db = new DBConnection();
            if (db.check("Select username from employee where username ='"+username+"'")) {
                if (db.check("Select password from employee where username ='"+username+"' and password ='"+password+"'")) {

                    userName = username;
                    tftUsername.setText(null);
                    tpPass.setText(null);
                    tpPass.setEchoChar(originalEchoChar);
                    MainForm mf=new MainForm();
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

