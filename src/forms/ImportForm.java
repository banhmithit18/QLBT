package forms;

import models.entities.Import;
import models.entities.depot;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ImportForm extends JDialog {
    protected int quantity;
    protected String price;
    protected String productName;
    protected static JComboBox boxProduct;
    public ImportForm() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Import");
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(100, 100, 450, 500);
        setLayout(null);

        JLabel lblProduct = new JLabel("Product");
        lblProduct.setBounds(30, 10, 80, 25);
        add(lblProduct);

        DBConnection db = new DBConnection();
        String[] comboboxArr = db.getComboboxString("select productid from product").split(",");
        boxProduct = new JComboBox(comboboxArr);
        ComboboxToolTipRender productRender = new ComboboxToolTipRender();
        boxProduct.setRenderer(productRender);
        String []  productToolTipArr = db.getProductInformation("product").split("\\.");
        List<String> listProduct = Arrays.asList(productToolTipArr);
        productRender.setTooltips(listProduct);
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
        ComboboxToolTipRender render = new ComboboxToolTipRender();
        String [] arrSupplier = db.getSupplierInformation().split(",");
        List<String> listSupplier = Arrays.asList(arrSupplier);
        render.setTooltips(listSupplier);
        boxSupplier.setRenderer(render);
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

        JLabel lblExpDate = new JLabel("Expiration Date");
        lblExpDate.setBounds(30,260,80,25);
        add(lblExpDate);

        JTextField tfExpDate = new JTextField("YYYY-MM-DD");
        tfExpDate.setToolTipText("YYYY-MM-DD");
        tfExpDate.setBounds(110,260,180,25);
        add(tfExpDate);

        JButton btnCreate = new JButton("Create");
        btnCreate.setBounds(120,310,120,25);
        btnCreate.addActionListener(e -> {
            if(!tfExpDate.getText().matches("^\\d{4}-\\d{2}-\\d{2}$"))
            {
                JOptionPane.showMessageDialog(rootPane,"Wrong date format");

            }
            else {
                String Strexpirationdate = tfExpDate.getText();
                String product = boxProduct.getSelectedItem().toString();
            int supplier = db.getID("Select supplierid from supplier where suppliername ='"+boxSupplier.getSelectedItem().toString()+"'");
            price = tfPrice.getText();
            String quantityStr = tfQuantity.getText();

            if(price.equals("")|| quantityStr.equals(""))
            {

                JOptionPane.showMessageDialog(rootPane,"Please enter all required information");
            }
            else
            {
                if(price.matches("^([+-]?\\d*\\.?\\d*)$"));
                {
                    try {
                        quantity = Integer.parseInt(quantityStr);
                        int check = 0;
                        float p = Float.valueOf(price);
                        Import imp = new Import();
                        if(db.check("select productid from product where productid ='"+product+"'")) {
                            imp.setProductid(product);
                            check++;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(rootPane,"Could not find product");
                        }
                        if (db.check("select supplierid from supplier where supplierid = "+supplier)) {
                            imp.setSupplierid(supplier);
                            check++;
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(rootPane,"Could not find supplier");
                        }
                        if(check == 2) {
                            imp.setQuantity(quantity);
                            imp.setPrice(p);
                            imp.setDate(TimeStampConvert.getTimeStamp());
                            String[] unitPart = boxUnit.getSelectedItem().toString().split(" ");
                            imp.setUnit(db.getID("Select unitid from unit where unitname =N'" + unitPart[1] + "' and unitconvertvalue =" + unitPart[3] + " and unitconvertname =N'" + unitPart[4] + "'"));
                            imp.setEmployeeid(1);// nho sua
                            imp.setExpirationdate(Strexpirationdate);
                            if (db.Create(imp)) {
                                int realQuantity = quantity * Integer.parseInt(unitPart[3]);
                                ArrayList<Object> ob = db.getAllData("select top 1 importid,date  from import where productid  ='"+product+"' order by importid desc");
                                Timestamp importdate = (Timestamp) ob.get(1);
                                String importid = ob.get(0).toString();
                                depot d = new depot(importid,product,realQuantity,p,Strexpirationdate,importdate);
                                db.Create(d);
//                                checkCrt = true;
//                                boolean checkAdd = true;
                                JOptionPane.showMessageDialog(rootPane, "Create successfully");
                                tfPrice.setText(null);
                                tfQuantity.setText(null);
                                productName = db.getName("Select productname from product where productid ='" + product + "'");
                                ///add su kien sua
//                                if (checkCrt) {
//                                    int column = 0;
//                                    int row = DepotForm.tp.row;
//                                    for (int i = 0; i < row; i++) {
//                                        if (DepotForm.tp.getLabels().get(column).getText().equals(importid)) {
//                                            int newQuantity = Integer.parseInt(DepotForm.tp.labels.get(2 + column).getText()) + quantity * Integer.parseInt(unitPart[3]);
//                                            DepotForm.tp.labels.get(4 + column).setText(price);
//                                            DepotForm.tp.labels.get(3 + column).setText(String.valueOf(newQuantity));
//                                            checkAdd = false;
//                                        }
//                                        column += DepotForm.tp.column;
//                                    }
//                                }
                                //add su kien them bang
                                if ( db.check("Select productid from depot where productid=N'" + product + "'")) {
                                    //lay du lieu vua moi add
                                    ArrayList<Object> obData = db.getAllData("select importid,productname , productcontent, depot.quantity, depot.price, suppliername,importdate,expirationdate\n" +
                                                    "from depot join product on  depot.productid = product.productid\n" +
                                                    "join supplier on product.supplierid = supplier.supplierid where importid ='"+importid+"'");
                                    //set layout lai cho panel body
                                    DepotForm.tp.pnlAllData.setLayout(new GridLayout(db.getRowCount("depot"),0));
                                    int column = DepotForm.tp.column;
                                    int i = DepotForm.tp.pnlData.size();
                                    int z = DepotForm.tp.labels.size();
                                    //khoi tao panel data
                                    DepotForm.tp.pnlData.add(new JPanel());
                                    DepotForm.tp.pnlData.get(i).setLayout(new GridLayout(0, column + 1));
                                    for (int y = 0; y < column; y++) {
                                        DepotForm.tp.labels.add(new JLabel());
                                        DepotForm.tp.labels.get(z).setVerticalAlignment(SwingConstants.CENTER);
                                        //                labels[z].setHorizontalAlignment(SwingConstants.CENTER);
                                        DepotForm.tp.labels.get(z).setText(obData.get(y).toString());
                                        DepotForm.tp.labels.get(z).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
                                        DepotForm.tp.labels.get(z).setPreferredSize(DepotForm.d);
                                        DepotForm.tp.pnlData.get(i).add(DepotForm.tp.labels.get(z));
                                        z++;
                                    }
                                    // add panel chua data vao panel body
                                    DepotForm.tp.pnlAllData.add(DepotForm.tp.pnlData.get(i));
                                    //add edit row
                                    DepotForm.tp.btnEdit.add(new JButton());
                                    DepotForm.tp.btnEdit.get(i).setText("<HTML><FONT color=\"#006ce5\"><U>Edit</U></FONT>"
                                            + " </HTML>");
                                    DepotForm.tp.btnEdit.get(i).setHorizontalAlignment(SwingConstants.CENTER);
                                    DepotForm.tp.btnEdit.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
                                    DepotForm.tp.btnEdit.get(i).setOpaque(false);
                                    DepotForm.tp.btnEdit.get(i).setBackground(Color.WHITE);
                                    DepotForm.tp.btnEdit.get(i).setPreferredSize(DepotForm.d);
//                                  DepotForm.tp.btnEdit.get(i).addActionListener(this::ActionEvent);
                                    DepotForm.tp.pnlData.get(i).add(DepotForm.tp.btnEdit.get(i));
                                }
                                DepotForm.tp.pnlAllData.repaint();
                                DepotForm.tp.pnlAllData.revalidate();

                            }
                        }
                     }catch (Exception ex)
                    {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(rootPane,"Please enter number only");
                    }
                }
            }
        }});
        add(btnCreate);
        setVisible(true);
    }
}
