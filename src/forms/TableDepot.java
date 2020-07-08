package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class TableDepot extends TableForm {

    @Override
    void ActionEvent(ActionEvent e) {
     ////add su kien
        for(int i = 0 ; i<this.row ; i++)
        {
            if(this.btnEdit.get(i).equals(e.getSource()))
            {
                System.out.println(i);
            }
        }
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

}

