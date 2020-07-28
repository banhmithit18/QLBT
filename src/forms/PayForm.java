package forms;

import utils.setUIFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class PayForm extends JDialog {
    public PayForm(int supplier, float value){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Payment");
        setBounds(300, 150, 400, 200);
        JPanel contentPane = (JPanel)getContentPane();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Amount");
        lblNewLabel.setBounds(92, 47, 57, 22);
        contentPane.add(lblNewLabel);

        JTextField textField = new JTextField();
        textField.setBounds(159, 49, 141, 19);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton btnNewButton = new JButton("Pay");
        btnNewButton.setBounds(121, 100, 99, 21);
        contentPane.add(btnNewButton);
        btnNewButton.addActionListener(e -> {
            double amount = 0;
            try{
                amount = Double.parseDouble(textField.getText());
                if(amount > value)
                {
                    JOptionPane.showMessageDialog(rootPane,"Not available number");
                }
                else{
                    double result = amount - value;

                }
            }catch (Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,"Please enter number only");
            }

        });

        setVisible(true);



    }
}
