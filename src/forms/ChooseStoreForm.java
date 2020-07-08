package forms;

import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class ChooseStoreForm extends JDialog {
    public ChooseStoreForm(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN,12));
        setTitle("Store");
        setBounds(100, 100, 382, 245);

        JLabel lblNewLabel = new JLabel("Store");
        lblNewLabel.setBounds(54, 47, 45, 25);
        add(lblNewLabel);

        DBConnection db = new DBConnection();
        String[] strStore = db.getComboboxString("Select storeid from store").split(",");
        JComboBox comboBox = new JComboBox(strStore);
        ComboboxToolTipRender render = new ComboboxToolTipRender();
        comboBox.setRenderer(render);
        String [] storeToolTipArr = db.getStoreAddress().split(",");
        List<String> storeTooltip = Arrays.asList(storeToolTipArr);
        render.setTooltips(storeTooltip);
        ComboboxDecorator.decorate(comboBox,true);
        comboBox.setBounds(136, 47, 200, 25);
        add(comboBox);

        JButton btnNewButton = new JButton("Submit");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String storeId = comboBox.getSelectedItem().toString();
                if(db.check("Select storeid from store where storeid="+storeId))
                {
                    dispose();
                    InventoryForm iv = new InventoryForm(storeId);
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane,"Could not find store");
                }
            }
        });
        btnNewButton.setBounds(116, 122, 100, 25);
        add(btnNewButton);

        setVisible(true);
    }
}
