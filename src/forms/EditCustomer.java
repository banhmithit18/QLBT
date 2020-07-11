package forms;

import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class EditCustomer extends JDialog {
    TableForm TF;
    public EditCustomer() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Edit Customer");
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(100, 100, 450, 500);
        setLayout(null);




        setVisible(true);
    }
}
