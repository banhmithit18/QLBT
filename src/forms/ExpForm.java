package forms;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ExpForm extends JDialog {
    public ExpForm(String product,String title){
        setModal(true);
        setResizable(false);
        setTitle(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);
        setBounds(400,200,250,300);
        JTextArea text = new JTextArea(product);
        text.setBounds(10,10,250,300);
        add(text);
        text.setEditable(false);
        this.setVisible(true);
    }
}
