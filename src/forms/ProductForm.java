package forms;

import utils.ChangePosition;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ProductForm extends JPanel {
    public ProductForm(){
        setBounds(0, 0, 1110, 643);
        setBackground(new Color(192, 192, 192));
//        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(null);

        JPanel contentPane = new JPanel();
        contentPane.setBounds(0,0,1110, 643);
        contentPane.setLayout(null);
        add(contentPane);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(0, 0, 1110, 643);
        contentPane.add(tabbedPane);
        ///----------------------------PNL_MEDICINE CATEGORY-----------------
        JPanel pnlMedicine_ctg = new JPanel();
        pnlMedicine_ctg.setLayout(null);
        tabbedPane.addTab("Medicine category", null, pnlMedicine_ctg, null);

        JPanel pnlTitle_MC = new JPanel();
        pnlTitle_MC.setBounds(0, 0, 1105, 67);
        pnlTitle_MC.setBackground(new Color(52, 235, 58));
        pnlTitle_MC.setBorder(LineBorder.createBlackLineBorder());
        pnlTitle_MC.setLayout(null);
        pnlMedicine_ctg.add(pnlTitle_MC);

        JPanel pnlSearch = new JPanel();
        pnlSearch.setToolTipText("Search");
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(10, 100, 400, 130);
        pnlMedicine_ctg.add(pnlSearch);

        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(10, 40, 200, 25);
        pnlSearch.add(tfSearch);

        String[] boxColumn = {"All", "Name", "Content", "Quantity", "Price", "Supplier"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(230, 40, 120, 25);
        pnlSearch.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(120, 80, 120, 25);
        pnlSearch.add(btnSearch);

        JLabel lblMC = new JLabel("Medicine Category");
        lblMC.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblMC.setBounds(413, 10, 257, 47);
        pnlTitle_MC.add(lblMC);

        JPanel pnlTable = new JPanel();
        pnlTable.setBounds(0, 311, 1110, 242);
        pnlMedicine_ctg.add(pnlTable);
        pnlTable.setLayout(new GridLayout(1, 0));


        ///----------------------------PNL_PRICESETTING-----------------
        JPanel pnlPrice_st = new JPanel();
        pnlPrice_st.setLayout(null);
        tabbedPane.addTab("Price setting", null, pnlPrice_st, null);

        ///----------------------------PNL_INVENTORY-----------------
        JPanel pnlInventory = new JPanel();
        pnlInventory.setLayout(new GridLayout(1,0));
        tabbedPane.addTab("Inventory", null, pnlInventory, null);
        setVisible(true);
    }
}
