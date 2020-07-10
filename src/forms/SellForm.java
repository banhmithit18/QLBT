package forms;

import utils.ChangePosition;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.jar.JarFile;

public class SellForm extends JFrame {
    int row;
    public static Dimension d;
    public static TableDepot tp;
    private JTextField tftSearch, tftCusName;
    public SellForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        tftSearch = new JTextField();
        tftSearch.setBounds(47, 5, 327, 42);
        pnlHeader.add(tftSearch);
        tftSearch.setColumns(10);

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

        JLabel lblTotal_Money = new JLabel("12300000");
        lblTotal_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotal_Money.setBounds(116, 269, 125, 15);
        pnlPey.add(lblTotal_Money);

        JLabel lblDiscount = new JLabel("Discount:");
        lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblDiscount.setBounds(10, 310, 96, 13);
        pnlPey.add(lblDiscount);

        JLabel lblDiscount_Money = new JLabel("1200000");
        lblDiscount_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblDiscount_Money.setBounds(116, 308, 125, 15);
        pnlPey.add(lblDiscount_Money);

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

        JLabel lblTotalDue_Money = new JLabel("1200000");
        lblTotalDue_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTotalDue_Money.setBounds(116, 379, 125, 15);
        pnlPey.add(lblTotalDue_Money);

        JLabel lblCusPey = new JLabel("CusPey:");
        lblCusPey.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblCusPey.setBounds(10, 400, 96, 23);
        pnlPey.add(lblCusPey);

        JLabel lblCusPey_Money = new JLabel("1200000");
        lblCusPey_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblCusPey_Money.setBounds(116, 404, 125, 15);
        pnlPey.add(lblCusPey_Money);

        JLabel lblExcessCash = new JLabel("Excess Cash:");
        lblExcessCash.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblExcessCash.setBounds(10, 433, 96, 23);
        pnlPey.add(lblExcessCash);

        JLabel lblExcessCase_Money = new JLabel("1200000");
        lblExcessCase_Money.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblExcessCase_Money.setBounds(116, 440, 125, 15);
        pnlPey.add(lblExcessCase_Money);

        JPanel pnlStartPayment = new JPanel();
        pnlStartPayment.setBackground(new Color(0, 204, 51));
        pnlStartPayment.setBounds(10, 485, 396, 60);
        pnlPey.add(pnlStartPayment);
        pnlStartPayment.setLayout(null);

        JPanel pnlTable = new JPanel();
        pnlTable.setLayout(new GridLayout(0,1));
        pnlTable.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlTable.setBounds(10, 379, 855, 220);
        Contentpane.add(pnlTable);
        String query = "select productid, productname, productcontent, supplier.suppliername from product inner join supplier on product.supplierid = supplier.supplierid";
        String[] columnname = {"productid", "productname", "productcontent", "supplier"};

        d = new Dimension(164, 20);
        tp = new TableDepot();
        JScrollPane sp = tp.table("product", columnname, query, d, true);
        pnlTable.add(sp);

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
//        btnSearch.addActionListener(e -> {
//            ChangePosition c = new ChangePosition();
//            int position = 0;
//            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
//            String strSearch = tfSearch.getText(); // lay text trong text field
//            row = tp.row; // lay so hang
//            int column = 0;
//            if (index == 0 ) // tuong duong voi All trong boxSearch
//            {
//                for (JPanel item : tp.pnlData
//                ) {
//                    item.show();
//                }
//            } else {
//                for (int i = 0; i < row; i++) {
//                    if (tp.getLabels().get(index-1 + column).getText().matches(".*" + strSearch + ".*"))
//                    {
//                        tp.pnlData.get(i).show();
//                        for (int secondIndex = 0; secondIndex < tp.pnlAllData.getComponentCount(); secondIndex++) {
//                            if (tp.pnlAllData.getComponent(secondIndex) == (tp.pnlData.get(i))) {
//                                c.swap(tp.pnlAllData, position, secondIndex);
//                                position++;
//                            }
//                        }
//                    } else {
//                        tp.pnlData.get(i).hide();
//                    }
//                    column += tp.column;
//                }
//                //redraw lai panel
//                tp.pnlAllData.revalidate();
//                tp.pnlAllData.repaint();
//            }
//
//        });
        btnSearch.setBounds(403, 10, 92, 31);
        pnlHeader.add(btnSearch);
        tftSearch.addKeyListener(new KeyAdapter() {
        });

        setVisible(true);
    }
}
