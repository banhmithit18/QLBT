package forms;

import models.entities.product2;
import org.w3c.dom.html.HTMLTableCaptionElement;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class SellForm2 extends JFrame {
    public static JTextField tfProductCode, tfCusPey, tftCusPhone;
    JTextField tfProductName, tfExpirationDate, tfDiscount;
    JTextField tfPrice;
    JTextField tfTotal;
    public static JLabel lblTotal_Money, lblEmployeeName, lblExcessCase_Money, lbl_CusName, lbl_CusAge, lbl_CusAddress;


    int inventory_1;
    int Total = 0;
    public static JTable table;
    public static String urlConnection = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";

    public SellForm2() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setBounds(140, 90, 1300, 643);
        getContentPane().setLayout(null);

        JPanel Contentpane = new JPanel();
        Contentpane.setBounds(0, 0, 1291, 643);
        getContentPane().add(Contentpane);
        Contentpane.setLayout(null);

        JPanel pnlHeader = new JPanel();
        pnlHeader.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }
        });
        pnlHeader.setBackground(new Color(0, 204, 51));
        pnlHeader.setBounds(0, 0, 1291, 52);
        Contentpane.add(pnlHeader);
        pnlHeader.setLayout(null);

        JLabel lblStaff = new JLabel("Employee:");
        lblStaff.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblStaff.setBounds(992, 19, 78, 13);
        pnlHeader.add(lblStaff);

        lblEmployeeName = new JLabel("");
        lblEmployeeName.setBounds(1080, 19, 153, 13);
        lblEmployeeName.setText(LoginForm.userName);
        pnlHeader.add(lblEmployeeName);

        JPanel pnlPey = new JPanel();
        pnlPey.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlPey.setBounds(875, 52, 416, 555);
        Contentpane.add(pnlPey);
        pnlPey.setLayout(null);

        JLabel lblCusName = new JLabel("Customer Phone:");
        lblCusName.setBounds(10, 49, 96, 23);
        pnlPey.add(lblCusName);

        JButton btnSearchCus = new JButton("");
        btnSearchCus.setIcon(new ImageIcon("src\\icon\\Sell_Search.png"));
        btnSearchCus.setBounds(331, 49, 30, 23);
        btnSearchCus.setFocusPainted(false);
        pnlPey.add(btnSearchCus);

        JButton btnAddCus = new JButton("");
        btnAddCus.setIcon(new ImageIcon("src\\icon\\Sell_Plus.png"));
        btnAddCus.setBounds(371, 49, 30, 23);
        btnAddCus.setFocusPainted(false);
        pnlPey.add(btnAddCus);

        tftCusPhone = new JTextField();
        tftCusPhone.setBounds(116, 51, 190, 19);
        tftCusPhone.setOpaque(false);
        pnlPey.add(tftCusPhone);
        tftCusPhone.setColumns(10);

        JLabel lbl_Title_CusName = new JLabel("Customer Name:");
        lbl_Title_CusName.setBounds(10, 99, 114, 13);
        pnlPey.add(lbl_Title_CusName);

        JLabel lbl_Title_CusAge = new JLabel("Customer Age:");
        lbl_Title_CusAge.setBounds(10, 137, 96, 13);
        pnlPey.add(lbl_Title_CusAge);

        lbl_CusName = new JLabel("");
        lbl_CusName.setBounds(133, 99, 125, 13);
        pnlPey.add(lbl_CusName);

        lbl_CusAge = new JLabel("");
        lbl_CusAge.setBounds(133, 137, 125, 13);
        pnlPey.add(lbl_CusAge);

        JLabel lbl_Title_CusAddress = new JLabel("Customer Address:");
        lbl_Title_CusAddress.setBounds(10, 171, 114, 13);
        pnlPey.add(lbl_Title_CusAddress);

        lbl_CusAddress = new JLabel("");
        lbl_CusAddress.setBounds(133, 171, 196, 13);
        pnlPey.add(lbl_CusAddress);

        JLabel lblSetTotal = new JLabel("Total:");
        lblSetTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSetTotal.setBounds(10, 271, 63, 13);
        pnlPey.add(lblSetTotal);

        lblTotal_Money = new JLabel("0");
        lblTotal_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotal_Money.setBounds(116, 269, 125, 15);
        pnlPey.add(lblTotal_Money);

        JLabel lblDiscount = new JLabel("Discount:");
        lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDiscount.setBounds(10, 304, 96, 13);
        pnlPey.add(lblDiscount);

        tfDiscount = new JTextField();
        tfDiscount.setBounds(116, 303, 125, 19);
        pnlPey.add(tfDiscount);
        tfDiscount.setColumns(10);

        JRadioButton rdbtnVND = new JRadioButton("vnd");
        rdbtnVND.setBounds(251, 302, 55, 21);
        pnlPey.add(rdbtnVND);

        JRadioButton rdbtnPercent = new JRadioButton("%");
        rdbtnPercent.setBounds(320, 302, 41, 21);
        pnlPey.add(rdbtnPercent);

        JLabel lblTotalDue = new JLabel("Total Due:");
        lblTotalDue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTotalDue.setBounds(10, 337, 96, 13);
        pnlPey.add(lblTotalDue);

        JLabel lblTotalDue_Money = new JLabel("0");
        lblTotalDue_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotalDue_Money.setBounds(116, 338, 125, 15);
        pnlPey.add(lblTotalDue_Money);

        JLabel lblCusPey = new JLabel("CusPey:");
        lblCusPey.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCusPey.setBounds(10, 375, 96, 23);
        pnlPey.add(lblCusPey);

        JLabel lblExcessCash = new JLabel("Excess Cash:");
        lblExcessCash.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblExcessCash.setBounds(10, 433, 96, 23);
        pnlPey.add(lblExcessCash);

        lblExcessCase_Money = new JLabel("0");
        lblExcessCase_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblExcessCase_Money.setBounds(133, 439, 125, 15);
        pnlPey.add(lblExcessCase_Money);

        tfCusPey = new JTextField();
        tfCusPey.setBounds(116, 375, 245, 19);
        pnlPey.add(tfCusPey);
        tfCusPey.setColumns(10);

        JPanel pnlStartPayment = new JPanel();
        pnlStartPayment.setBackground(new Color(0, 204, 51));
        pnlStartPayment.setBounds(10, 485, 396, 60);
        pnlPey.add(pnlStartPayment);
        pnlStartPayment.setLayout(null);

        JButton btnNewButton = new JButton("Pey");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnNewButton.setBounds(0, 0, 396, 60);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        pnlStartPayment.add(btnNewButton);
        btnNewButton.addActionListener(e -> {
            Pay p = new Pay();
        });

        JPanel pnlTable = new JPanel();
        pnlTable.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        pnlTable.setBounds(10, 282, 855, 351);
        Contentpane.add(pnlTable);
        pnlTable.setLayout(null);

        JLabel lblCart = new JLabel("     CART");
        lblCart.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblCart.setBounds(421, 252, 82, 20);
        Contentpane.add(lblCart);

        JLabel lblIconCart = new JLabel("");
        lblIconCart.setIcon(new ImageIcon("D:\\QLBT3\\src\\icon\\supermarket.png"));
        lblIconCart.setBounds(388, 239, 45, 43);
        Contentpane.add(lblIconCart);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 855, 351);
        pnlTable.add(scrollPane);

        table = new JTable();
        table.setEnabled(false);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "code","productname", "quantity", "price", "expirationdate", "total",
                }
        ));
        scrollPane.setViewportView(table);

        JPanel pnlListOrder = new JPanel();
        pnlListOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlListOrder.setBounds(10, 62, 855, 180);
        Contentpane.add(pnlListOrder);
        pnlListOrder.setLayout(null);

        JTextField tfProductCode = new JTextField();
        tfProductCode.setBounds(47, 50, 151, 19);
        pnlListOrder.add(tfProductCode);
        tfProductCode.setColumns(10);

        JLabel lblProductCode = new JLabel("Product Code:");
        lblProductCode.setBounds(83, 27, 90, 13);
        pnlListOrder.add(lblProductCode);

        JLabel lblProductName = new JLabel("Product Name:");
        lblProductName.setBounds(270, 27, 90, 13);
        pnlListOrder.add(lblProductName);

        JTextField tfProductName = new JTextField();
        tfProductName.setColumns(10);
        tfProductName.setEditable(false);
        tfProductName.setBounds(234, 50, 151, 19);
        pnlListOrder.add(tfProductName);

        JLabel lblInventory = new JLabel("Inventory:");
        lblInventory.setBounds(426, 27, 78, 13);
        pnlListOrder.add(lblInventory);

        JLabel lblInventory_Quantify = new JLabel("");
        lblInventory_Quantify.setBounds(436, 50, 57, 19);
        pnlListOrder.add(lblInventory_Quantify);

        JLabel lbQuantity = new JLabel("Quantity:");
        lbQuantity.setBounds(537, 27, 70, 13);
        pnlListOrder.add(lbQuantity);

        JTextField tfQuantity = new JTextField();
        tfQuantity.setBounds(537, 50, 45, 20);
        pnlListOrder.add(tfQuantity);

        JTextField tfPrice = new JTextField();
        tfPrice.setColumns(10);
        tfPrice.setBounds(609, 50, 66, 19);
        pnlListOrder.add(tfPrice);
        tfPrice.setEditable(false);

        JLabel lblPrice = new JLabel("Price:");
        lblPrice.setBounds(624, 27, 45, 13);
        pnlListOrder.add(lblPrice);

        JLabel lblTotal = new JLabel("Total:");
        lblTotal.setBounds(737, 27, 66, 13);
        pnlListOrder.add(lblTotal);

        JTextField tfTotal = new JTextField();
        tfTotal.setColumns(10);
        tfTotal.setEditable(false);
        tfTotal.setBounds(699, 50, 116, 19);
        pnlListOrder.add(tfTotal);

        tfExpirationDate = new JTextField();
        tfExpirationDate.setBounds(609, 110, 125, 19);
        pnlListOrder.add(tfExpirationDate);
        tfExpirationDate.setEditable(false);
        tfExpirationDate.setColumns(10);

        JLabel lblExpirationDate = new JLabel("Expiration Date:");
        lblExpirationDate.setBounds(619, 87, 95, 13);
        pnlListOrder.add(lblExpirationDate);

        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(377, 109, 142, 21);
        pnlListOrder.add(btnAdd);

        btnNewButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnlStartPayment.setBackground(new Color(0, 99, 25));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnlStartPayment.setBackground(new Color(0, 204, 51));
            }
        });
        tfProductCode.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    super.keyReleased(e);
                    String value = tfProductCode.getText();
                    String sql = "select product.productname, quantity, price, expirationdate from product inner join inventory on product.productid = inventory.productid where code like  '" + value + "%'";
                    try {
                        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
                        Connection conn = DriverManager.getConnection(connectionUrl);
                        PreparedStatement ps = conn.prepareStatement(sql);
                        ResultSet rs = ps.executeQuery();
                        if (rs.next() == false) {
                            JOptionPane.showMessageDialog(null, "This product is unavailable");
                            tfProductCode.setText("");
                            tfProductName.setText("");
                            tfProductCode.setText("");
                            tfPrice.setText("");
                            tfTotal.setText("");
                            tfQuantity.setText("");
                            lblInventory_Quantify.setText("");
                            tfExpirationDate.setText("");
                        } else {
                            String name = rs.getString("productname");
                            float price = rs.getFloat("price");
                            String quantity = rs.getString("quantity");
                            inventory_1 = rs.getInt("quantity");
                            tfProductName.setText(name);
                            tfPrice.setText(String.valueOf(price));
                            lblInventory_Quantify.setText(quantity);
                            tfExpirationDate.setText(rs.getString("expirationdate"));
                        }
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });

        //----BTN SEARCH CUS-------
        btnSearchCus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name, address;
                int age;
                try(Connection con = DriverManager.getConnection(urlConnection)){
                    String query = "select * from customer where customerphone = '" + tftCusPhone.getText() + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        lbl_CusName.setText(rs.getString("customername"));
                        lbl_CusAge.setText(String.valueOf(rs.getInt("customerage")));
                        lbl_CusAddress.setText(rs.getString("customeraddress"));
                    }else {
                        JOptionPane.showMessageDialog(null, "This customer does not exist");
                        lbl_CusName.setText("");
                        lbl_CusAge.setText("");
                        lbl_CusAddress.setText("");
                    }
                }catch (Exception a){
                    a.printStackTrace();
                }
            }
        });

        //---EVENT RELEASE QUANTITY---
        tfQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (tfQuantity.getText().equals("")) {
                    int quantity = 0;
                    int inventory = Integer.parseInt(lblInventory_Quantify.getText());
                    lblInventory_Quantify.setText(String.valueOf(inventory_1));
                    tfTotal.setText("");

                } else {
                    int quantity = Integer.parseInt(tfQuantity.getText());
                    int inventory = Integer.parseInt(lblInventory_Quantify.getText());
                    float price = Float.parseFloat(tfPrice.getText());
                    if (quantity > inventory_1) {
                        JOptionPane.showMessageDialog(null, "inventory only 300 products left");
                        lblInventory_Quantify.setText(String.valueOf(inventory_1));
                        tfQuantity.setText("");
                        tfTotal.setText("");
                    } else {
                        lblInventory_Quantify.setText(String.valueOf(inventory_1 - quantity));
                        tfTotal.setText(String.valueOf(quantity * price * 1000));
                    }
                }
            }
        });

        //---BUTTON ADD------
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfProductCode.getText().equals("")){
                    if(!tfQuantity.getText().equals("")){
                        Float Total_sell = Float.parseFloat(tfTotal.getText());
                        DefaultTableModel model = new DefaultTableModel();
                        model = (DefaultTableModel) table.getModel();
                        model.addRow(new Object[]{
                                tfProductCode.getText(),
                                tfProductName.getText(),
                                tfQuantity.getText(),
                                tfPrice.getText(),
                                tfExpirationDate.getText(),
                                tfTotal.getText(),
                        });

                        float tien = 0;
                        for (int i = 0; i <= table.getRowCount() - 1; i++) {
                            float tien1 = Float.parseFloat(table.getValueAt(i, 5).toString());
                            tien += tien1;
                        }
                        lblTotal_Money.setText(String.valueOf(tien));

                        try(Connection con = DriverManager.getConnection(urlConnection)){
                            String query = "update inventory set quantity = quantity - " + tfQuantity.getText() + " from inventory inner join product" +
                                    " on inventory.productid = product.productid " +
                                    "where code = '" + tfProductCode.getText() + "'";
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(query);
                        }catch (Exception a){
                            a.printStackTrace();
                        }
                        tfProductName.setText("");
                        tfProductCode.setText("");
                        tfPrice.setText("");
                        tfTotal.setText("");
                        tfQuantity.setText("");
                        tfExpirationDate.setText("");
                        lblInventory_Quantify.setText("");
                    }else {
                        JOptionPane.showMessageDialog(null, "Please enter the quantity !!");
                    }
                }else {
                    JOptionPane.showMessageDialog(null, "Please enter enough information !!");
                }

            }
        });

        //---CLICK TO SHOW POPUP
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                int r = table.rowAtPoint(e.getPoint());
                if (r >= 0 && r < table.getRowCount()) {
                    table.setRowSelectionInterval(r, r);
                } else {
                    table.clearSelection();
                }

                //row index is found...
                int rowindex = table.getSelectedRow();
                if (rowindex < 0)
                    return;
                if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
                    JPopupMenu popup = createYourPopUp(rowindex, table);
                    popup.show(e.getComponent(), e.getX(), e.getY());
                }
            }

        });
        rdbtnPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent a) {
                rdbtnVND.setSelected(false);
                tfDiscount.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            float total = Float.parseFloat(lblTotal_Money.getText().toString());
                            float discount = Float.parseFloat(tfDiscount.getText().toString());
                            if(discount > 100){
                                JOptionPane.showMessageDialog(null, "Discount must be <= 100% !!");
                            }
                            lblTotalDue_Money.setText(String.valueOf(total - (total * discount / 100)));
                        }
                    }
                });
            }
        });
        rdbtnVND.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rdbtnPercent.setSelected(false);
                tfDiscount.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                            float total = Float.parseFloat(lblTotal_Money.getText().toString());
                            float discount = Float.parseFloat(tfDiscount.getText().toString());
                            if(discount > total){
                                JOptionPane.showMessageDialog(null, "Must be below total product price !!");
                            }else {
                                lblTotalDue_Money.setText(String.valueOf(total - discount));
                            }
                        }
                    }
                });
            }
        });

        tfCusPey.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    float cuspey = Float.parseFloat(tfCusPey.getText().toString());
                    float total = Float.parseFloat(lblTotalDue_Money.getText().toString());
                    lblExcessCase_Money.setText(String.valueOf(cuspey - total));
                }
            }
        });
        //--BUTTON PEY--------
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        setVisible(true);
    }

    public static JPopupMenu createYourPopUp(int rowindex, JTable table) {
        JPopupMenu popup = new JPopupMenu();
        JMenuItem edit = new JMenuItem("Edit Quantity");
        JMenuItem delete = new JMenuItem("Delete");
        edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int soluong = 0;
                int select = table.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                try(Connection con = DriverManager.getConnection(urlConnection)){
                    String query = "select quantity from inventory inner join product\n" +
                            "on inventory.productid = product.productid\n" +
                            "where code = '" + table.getValueAt(select, 0) +"'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        soluong = rs.getInt("quantity");
                    }
                }catch (Exception a){
                    a.printStackTrace();
                }

                //-----EDIT QUANTITY--------------------
                String quantity_new = JOptionPane.showInputDialog(null,"Quantity: " + soluong);
                int Quantity_Curent = Integer.parseInt(table.getValueAt(select, 2).toString());

                if(Integer.parseInt(quantity_new) > soluong){
                    JOptionPane.showMessageDialog(null, "inventory just " + soluong + " product left");
                }else {
                    table.setValueAt(Integer.parseInt(quantity_new),select,2);
                    float price = Float.parseFloat(table.getValueAt(select, 3).toString());
                    table.setValueAt(Integer.parseInt(quantity_new) * price * 1000,select, 5);
                    float money = 0;
                    for(int i =0; i <= table.getRowCount()-1; i++){
                        money += Float.parseFloat(table.getValueAt(i, 5).toString());
                    }
                    lblTotal_Money.setText(String.valueOf(money));
                    if(Integer.parseInt(quantity_new) > Quantity_Curent){
                        int sl = Integer.parseInt(quantity_new) - Quantity_Curent;
                        try(Connection con = DriverManager.getConnection(urlConnection)){
                            String query = "update inventory set quantity = quantity - " + sl + " " +
                                    "from inventory inner join product " +
                                    "on inventory.productid = product.productid " +
                                    "where code = '" + table.getValueAt(select, 0) +"'";
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(query);
                        }catch (Exception a){
                            a.printStackTrace();
                        }
                    }
                    if(Integer.parseInt(quantity_new) < Quantity_Curent){
                        int sl =  Quantity_Curent - Integer.parseInt(quantity_new);
                        try(Connection con = DriverManager.getConnection(urlConnection)){
                            String query = "update inventory set quantity = quantity + " + sl + " " +
                                    "from inventory inner join product " +
                                    "on inventory.productid = product.productid " +
                                    "where code = '" + table.getValueAt(select, 0) +"'";
                            Statement stmt = con.createStatement();
                            stmt.executeUpdate(query);
                        }catch (Exception a){
                            a.printStackTrace();
                        }
                    }
                }
            }
        });
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                int select = table.getSelectedRow();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                try(Connection con = DriverManager.getConnection(urlConnection)){
                    String query = "update inventory set quantity = quantity + " + table.getValueAt(select, 2) + " from inventory inner join product" +
                            " on inventory.productid = product.productid " +
                            "where code = '" + table.getValueAt(select, 0) + "'";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    model.removeRow(select);
                    float money = 0;
                    for(int i =0; i <= table.getRowCount()-1; i++){
                        money += Float.parseFloat(table.getValueAt(i, 5).toString());
                    }
                    JOptionPane.showMessageDialog(null, "Delete scucessfuly");
                    lblTotal_Money.setText(String.valueOf(money));
                }catch (Exception a){
                    a.printStackTrace();
                }

                // sum total

            }
        });
        popup.add(edit);
        popup.add(delete);
        return popup;
    }

    public static void main(String[] args) {
        SellForm2 sellForm2=new SellForm2();
    }
}
