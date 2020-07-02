package forms;

import utils.ChangePosition;
import utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class TableForm {
    public static JLabel[] labels, labelshead;
    public static JPanel[] pnlData;
    public static JButton[] btnEdit;
    public static int row, column;
    public static JPanel pnlAllData;
    public JScrollPane table(String table, String[] columnName, String query, Dimension d, boolean editable) {
        DBConnection db = new DBConnection();
        // lay so hang + so cot bang muon ve
        row = db.getRowCount(table);
        // lay so cot muon ve
        column = columnName.length;
        // tao panel tong chua panelheader va paneldata
        JPanel panel = new JPanel();
        //set layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // tao array label chua data
        labels = new JLabel[column * row + row + 1];
        // lay du lieu bang
        ArrayList<Object> ob = db.getAllData(query);
        // tao array label chua ten cot
        labelshead = new JLabel[column];
        // tao panel chua label header
        JPanel pnlheader = new JPanel();
        pnlheader.setLayout(new GridLayout(1, column));
        // ve bang ten cot
        int name = 0;
        for (JLabel item : labelshead
        ) {
            item = new JLabel();
            item.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
            item.setHorizontalAlignment(SwingConstants.CENTER);
            item.setVerticalAlignment(SwingConstants.CENTER);
            item.setText(columnName[name]);
            pnlheader.add(item);
            item.setPreferredSize(d);
            name++;
        }
        // add o action vao cuoi label header
        JLabel lblAction = new JLabel("Action");
        lblAction.setBorder(BorderFactory.createMatteBorder(1, 0, 1, 1, Color.black));
        lblAction.setHorizontalAlignment(SwingConstants.CENTER);
        lblAction.setVerticalAlignment(SwingConstants.CENTER);
        pnlheader.add(lblAction);
        //tao panel chua panel data
        pnlAllData = new JPanel();
        pnlAllData.setLayout(new GridLayout(row,0));
        //tao panel chua label data
        pnlData = new JPanel[row];
        //tao array label edit
        btnEdit = new JButton[row];
        // ve bang label data
        int z = 1;
        for (int i = 0; i < row; i++) {
            //khoi tao panel data
            pnlData[i] = new JPanel();
            pnlData[i].setLayout(new GridLayout(0,column+1));
            for (int y = 0; y < column; y++) {
                labels[z] = new JLabel();
                labels[z].setVerticalAlignment(SwingConstants.CENTER);
//                labels[z].setHorizontalAlignment(SwingConstants.CENTER);
                labels[z].setText(ob.get(z - 1).toString());
                labels[z].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
                labels[z].setPreferredSize(d);
                pnlData[i].add(labels[z]);
                z++;
            }
            pnlAllData.add(pnlData[i]);
            //add edit row
            btnEdit[i] = new JButton();
            btnEdit[i].setText("<HTML><FONT color=\"#006ce5\"><U>Edit</U></FONT>"
                    + " </HTML>");
            btnEdit[i].setHorizontalAlignment(SwingConstants.CENTER);
            btnEdit[i].setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
            btnEdit[i].setOpaque(false);
            btnEdit[i].setBackground(Color.WHITE);
            btnEdit[i].setPreferredSize(d);
            btnEdit[i].addActionListener(this::ActionEvent);
            pnlData[i].add(btnEdit[i]);
        }
        //add panel header va panel data vao panel tong
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        panel.add(pnlheader, c);
        c.gridy = 1;
        panel.add(pnlAllData, c);
        JScrollPane sp = new JScrollPane(panel);
        return sp;
    }
    abstract void ActionEvent(ActionEvent e);

}
