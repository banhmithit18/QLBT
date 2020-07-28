package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public abstract class TableForm {
    protected JLabel[] labelshead;
    protected ArrayList<JPanel> pnlData = new ArrayList<>();
    protected ArrayList<JButton> btnEdit = new ArrayList<>();
    protected int row, column;
    protected JPanel pnlAllData;
    protected ArrayList<JLabel> labels = new ArrayList<>();

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
        pnlAllData.setLayout(new GridLayout(row, 0));
        // ve bang label data
        int z = 0;
        for (int i = 0; i < row; i++) {
            //khoi tao panel data
            pnlData.add(new JPanel());
            pnlData.get(i).setLayout(new GridLayout(0, column + 1));
            for (int y = 0; y < column; y++) {
                labels.add(new JLabel());
                labels.get(z).setVerticalAlignment(SwingConstants.CENTER);
//                labels[z].setHorizontalAlignment(SwingConstants.CENTER);
                labels.get(z).setText(ob.get(z).toString());
                labels.get(z).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
                labels.get(z).setPreferredSize(d);
                pnlData.get(i).add(labels.get(z));
                z++;
            }
            pnlAllData.add(pnlData.get(i));
            //add edit row
            btnEdit.add(new JButton());
            btnEdit.get(i).setText("<HTML><FONT color=\"#006ce5\"><U>Edit</U></FONT>"
                    + " </HTML>");
            btnEdit.get(i).setHorizontalAlignment(SwingConstants.CENTER);
            btnEdit.get(i).setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.black));
            btnEdit.get(i).setOpaque(false);
            btnEdit.get(i).setBackground(Color.WHITE);
            btnEdit.get(i).setPreferredSize(d);
            btnEdit.get(i).addActionListener(this::ActionEvent);
            pnlData.get(i).add(btnEdit.get(i));
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

    public ArrayList<JLabel> getLabels() {
        return labels;
    }

    public JLabel[] getLabelshead() {
        return labelshead;
    }

    public ArrayList<JPanel> getPnlData() {
        return pnlData;
    }

    public ArrayList<JButton> getBtnEdit() {
        return btnEdit;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public JPanel getPnlAllData() {
        return pnlAllData;
    }

    abstract void ActionEvent(ActionEvent e);

}
