package forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;

public class  MainForm extends JFrame {
    JPanel contentPane;
    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(250, 200, 925, 582);
        setLayout(new GridLayout(1 ,0));
        setTitle("#Chuabaogiorotmon");
//        JScrollPane sp = new CreateTable().table("city");
//        add(sp);


        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu mnProduct = new JMenu("Product");
        mnProduct.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnProduct.setIcon(new ImageIcon("src\\Icon\\Product.png"));
        menuBar.add(mnProduct);

        JMenuItem mntmMedicineCategory = new JMenuItem("Medicine category");
        mntmMedicineCategory.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mntmMedicineCategory.setIcon(new ImageIcon("src\\Icon\\Listicon.png"));
        mnProduct.add(mntmMedicineCategory);

        JMenuItem mntmPriceSetting = new JMenuItem("Price setting");
        mntmPriceSetting.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        mntmPriceSetting.setIcon(new ImageIcon("src\\Icon\\Capsulesicon.png"));
        mnProduct.add(mntmPriceSetting);

        JMenuItem mntmInventory = new JMenuItem("Inventory");
        mntmInventory.setIcon(new ImageIcon("src\\Icon\\Approvalicon.png"));
        mntmInventory.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        mnProduct.add(mntmInventory);

        JMenu mnExchange = new JMenu("Exchange");
        mnExchange.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnExchange.setIcon(new ImageIcon("src\\Icon\\Exchange.png"));
        menuBar.add(mnExchange);

        JMenuItem mntmReceipt = new JMenuItem("Receipt");
        mntmReceipt.setIcon(new ImageIcon("src\\Icon\\Receipt.png"));
        mnExchange.add(mntmReceipt);

        JMenuItem mntmImport = new JMenuItem("Import");
        mntmImport.setIcon(new ImageIcon("src\\Icon\\Import.png"));
        mnExchange.add(mntmImport);

        JMenu mnPartner = new JMenu("Partner");
        mnPartner.setIcon(new ImageIcon("src\\Icon\\Partner.png"));
        mnPartner.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnPartner.setOpaque(true);
        menuBar.add(mnPartner);

        JMenuItem mntmCustomer = new JMenuItem("Customer");
        mntmCustomer.setIcon(new ImageIcon("src\\Icon\\Customer.png"));
        mnPartner.add(mntmCustomer);

        JMenuItem mntmSupplier = new JMenuItem("Supplier");
        mntmSupplier.setIcon(new ImageIcon("src\\Icon\\supplierIcon.png"));
        mnPartner.add(mntmSupplier);

        JMenu mnEmployee = new JMenu("Employee");
        mnEmployee.setIcon(new ImageIcon("src\\Icon\\Employee.png"));
        mnEmployee.setFont(new Font("Segoe UI", Font.BOLD, 12));
        mnEmployee.setOpaque(true);
        menuBar.add(mnEmployee);

        JMenuItem mntmEmployee = new JMenuItem("Employee");
        mntmEmployee.setIcon(new ImageIcon("src\\Icon\\Staff.png"));
        mnEmployee.add(mntmEmployee);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        setVisible(true);
    }
}
