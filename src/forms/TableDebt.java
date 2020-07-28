package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TableDebt extends  TableForm {
    @Override
    void ActionEvent(ActionEvent e) {
        for(int i = 0 ; i<this.row ; i++)
        {
            if(this.btnEdit.get(i).equals(e.getSource()))
            {
                int debtid = Integer.parseInt(((JLabel)pnlData.get(i).getComponent(0)).getText());
                int supplier = Integer.parseInt(((JLabel)pnlData.get(i).getComponent(1)).getText());
                float value = Float.parseFloat(((JLabel)pnlData.get(i).getComponent(2)).getText());
                PayForm p = new PayForm(debtid,supplier,value);
            }
        }
    }
}
