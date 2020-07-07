package forms;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.event.*;

public class  MainForm extends JFrame {
    JPanel contentPane;
    public MainForm(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100,1309, 680);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel pnlMenu = new JPanel();
        pnlMenu.setBackground(Color.LIGHT_GRAY);
        pnlMenu.setBounds(0, 0, 174, 643);
        pnlMenu.setBorder(new LineBorder(new Color(0,0,0),1));
        contentPane.add(pnlMenu);
        pnlMenu.setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTitle.setBackground(new Color(52, 235, 58));
        pnlTitle.setBounds(0, 0, 174, 80);
        pnlMenu.add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblTitle = new JLabel("QLBT");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 15));
        lblTitle.setBounds(63, 33, 76, 13);
        pnlTitle.add(lblTitle);

        JPanel pnl_HomeMenu=new JPanel();
        pnl_HomeMenu.setBounds(1, 116, 172, 44);
        pnl_HomeMenu.setLayout(null);
        pnl_HomeMenu.setBackground(Color.LIGHT_GRAY);
        pnlMenu.add(pnl_HomeMenu);

        JButton btnHome = new JButton("Home");
        btnHome.setOpaque(false);
        btnHome.setFocusPainted(false);
        btnHome.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnHome.setContentAreaFilled(false);
        btnHome.setBorderPainted(false);
        btnHome.setBounds(1, 0, 172, 44);
        pnl_HomeMenu.add(btnHome);

        JLabel lblIconHome = new JLabel("");
        lblIconHome.setIcon(new ImageIcon("src\\icon\\iconHome.png"));
        lblIconHome.setBounds(10, 16, 45, 13);
        pnl_HomeMenu.add(lblIconHome);

        btnHome.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlContent.removeAll();
                pnlContent.add(new HomeForm());
                pnlContent.setLayout(null);
                pnlContent.validate();
                pnlContent.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_HomeMenu.setBackground(new Color(78, 82, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_HomeMenu.setBackground(Color.LIGHT_GRAY);
            }
        });

        ///----------Menu_Employee-----------------
        JPanel pnl_EmployeeHome = new JPanel();
        pnl_EmployeeHome.setBounds(1, 160, 172, 44);
        pnl_EmployeeHome.setLayout(null);
        pnl_EmployeeHome.setBackground(Color.LIGHT_GRAY);
        pnlMenu.add(pnl_EmployeeHome);

        JButton btnEmployee = new JButton(" Employee");
        btnEmployee.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnEmployee.setFocusPainted(false);
        btnEmployee.setContentAreaFilled(false);
        btnEmployee.setBorderPainted(false);
        btnEmployee.setBounds(0, 0, 174, 44);
        pnl_EmployeeHome.add(btnEmployee);

        JLabel lblIconEmployee = new JLabel("");
        lblIconEmployee.setIcon(new ImageIcon("src\\icon\\Employee.png"));
        lblIconEmployee.setBounds(10, 16, 45, 13);
        pnl_EmployeeHome.add(lblIconEmployee);

        btnEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlContent.removeAll();
                pnlContent.add(new EmployeeForm());
                pnlContent.setLayout(null);
                pnlContent.validate();
                pnlContent.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_EmployeeHome.setBackground(new Color(78, 82, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_EmployeeHome.setBackground(Color.LIGHT_GRAY);
            }
        });

        ///----------Menu_Product-----------------

        JPanel pnl_ProductMenu = new JPanel();
        pnl_ProductMenu.setBounds(1,205,172, 44);
        pnl_ProductMenu.setLayout(null);
        pnl_ProductMenu.setBackground(Color.LIGHT_GRAY);
        pnlMenu.add(pnl_ProductMenu);

        JButton btnProduct = new JButton("Product");
        btnProduct.setOpaque(false);
        btnProduct.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnProduct.setFocusPainted(false);
        btnProduct.setContentAreaFilled(false);
        btnProduct.setBorderPainted(false);
        btnProduct.setBounds(0, 0, 172, 44);
        pnl_ProductMenu.add(btnProduct);

        JLabel lblIconProduct = new JLabel("");
        lblIconProduct.setIcon(new ImageIcon("src\\icon\\Product.png"));
        lblIconProduct.setBounds(10, 16, 45, 19);
        pnl_ProductMenu.add(lblIconProduct);

        btnProduct.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlContent.removeAll();
                pnlContent.add(new ProductForm());
                pnlContent.setLayout(null);
                pnlContent.validate();
                pnlContent.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_ProductMenu.setBackground(new Color(78, 82, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_ProductMenu.setBackground(Color.LIGHT_GRAY);
            }
        });


        ///----------Menu_Partner-----------------

        JPanel pnl_PartnerMenu=new JPanel();
        pnl_PartnerMenu.setBounds(1, 250, 172, 44);
        pnl_PartnerMenu.setLayout(null);
        pnl_PartnerMenu.setBackground(Color.LIGHT_GRAY);
        pnlMenu.add(pnl_PartnerMenu);

        JButton btnPartner = new JButton(" Partner");
        btnPartner.setOpaque(false);
        btnPartner.setFocusPainted(false);
        btnPartner.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnPartner.setContentAreaFilled(false);
        btnPartner.setBorderPainted(false);
        btnPartner.setBounds(0, 0, 172, 44);
        pnl_PartnerMenu.add(btnPartner);

        JLabel lblIconPartner = new JLabel("");
        lblIconPartner.setIcon(new ImageIcon("src\\icon\\Partner.png"));
        lblIconPartner.setBounds(10, 16, 45, 13);
        pnl_PartnerMenu.add(lblIconPartner);

        btnPartner.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlContent.removeAll();
                pnlContent.add(new PartnerForm());
                pnlContent.setLayout(null);
                pnlContent.validate();
                pnlContent.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_PartnerMenu.setBackground(new Color(78, 82, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_PartnerMenu.setBackground(Color.LIGHT_GRAY);
            }
        });

        ///----------Menu_Exchange-----------------
        JPanel pnl_ExchangeMenu=new JPanel();
        pnl_ExchangeMenu.setBounds(1, 296, 172, 44);
        pnl_ExchangeMenu.setLayout(null);
        pnl_ExchangeMenu.setBackground(Color.LIGHT_GRAY);
        pnlMenu.add(pnl_ExchangeMenu);

        JButton btnExchange = new JButton("Exchange");
        btnExchange.setOpaque(false);
        btnExchange.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExchange.setContentAreaFilled(false);
        btnExchange.setFocusPainted(false);
        btnExchange.setBorderPainted(false);
        btnExchange.setBounds(0, 0, 172, 44);
        pnl_ExchangeMenu.add(btnExchange);

        JLabel lblIconExchange = new JLabel("");
        lblIconExchange.setIcon(new ImageIcon("src\\icon\\Exchange.png"));
        lblIconExchange.setBounds(10, 16, 45, 13);
        pnl_ExchangeMenu.add(lblIconExchange);

        btnExchange.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pnlContent.removeAll();
                pnlContent.add(new ExchangeForm());
                pnlContent.setLayout(null);
                pnlContent.validate();
                pnlContent.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_ExchangeMenu.setBackground(new Color(78, 82, 78));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_ExchangeMenu.setBackground(Color.LIGHT_GRAY);
            }
        });

        //---------------------------------------------
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setBackground(new Color(52, 235, 58));
        panel.setBounds(10, 563, 154, 70);
        pnlMenu.add(panel);
        panel.setLayout(null);

        JButton btnBasket = new JButton("");
        btnBasket.setBounds(0, 0, 154, 70);
        btnBasket.setContentAreaFilled(false);
        btnBasket.setBorderPainted(false);
        panel.add(btnBasket);
        btnBasket.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                SellForm sf=new SellForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                panel.setBackground(new Color(33, 148, 36));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                panel.setBackground(new Color(52, 235, 58));
            }
        });

        JLabel lblBasket = new JLabel("");
        lblBasket.setIcon(new ImageIcon("src\\icon\\Basket.png"));
        lblBasket.setBounds(45, 10, 92, 48);
        panel.add(lblBasket);

        pnlContent = new JPanel();
        pnlContent.add(new HomeForm());
        pnlContent.setBackground(Color.ORANGE);
        pnlContent.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlContent.setBounds(184, 0, 1110, 643);
        contentPane.add(pnlContent);
        pnlContent.setLayout(null);

        setVisible(true);
    }
}
