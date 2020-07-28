package forms;

import models.entities.product2;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SellForm extends JFrame {
    int row = 1;
    int column;
    int quantify;
    public static Dimension d;
    public static TableDepot tp;
    public JTextField tftSearch, tftCusName, tfDiscount_Money, tfCusPey_Money;
    public JPanel pnlListOrder;
    public JLabel lblTotalDue_Money;
    DefaultTableModel model;
    JTable table;
    ArrayList<product2> list;

    float total = 0;
    protected JLabel[] labelshead;
    protected ArrayList<JPanel> pnlData = new ArrayList<>();
    protected ArrayList<JButton> btnEdit = new ArrayList<>();
    protected JPanel pnlAllData;
    protected ArrayList<JLabel> labels = new ArrayList<>();

    public ArrayList<JLabel> lable = new ArrayList<>();

    public SellForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(140, 90, 1300, 643);
        getContentPane().setLayout(null);

        JPanel Contentpane = new JPanel();
        Contentpane.setBounds(0, 0, 1291, 643);
        getContentPane().add(Contentpane);
        Contentpane.setLayout(null);

        JPanel pnlHeader = new JPanel();
        pnlHeader.setBackground(new Color(0, 204, 51));
        pnlHeader.setBounds(0, 0, 1291, 52);
        Contentpane.add(pnlHeader);
        pnlHeader.setLayout(null);

        tftSearch = new JTextField();
        tftSearch.setBounds(47, 5, 327, 42);
        pnlHeader.add(tftSearch);
        tftSearch.setColumns(10);

        JLabel lblStaff = new JLabel("Employee:");
        lblStaff.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblStaff.setBounds(992, 19, 78, 13);
        pnlHeader.add(lblStaff);

        JLabel lblEmployeeName = new JLabel("");
        lblEmployeeName.setBounds(1080, 19, 153, 13);
        lblEmployeeName.setText(LoginForm.userName);
        pnlHeader.add(lblEmployeeName);

        JLabel lblIconSearch = new JLabel("");
        lblIconSearch.setIcon(new ImageIcon("src\\icon\\Sell_Search_Bigsize.png"));
        lblIconSearch.setBounds(8, 7, 45, 37);
        pnlHeader.add(lblIconSearch);

        JPanel pnlPey = new JPanel();
        pnlPey.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        pnlPey.setBounds(875, 52, 416, 555);
        Contentpane.add(pnlPey);
        pnlPey.setLayout(null);

        JLabel lblCusName = new JLabel("Customer Name:");
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

        tftCusName = new JTextField();
        tftCusName.setBounds(116, 51, 190, 19);
        tftCusName.setOpaque(false);
        pnlPey.add(tftCusName);
        tftCusName.setColumns(10);

        JLabel lblSetTotal = new JLabel("Total:");
        lblSetTotal.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblSetTotal.setBounds(10, 271, 63, 13);
        pnlPey.add(lblSetTotal);

        JLabel lblTotal_Money = new JLabel("0");
        lblTotal_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotal_Money.setBounds(116, 269, 125, 15);
        pnlPey.add(lblTotal_Money);

        JLabel lblDiscount = new JLabel("Discount:");
        lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDiscount.setBounds(10, 310, 96, 13);
        pnlPey.add(lblDiscount);

        tfDiscount_Money = new JTextField();
        tfDiscount_Money.setEditable(false);
        tfDiscount_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        tfDiscount_Money.setBounds(116, 308, 125, 15);
        pnlPey.add(tfDiscount_Money);

        JRadioButton rdbtnVND = new JRadioButton("vnd");
        rdbtnVND.setBounds(247, 308, 63, 21);
        pnlPey.add(rdbtnVND);

        JRadioButton rdbtnPercent = new JRadioButton("%");
        rdbtnPercent.setBounds(331, 308, 41, 21);
        pnlPey.add(rdbtnPercent);

        JLabel lblTotalDue = new JLabel("Total Due:");
        lblTotalDue.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTotalDue.setBounds(10, 377, 96, 13);
        pnlPey.add(lblTotalDue);

        lblTotalDue_Money = new JLabel("0");
        lblTotalDue_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotalDue_Money.setBounds(116, 379, 125, 15);
        pnlPey.add(lblTotalDue_Money);

        JLabel lblCusPey = new JLabel("CusPey:");
        lblCusPey.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCusPey.setBounds(10, 400, 96, 23);
        pnlPey.add(lblCusPey);

        tfCusPey_Money = new JTextField();
        tfCusPey_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        tfCusPey_Money.setBounds(116, 404, 125, 15);
        pnlPey.add(tfCusPey_Money);

        JLabel lblExcessCash = new JLabel("Excess Cash:");
        lblExcessCash.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblExcessCash.setBounds(10, 433, 96, 23);
        pnlPey.add(lblExcessCash);

        JLabel lblExcessCase_Money = new JLabel("0");
        lblExcessCase_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblExcessCase_Money.setBounds(116, 440, 125, 15);
        pnlPey.add(lblExcessCase_Money);

        JPanel pnlStartPayment = new JPanel();
        pnlStartPayment.setBackground(new Color(0, 204, 51));
        pnlStartPayment.setBounds(10, 485, 396, 60);
        pnlPey.add(pnlStartPayment);
        pnlStartPayment.setLayout(null);

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(null);
        pnlTable.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        pnlTable.setBounds(10, 379, 855, 220);
        Contentpane.add(pnlTable);

        pnlListOrder = new JPanel();
        pnlListOrder.setLayout(new GridBagLayout());
        pnlListOrder.setBorder(new LineBorder(new Color(0, 0, 0)));
        JScrollPane sp = new JScrollPane(pnlListOrder);
        sp.setBounds(10, 62, 855, 306);
        Contentpane.add(sp);

        JPanel pnlcolumn = new JPanel();
        pnlcolumn.setBounds(0, 0, 855, 50);
        pnlListOrder.add(pnlcolumn);

        JPanel pnlorder = new JPanel();
        pnlorder.setBounds(0, 50, 855, 256);
        pnlListOrder.add(pnlorder);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnNewButton.setBounds(0, 0, 396, 60);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setBorderPainted(false);
        pnlStartPayment.add(btnNewButton);
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

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(403, 10, 92, 31);
        pnlHeader.add(btnSearch);
        tftSearch.addKeyListener(new KeyAdapter() {
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 0, 855, 254);
        pnlTable.add(scrollPane);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setDefaultEditor(Object.class, null);
        table.setModel(new DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},
                },
                new String[]{
                        "productid", "productname", "productcontent", "supplier", "code", "price",
                }
        ));
        scrollPane.setViewportView(table);
        getListProduct();


        // ============================================================================
        //=============================================================================

        String[] columnName = {"productname", "productcontent", "quantify", "price"};
        int name = 0;

        pnlcolumn.setLayout(new GridLayout(1, columnName.length));
        JLabel[] labelshead = new JLabel[columnName.length];
        for (JLabel item : labelshead
        ) {
            item = new JLabel();
            item.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));
            item.setHorizontalAlignment(SwingConstants.CENTER);
            item.setVerticalAlignment(SwingConstants.CENTER);
            item.setText(columnName[name]);
            pnlcolumn.add(item);
            item.setPreferredSize(new Dimension(142, 30));
            name++;
        }
        ArrayList<JPanel> pnlRow = new ArrayList<>();
        ArrayList<JLabel> lblRowData = new ArrayList<>();
        pnlorder.setLayout(new GridLayout(1, columnName.length));
        showTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row++;
                pnlorder.setLayout(new GridLayout(row, columnName.length));
                int size = pnlRow.size();
                pnlRow.add(new JPanel());
                int lblSize = lblRowData.size();

                int selectedRow = table.getSelectedRow();
                DefaultTableModel modelUpdate = (DefaultTableModel) table.getModel();

                pnlRow.get(size).setLayout(new GridLayout(1, columnName.length));
                for (int i = 0; i <= 5; i++) {

                    if (i == 4) {
                        quantify = Integer.parseInt(JOptionPane.showInputDialog(null, "quatify"));
                        lblRowData.add(new JLabel());
                        lblRowData.get(lblSize).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                        lblRowData.get(lblSize).setHorizontalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setVerticalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setText(String.valueOf(quantify));
                        pnlRow.get(size).add(lblRowData.get(lblSize));
                        lblSize++;
                    }
                    if (i == 1 || i == 2 ) {
                        lblRowData.add(new JLabel());
                        lblRowData.get(lblSize).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                        lblRowData.get(lblSize).setHorizontalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setVerticalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setText(modelUpdate.getValueAt(selectedRow, i).toString());
                        pnlRow.get(size).add(lblRowData.get(lblSize));
                        lblSize++;

                    }
                    if (i == 5) {


                        String tien = modelUpdate.getValueAt(selectedRow, 5).toString();
                        Float tongtien = Float.parseFloat(tien) * 1000 * quantify;
                        total += tongtien;
//                            System.out.println(total);
                        String lbltotal = String.valueOf(total);
                        lblTotal_Money.setText(lbltotal);
                        lblTotalDue_Money.setText(lbltotal);

                        lblRowData.add(new JLabel());
                        lblRowData.get(lblSize).setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
                        lblRowData.get(lblSize).setHorizontalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setVerticalAlignment(SwingConstants.CENTER);
                        lblRowData.get(lblSize).setText(String.valueOf(Float.parseFloat(tien) * quantify * 1000));
                        pnlRow.get(size).add(lblRowData.get(lblSize));
                        lblSize++;

                        rdbtnVND.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (rdbtnVND.isSelected()) {
                                    rdbtnPercent.setSelected(false);
                                    tfDiscount_Money.setEditable(true);
                                    tfDiscount_Money.addKeyListener(new KeyAdapter() {
                                        @Override
                                        public void keyReleased(KeyEvent e) {
                                            super.keyReleased(e);
                                            if (tfDiscount_Money.getText().equals("")) {
                                                lblTotalDue_Money.setText(String.valueOf(total - 0));
                                            } else {
                                                float discount = Float.parseFloat(tfDiscount_Money.getText());
                                                lblTotalDue_Money.setText(String.valueOf(total - discount));
                                            }
                                        }
                                    });
                                    return;
                                }
                            }
                        });
                        rdbtnPercent.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (rdbtnPercent.isSelected()) {
                                    rdbtnVND.setSelected(false);
                                    tfDiscount_Money.setEditable(true);
                                    tfDiscount_Money.addKeyListener(new KeyAdapter() {
                                        @Override
                                        public void keyReleased(KeyEvent e) {
                                            super.keyReleased(e);
                                            int z = Integer.parseInt(tfDiscount_Money.getText());
                                            if (z > 100) {
                                                JOptionPane.showMessageDialog(null, "please <= 100%");
                                                tfDiscount_Money.setText("");
                                            }
                                            if (tfDiscount_Money.getText().equals("")) {
                                                lblTotalDue_Money.setText(String.valueOf(total));
                                            } else if (tfDiscount_Money.getText().equals("100")) {
                                                lblTotalDue_Money.setText(String.valueOf(total * 0));
                                            } else {
                                                float discount = Float.parseFloat(tfDiscount_Money.getText());
                                                lblTotalDue_Money.setText(String.valueOf(total - (total * discount / 100)));
                                            }
                                        }
                                    });
                                    lblTotal_Money.addKeyListener(new KeyAdapter() {
                                        @Override
                                        public void keyReleased(KeyEvent e) {
                                            super.keyReleased(e);
                                            int z = Integer.parseInt(tfDiscount_Money.getText());
                                            if (z > 100) {
                                                JOptionPane.showMessageDialog(null, "please <= 100%");
                                                tfDiscount_Money.setText("");
                                            }
                                            if (tfDiscount_Money.getText().equals("")) {
                                                lblTotalDue_Money.setText(String.valueOf(total));
                                            } else if (tfDiscount_Money.getText().equals("100")) {
                                                lblTotalDue_Money.setText(String.valueOf(total * 0));
                                            } else {
                                                float discount = Float.parseFloat(tfDiscount_Money.getText());
                                                lblTotalDue_Money.setText(String.valueOf(total - (total * discount / 100)));
                                            }
                                        }
                                    });
                                    return;
                                }
                            }
                        });
                    }
                }
                pnlorder.add(pnlRow.get(size));
                pnlorder.repaint();
                pnlorder.revalidate();
                pnlListOrder.revalidate();
            }
        });

        tftSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (tftSearch.getText().equals("")) {
                    getListProduct();
                    showTable();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                String value = tftSearch.getText();
                getListProductSearch(value);
                showTableSearch(value);
            }
        });

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.HORIZONTAL;
        g.gridx = 0;
        g.gridy = 0;
        pnlListOrder.add(pnlcolumn, g);
        g.gridy = 1;
        pnlListOrder.add(pnlorder, g);
        setVisible(true);
    }

    public void showTableSearch(String value) {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        list = getListProductSearch(value);
        for (product2 p : list) {
            model.addRow(new Object[]{
                    p.getProductid(), p.getProductname(), p.getProductcontent(), p.getSupplier(), p.getCode(), p.getPrice()
            });
        }
    }

    public ArrayList<product2> getListProductSearch(String value) {
        ArrayList<product2> list = new ArrayList<>();
        String sql = "select product.productid as productid, productname, productcontent, supplier.suppliername as supplier, code, depot.price as price  from product inner join supplier on product.supplierid = supplier.supplierid join depot on product.productid = depot.productid where code like '" + value + "%'";

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product2 p = new product2();
                p.setProductid(rs.getString("productid"));
                p.setProductname(rs.getString("productname"));
                p.setProductcontent(rs.getString("productcontent"));
                p.setSupplier(rs.getString("supplier"));
                p.setCode(rs.getString("code"));
                p.setPrice(rs.getFloat("price"));
                list.add(p);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }


    public ArrayList<product2> getListProduct() {
        ArrayList<product2> list = new ArrayList<>();
        String sql = "select product.productid, productname, productcontent, supplier.suppliername as supplier, code, depot.price as price  from product inner join supplier on product.supplierid = supplier.supplierid join depot on product.productid = depot.productid  ";

        try {
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
            Connection conn = DriverManager.getConnection(connectionUrl);
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                product2 p = new product2();
                p.setProductid(rs.getString("productid"));
                p.setProductname(rs.getString("productname"));
                p.setProductcontent(rs.getString("productcontent"));
                p.setSupplier(rs.getString("supplier"));
                p.setCode(rs.getString("code"));
                p.setPrice(rs.getFloat("price"));
                list.add(p);
            }
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public void showTable() {
        model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        list = getListProduct();
        for (product2 p : list) {
            model.addRow(new Object[]{
                    p.getProductid(), p.getProductname(), p.getProductcontent(), p.getSupplier(), p.getCode(), p.getPrice()
            });
        }
        tftSearch.setText("");
    }

}


