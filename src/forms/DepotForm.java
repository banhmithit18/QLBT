package forms;

import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class DepotForm extends JDialog {
    public DepotForm () {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100,100,1000,500);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN,12));
        setTitle("Depot");
        setLayout(new BorderLayout());
        JPanel pnlData = new JPanel();
        pnlData.setBorder(BorderFactory.createLineBorder(Color.black));
        String [] columnname = {"ID","Quantity","Price"};
        String query = "Select * from depot";
        Dimension d =  new Dimension(200,50);
        JScrollPane sp = new CreateTable().table(columnname,query,d);
        pnlData.setLayout(new GridLayout(1,0));
        pnlData.add(sp);
        add(pnlData,BorderLayout.SOUTH);
        JPanel pnlComponent = new JPanel();
        pnlComponent.setBorder(BorderFactory.createLineBorder(Color.black));
        add(pnlComponent,BorderLayout.NORTH);
        setVisible(true);
    }
}
