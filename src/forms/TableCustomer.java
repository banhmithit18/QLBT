package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TableCustomer extends TableForm {
    @Override
    void ActionEvent(ActionEvent e) {
        ////add su kien
        for(int i = 0 ; i<this.row ; i++)
        {
            if(this.btnEdit.get(i).equals(e.getSource()))
            {
                System.out.println(((JLabel)pnlData.get(i).getComponent(1)).getText());
                /// i = so thu tu cua dong
                /// 1 tuong duong voi so thu tu cua cot (vd o day la name)
            }

        }
    }
}
