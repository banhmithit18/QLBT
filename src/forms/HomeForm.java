package forms;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomeForm extends JPanel {
    public HomeForm(){
        setBounds(0,0,1110, 643);
        setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBounds(0,0,1110, 80);
        pnlTitle.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlTitle.setBackground(new Color(52, 235, 58));
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblNewLabel = new JLabel("DRUG MANAGEMENT");
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblNewLabel.setBounds(406, 10, 298, 47);
        pnlTitle.add(lblNewLabel);

        JPanel pnlContent = new JPanel();
        pnlContent.setBackground(Color.LIGHT_GRAY);
        pnlContent.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlContent.setBounds(0, 67, 1110, 575);
        add(pnlContent);
        pnlContent.setLayout(null);
        setVisible(true);
    }
}
