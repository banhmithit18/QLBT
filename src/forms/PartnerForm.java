package forms;

import utils.ChangePosition;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PartnerForm extends JPanel {
    JPanel pnlContent_2;

    public PartnerForm(){
        setBounds(0,0,1110, 643);
        setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        pnlTitle.setBounds(0,0,1110, 82);
        pnlTitle.setBackground(new Color(52, 235, 58));
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblTitle = new JLabel("Utilities");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblTitle.setBounds(514, 20, 163, 47);
        pnlTitle.add(lblTitle);

        JPanel pnlContent = new JPanel();
        pnlContent.setBorder(new LineBorder(new Color(0, 0, 0), 1));
        pnlContent.setBounds(0, 82, 1110, 561);
        add(pnlContent);
        pnlContent.setLayout(null);

        JPanel pnl_btnCustomer = new JPanel();
        pnl_btnCustomer.setBackground(new Color(230, 108, 108));
        pnl_btnCustomer.setBounds(55, 10, 142, 54);
        pnlContent.add(pnl_btnCustomer);
        pnl_btnCustomer.setLayout(null);

        JButton btnCustomer = new JButton("Create Bar Chart");
        btnCustomer.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnCustomer.setFocusPainted(false);
        btnCustomer.setContentAreaFilled(false);
        btnCustomer.setBorderPainted(false);
        btnCustomer.setBounds(0, 0, 142, 54);
        pnl_btnCustomer.add(btnCustomer);
        btnCustomer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ChartForm cf = new ChartForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnCustomer.setBackground(new Color(179, 70, 62));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnCustomer.setBackground(new Color(230, 108, 108));
            }
        });

        JPanel pnl_btnSupplier = new JPanel();
        pnl_btnSupplier.setBackground(new Color(230, 108, 108));
        pnl_btnSupplier.setBounds(228, 10, 142, 54);
        pnlContent.add(pnl_btnSupplier);
        pnl_btnSupplier.setLayout(null);

        JButton btnSupplier = new JButton("Supplier");
        btnSupplier.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSupplier.setFocusPainted(false);
        btnSupplier.setContentAreaFilled(false);
        btnSupplier.setBorderPainted(false);
        btnSupplier.setBounds(0, 0, 142, 54);
        pnl_btnSupplier.add(btnSupplier);
        btnSupplier.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Addsupplier add = new Addsupplier();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnSupplier.setBackground(new Color(179, 70, 62));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnSupplier.setBackground(new Color(230, 108, 108));
            }
        });

        JPanel pnl_btnDebt = new JPanel();
        pnl_btnDebt.setBackground(new Color(230, 108, 108));
        pnl_btnDebt.setBounds(400, 10, 142, 54);
        pnlContent.add(pnl_btnDebt);
        pnl_btnDebt.setLayout(null);

        JButton btnDebt = new JButton("Debt");
        btnDebt.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnDebt.setFocusPainted(false);
        btnDebt.setContentAreaFilled(false);
        btnDebt.setBorderPainted(false);
        btnDebt.setBounds(0, 0, 142, 54);
        pnl_btnDebt.add(btnDebt);
        btnDebt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DebtForm df = new DebtForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnDebt.setBackground(new Color(179, 70, 62));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnDebt.setBackground(new Color(230, 108, 108));
            }
        });
        JPanel pnl_btnUnit = new JPanel();
        pnl_btnUnit.setBackground(new Color(230, 108, 108));
        pnl_btnUnit.setBounds(575, 10, 142, 54);
        pnlContent.add(pnl_btnUnit);
        pnl_btnUnit.setLayout(null);

        JButton btnUnit = new JButton("Add unit");
        btnUnit.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnUnit.setFocusPainted(false);
        btnUnit.setContentAreaFilled(false);
        btnUnit.setBorderPainted(false);
        btnUnit.setBounds(0, 0, 142, 54);
        pnl_btnUnit.add(btnUnit);
        btnUnit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                UnitForm u = new UnitForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnUnit.setBackground(new Color(179, 70, 62));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnUnit.setBackground(new Color(230, 108, 108));
            }
        });


        pnlContent_2 = new JPanel();
        pnlContent_2.setBounds(2, 74, 1098, 485);
//        pnlContent_2.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlContent.add(pnlContent_2);
        pnlContent_2.setLayout(null);

        setVisible(true);
    }
}
