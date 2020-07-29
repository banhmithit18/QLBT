package forms;

import models.entities.unit;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UnitForm extends JDialog {
    public UnitForm(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Unit");
        setBounds(100, 100, 400, 500);
        JPanel contentPane =  (JPanel) getContentPane();
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Unit Name");
        lblNewLabel.setBounds(47, 74, 70, 22);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Value");
        lblNewLabel_1.setBounds(47, 127, 70, 22);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Unit Convert Name");
        lblNewLabel_2.setBounds(47, 177, 96, 22);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Convert Value");
        lblNewLabel_3.setBounds(47, 227, 79, 22);
        contentPane.add(lblNewLabel_3);

        JTextField textField = new JTextField();
        textField.setBounds(180, 76, 162, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JTextField textField_2 = new JTextField();
        textField_2.setBounds(180, 129, 162, 19);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        JTextField textField_3 = new JTextField();
        textField_3.setBounds(180, 179, 162, 19);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        JTextField textField_4 = new JTextField();
        textField_4.setBounds(180, 229, 162, 19);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        JButton btnNewButton = new JButton("Create");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String unitName = textField.getText();
                    Float value = Float.parseFloat(textField_2.getText());
                    String unitconvertName = textField_3.getText();
                    Float valueconvert = Float.parseFloat(textField_4.getText());

                    if(unitName.equals("")||unitconvertName.equals(""))
                    {
                        JOptionPane.showMessageDialog(rootPane,"Please enter all required information");
                    }
                    else
                    {
                        unit  u = new unit(value,valueconvert,unitName,unitconvertName);

                        DBConnection db = new DBConnection();
                        if (db.Create(u))
                        {
                            JOptionPane.showMessageDialog(rootPane,"Create successfully");
                            textField.setText("");
                            textField_2.setText("");
                            textField_3.setText("");
                            textField_4.setText("");
                        }
                    }
                }catch (Exception ex)
                {
                    JOptionPane.showMessageDialog(rootPane,"Please enter number only");
                }


            }
        });
        btnNewButton.setBounds(121, 304, 115, 40);
        contentPane.add(btnNewButton);
        setVisible(true);
    }
}
