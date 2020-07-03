package forms;

import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ImportForm extends JDialog {
    public ImportForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Import");
        setUIFont s = new setUIFont();
        s.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(50, 50, 700, 400);
        setLayout(null);

        JLabel lblProduct = new JLabel("Product");
        lblProduct.setBounds(400, 10, 80, 25);
        add(lblProduct);

        DBConnection db = new DBConnection();
        String[] comboboxArr = db.getComboboxString("select productid from product").split(",");
        JComboBox boxProduct = new JComboBox(comboboxArr);
        boxProduct.setBounds(480, 10, 180, 25);
        ComboboxDecorator.decorate(boxProduct, true);
        add(boxProduct);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(400, 60, 80, 25);
        add(lblQuantity);

        JTextField tfQuantity = new JTextField();
        tfQuantity.setBounds(480, 60, 180, 25);
        //add rang buoc chi nhap so
        tfQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() < '0' && e.getKeyChar() > '9' || (int) e.getKeyChar() != 8) {
                    e.consume();
                    JOptionPane.showMessageDialog(rootPane, "Please enter number only");
                }
            }
        });
        add(tfQuantity);

        JLabel lblSupplier = new JLabel("Supplier");
        lblSupplier.setBounds(400, 110, 80, 25);
        add(lblSupplier);

        String[] strSupplier = db.getComboboxString("Select suppliername from supplier").split(",");
        JComboBox boxSupplier = new JComboBox(strSupplier);
        boxSupplier.setBounds(480, 110, 180, 25);
        ComboboxDecorator.decorate(boxSupplier, true);
        add(boxSupplier);

        JLabel lblUnit = new JLabel("Unit");
        lblUnit.setBounds(400, 160, 80, 25);
        add(lblUnit);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(400, 210, 80, 25);
        add(lblPrice);

        JTextField tfPrice = new JTextField();
        tfPrice.setBounds(480, 210, 180, 25);
        tfPrice.addKeyListener(new KeyAdapter() {
            boolean dot = false;

            public void keyTyped(KeyEvent e) {
                char vChar = e.getKeyChar();
                if (tfPrice.getText().equals(""))
                    dot = false;
                if (dot == false) {
                    if (vChar == '.') dot = true;
                    else if (!(Character.isDigit(vChar)
                            || (vChar == KeyEvent.VK_BACK_SPACE)
                            || (vChar == KeyEvent.VK_DELETE))) {
                        JOptionPane.showMessageDialog(rootPane, "Please enter number only");
                        e.consume();
                    }

                }
            }
        });
        add(tfPrice);

        setVisible(true);


    }
}
