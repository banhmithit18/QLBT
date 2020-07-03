package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;


public class TableDepot extends TableForm {

    @Override
    void ActionEvent(ActionEvent e) {
     ////add su kien
        System.out.println("A");
    }
    public JLabel[] getLabels() {
        return labels;
    }

    public JLabel[] getLabelshead() {
        return labelshead;
    }

    public JPanel[] getPnlData() {
        return pnlData;
    }

    public JButton[] getBtnEdit() {
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

}

