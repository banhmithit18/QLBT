package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateTable extends JScrollPane {
    // table = ten bang
    // columnName = ten cac cot
    // query = sql query
    public JScrollPane table(String table, String[] columnName, String query) {
        DBConnection db = new DBConnection();
        // lay so hang + so cot bang muon ve
        int row = db.getRowCount(table);
        // lay so cot muon ve
        int column = columnName.length;
        // tao panel tong chua panelheader va paneldata
        JPanel panel = new JPanel();
        //set layout
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        // tao array label chua data
        JLabel[] labels = new JLabel[column * row + 1];
        // lay du lieu bang
        ArrayList<Object> ob = db.getAllData(query);
        // tao array label chua ten cot
        JLabel[] labelshead = new JLabel[column];
        // tao panel chua label header
        JPanel pnlheader = new JPanel();
        pnlheader.setLayout(new GridLayout(1, column));
        // ve bang ten cot
        int name = 0;
        for (JLabel item : labelshead
        ) {
            item = new JLabel();
            item.setBorder(BorderFactory.createLineBorder(Color.black));
            item.setText(columnName[name]);
            pnlheader.add(item);
            name++;
        }
        //tao panel chua label data
        JPanel pnlData = new JPanel();
        pnlData.setLayout(new GridLayout(row, column));
        //tao array label chua data
        JLabel[] labelsData = new JLabel[row * column];
        // ve bang label data
        int z = 1;
        for (int i = 0; i < row; i++) {
            for (int y = 0; y < column; y++) {
                labels[z] = new JLabel();
                labels[z].setText(ob.get(z - 1).toString());
                labels[z].setBorder(BorderFactory.createLineBorder(Color.black));
                pnlData.add(labels[z]);
                z++;
            }
        }
        //add panel heaeder va panel data vao panel tong
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = 0;
        c.gridx = 0;
        panel.add(pnlheader, c);
        c.gridy = 1;
        panel.add(pnlData, c);
        JScrollPane sp = new JScrollPane(panel);
        return sp;
    }
}