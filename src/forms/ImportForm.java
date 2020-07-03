package forms;

import models.entities.Import;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

public class ImportForm extends JDialog {
    public ImportForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Import");
        setUIFont s = new setUIFont();
        s.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(100, 100, 450, 500);
        setLayout(null);

        JLabel lblProduct = new JLabel("Product");
        lblProduct.setBounds(30, 10, 80, 25);
        add(lblProduct);

        DBConnection db = new DBConnection();
        String[] comboboxArr = db.getComboboxString("select productid from product").split(",");
        JComboBox boxProduct = new JComboBox(comboboxArr);
        boxProduct.setBounds(110, 10, 180, 25);
        ComboboxDecorator.decorate(boxProduct, true);
        add(boxProduct);

        JButton btnAddProduct = new JButton("Add Product");
        btnAddProduct.setBounds(300,10,120,25);
        btnAddProduct.addActionListener(e -> {
            AddProductForm apf = new AddProductForm();
        });
        add(btnAddProduct);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setBounds(30, 60, 80, 25);
        add(lblQuantity);

        JTextField tfQuantity = new JTextField();
        tfQuantity.setBounds(110, 60, 180, 25);
        //add rang buoc chi nhap so
        tfQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() >= '0' && e.getKeyChar() <= '9' || (int) e.getKeyChar() == 8) {
                }
                else {
                    e.consume();
                    JOptionPane.showMessageDialog(rootPane, "Please enter number only");

                }
            }
        });
        add(tfQuantity);

        JLabel lblSupplier = new JLabel("Supplier");
        lblSupplier.setBounds(30, 110, 80, 25);
        add(lblSupplier);

        String[] strSupplier = db.getComboboxString("Select suppliername from supplier").split(",");
        JComboBox boxSupplier = new JComboBox(strSupplier);
        boxSupplier.setBounds(110, 110, 180, 25);
        ComboboxDecorator.decorate(boxSupplier, true);
        add(boxSupplier);

        JLabel lblUnit = new JLabel("Unit");
        lblUnit.setBounds(30, 160, 80, 25);
        add(lblUnit);

        String[] strUnit = db.getUnit().split(",");
        JComboBox boxUnit = new JComboBox(strUnit);
        boxUnit.setBounds(110,160,180,25);
        add(boxUnit);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setBounds(30, 210, 80, 25);
        add(lblPrice);

        JTextField tfPrice = new JTextField();
        tfPrice.setBounds(110, 210, 180, 25);
        add(tfPrice);

        JButton btnCreate = new JButton("Create");
        btnCreate.setBounds(120,260,120,25);
        btnCreate.addActionListener(e -> {
            String product = boxProduct.getSelectedItem().toString();
            int supplier = db.getID("Select supplierid from supplier where suppliername ='"+boxSupplier.getSelectedItem().toString()+"'");
            String price = tfPrice.getText();
            String quantity = tfQuantity.getText();

            if(price.equals("")|| quantity.equals("")||supplier == 0)
            {

                JOptionPane.showMessageDialog(rootPane,"Please enter all required information");
            }
            else
            {
                if(price.matches("^([+-]?\\d*\\.?\\d*)$"));
                {
                    try {
                        float p = Float.valueOf(price);
                        Import imp = new Import();
                        imp.setProductid(product);
                        imp.setSupplierid(supplier);
                        imp.setQuantity(Integer.valueOf(quantity));
                        imp.setPrice(p);
                        imp.setDate(TimeStampConvert.getTimeStamp());
                        String [] unitPart = boxUnit.getSelectedItem().toString().split(" ");
                        imp.setUnit(db.getID("Select unitid from unit where unitname ='"+unitPart[1]+"' and unitconvertvalue ="+unitPart[3]+" and unitconvertname ='"+unitPart[4]+"'"));
                        imp.setEmployeeid(1);
                        if(db.Create(imp))
                        {
                            JOptionPane.showMessageDialog(rootPane,"Create successfully");
                            tfPrice.setText(null);
                            tfQuantity.setText(null);
                        }
                     }catch (Exception ex)
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane,"Please enter number only");
                    }
                }

            }

        });
        add(btnCreate);


        setVisible(true);


    }
}
