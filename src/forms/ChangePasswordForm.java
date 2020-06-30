package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;

public class ChangePasswordForm extends JDialog {
    public ChangePasswordForm() {
        setTitle("Change Password");
        setModal(true);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(2);
        setBounds(100, 100, 400, 300);

        JLabel lblOldPass = new JLabel("Old Password");
        lblOldPass.setBounds(30, 30, 180, 25);
        add(lblOldPass);

        JPasswordField tfOldPass = new JPasswordField();
        tfOldPass.setBounds(170, 30, 180, 25);
        add(tfOldPass);

        JLabel lblNewPass = new JLabel("New Password");
        lblNewPass.setBounds(30, 70, 180, 25);
        add(lblNewPass);

        JPasswordField pfNewPass = new JPasswordField();
        pfNewPass.setBounds(170, 70, 180, 25);
        add(pfNewPass);

        JLabel lblNewPassAgn = new JLabel("Confirm New Password");
        lblNewPassAgn.setBounds(30, 110, 180, 25);
        add(lblNewPassAgn);

        JPasswordField pfNewPassAgn = new JPasswordField();
        pfNewPassAgn.setBounds(170, 110, 180, 25);
        add(pfNewPassAgn);

        JLabel warn = new JLabel();
        warn.setBounds(130, 190, 220, 25);
        add(warn);

        JButton btnChange = new JButton("Change");
        btnChange.setForeground(Color.white);
        btnChange.setBackground(new Color(0,124,253));
        btnChange.setBounds(150, 160, 100, 25);
        btnChange.addActionListener(e -> {
            DBConnection db = new DBConnection();
            if (!pfNewPass.getText().equals(pfNewPassAgn.getText())) {
                warn.setText("Passwords do not match");
                warn.setForeground(Color.red);
            } else {
                if (!db.check("Select password from employee where username ='" + LoginForm.userName + "' and password ='" + tfOldPass.getText() + "'")) {
                    warn.setText("Wrong password");
                    warn.setForeground(Color.red);
                } else {
                    if (db.updatePassword(LoginForm.userName, pfNewPassAgn.getText())) {
                        warn.setText("Password was successfully updated");
                        warn.setForeground(Color.green);
                        pfNewPass.setText(null);
                        pfNewPassAgn.setText(null);
                        tfOldPass.setText(null);
                    }
                }
            }
        });
        add(btnChange);


        setVisible(true);
    }
}
