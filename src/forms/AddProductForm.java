package forms;

import models.entities.product;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class AddProductForm extends JDialog {
    public AddProductForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setTitle("Add Product");
        setResizable(false);
        setLayout(null);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(100, 100, 400, 450);
        JLabel lblProductId = new JLabel("Product ID");
        lblProductId.setBounds(30, 10, 80, 25);
        add(lblProductId);

        JTextField tfProductId = new JTextField();
        tfProductId.setBounds(120, 10, 220, 25);
        add(tfProductId);

        JLabel lblProductName = new JLabel("Product Name");
        lblProductName.setBounds(30, 50, 80, 25);
        add(lblProductName);

        JTextField tfProductName = new JTextField();
        tfProductName.setBounds(120, 50, 220, 25);
        add(tfProductName);

        JLabel lblProductContent = new JLabel("Product Content");
        lblProductContent.setBounds(30, 90, 160, 25);
        add(lblProductContent);

        JTextArea tfProductContent = new JTextArea();
        tfProductContent.setBorder(BorderFactory.createLineBorder(Color.black));
        tfProductContent.setBounds(120, 90, 220, 100);
        add(tfProductContent);

        JLabel lblSupplier = new JLabel("Supplier");
        lblSupplier.setBounds(30, 200, 80, 25);
        add(lblSupplier);

        DBConnection db = new DBConnection();
        String[] strSupplier = db.getComboboxString("Select suppliername from supplier").split(",");
        JComboBox boxSupplier = new JComboBox(strSupplier);
        ComboboxDecorator.decorate(boxSupplier, true);
        boxSupplier.setBounds(120, 200, 220, 25);
        add(boxSupplier);

        JButton btnAdd = new JButton("Add Product");
        btnAdd.setBounds(120, 260, 100, 25);
        btnAdd.addActionListener(e -> {
            String productId = tfProductId.getText();
            String productName = tfProductName.getText();
            String productContent = tfProductContent.getText();
            int supplier = db.getID("Select supplierid from supplier where suppliername ='" + boxSupplier.getSelectedItem().toString() + "'");
            if (productId.equals(null) || productName.equals(null) || productContent.equals(null) || supplier == 0) {
                JOptionPane.showMessageDialog(rootPane, "Please enter all required information");
            } else {
                if (!db.check("select productid from product where productid =N'" + productId + "'")) {
                    product p = new product();
                    p.setProductid(productId);
                    p.setProductname(productName);
                    p.setProductcontent(productContent);
                    p.setSupplierid(supplier);
                    if (db.Create(p)) {
                        JOptionPane.showMessageDialog(rootPane, "Add product successfully");
                        tfProductId.setText(null);
                        tfProductName.setText(null);
                        tfProductContent.setText(null);
                        String[] comboboxArr = db.getComboboxString("select productid from product").split(",");
                        ImportForm.boxProduct.setModel(new DefaultComboBoxModel(comboboxArr));
                    }
                } else {
                JOptionPane.showMessageDialog(rootPane,"Product already exists");
                }
            }


        });
        add(btnAdd);
        setVisible(true);
    }
}
