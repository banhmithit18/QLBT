package forms;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ExchangeForm extends JPanel {
    public ExchangeForm(){
        setBounds(0,0,1110, 643);
        setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pnlTitle.setBounds(0,0,1110, 82);
        pnlTitle.setBackground(new Color(52, 235, 58));
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblTitle = new JLabel("Exchange");
        lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblTitle.setBounds(506, 20, 171, 47);
        pnlTitle.add(lblTitle);

        JPanel pnlContent = new JPanel();
        pnlContent.setBackground(Color.LIGHT_GRAY);
        pnlContent.setBounds(0, 82, 1110, 561);
        add(pnlContent);
        pnlContent.setLayout(null);

        JPanel pnl_btnImport = new JPanel();
        pnl_btnImport.setBackground(new Color(225, 237, 119));
        pnl_btnImport.setBounds(55, 10, 142, 54);
        pnlContent.add(pnl_btnImport);
        pnl_btnImport.setLayout(null);

        JButton btnImport = new JButton("Import");
        btnImport.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnImport.setFocusPainted(false);
        btnImport.setContentAreaFilled(false);
        btnImport.setBorderPainted(false);
        btnImport.setBounds(0, 0, 142, 54);
        pnl_btnImport.add(btnImport);
        btnImport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ImportForm ifr=new ImportForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnImport.setBackground(new Color(142, 150, 72));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnImport.setBackground(new Color(225, 237, 119));
            }
        });

        JPanel pnl_btnBill = new JPanel();
        pnl_btnBill.setBackground(new Color(225, 237, 119));
        pnl_btnBill.setBounds(228, 10, 142, 54);
        pnlContent.add(pnl_btnBill);
        pnl_btnBill.setLayout(null);

        JButton btnBill = new JButton("Bill");
        btnBill.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnBill.setFocusPainted(false);
        btnBill.setContentAreaFilled(false);
        btnBill.setBorderPainted(false);
        btnBill.setBounds(0, 0, 142, 54);
        pnl_btnBill.add(btnBill);
        btnBill.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_btnBill.setBackground(new Color(142, 150, 72));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_btnBill.setBackground(new Color(225, 237, 119));
            }
        });

        JPanel pnl_Content_2 = new JPanel();
        pnl_Content_2.setBackground(Color.LIGHT_GRAY);
        pnl_Content_2.setForeground(Color.BLACK);
        pnl_Content_2.setBounds(0, 78, 1110, 483);
        pnlContent.add(pnl_Content_2);
        pnl_Content_2.setLayout(null);

        setVisible(true);
    }
}
