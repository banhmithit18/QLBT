package forms;

import models.entities.receipt;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class PayForm extends JDialog {
    public PayForm(int debtid, int supplier, float value) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Payment");
        setBounds(300, 150, 400, 200);
        JPanel contentPane = (JPanel) getContentPane();
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
            try {
                amount = Double.parseDouble(textField.getText());
                if (amount > value) {
                    JOptionPane.showMessageDialog(rootPane, "Not available number");
                } else {
                    DBConnection db = new DBConnection();
                    String receiptType = "Thanh toán";
                    int employeeid = 1; // nho sua
                    receipt r = new receipt(receiptType, (float) amount, supplier, employeeid, TimeStampConvert.getTimeStamp());
                    if (db.Create(r)) {

                        double result = value - amount;
                        if(db.updateDebt((float) result,debtid))
                        {
                            int column = 0;
                                    int row = DebtForm.td.row;
                                    for (int i = 0; i < row; i++) {
                                        if ( Integer.parseInt(DebtForm.td.getLabels().get(column).getText()) == (debtid)) {
                                            if(result != 0) {
                                                DebtForm.td.labels.get(2 + column).setText(String.valueOf(result));
                                            }
                                            else{
                                                DebtForm.td.labels.get(2 + column).setText(String.valueOf(result));
                                                DebtForm.td.labels.get(4 + column).setText("Đã thanh toán");

                                            }
                                            textField.setText(null);
                                            JOptionPane.showMessageDialog(rootPane,"Payment successful");
                                            this.dispose();
                                        }
                                        column +=  DebtForm.td.column;
                                    }
                        }

                    }


                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(rootPane, "Please enter number only");
            }

        });

        setVisible(true);


    }
}
