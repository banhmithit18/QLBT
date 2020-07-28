package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static forms.SellForm2.table;

public class PopClick extends MouseAdapter {
    int rowindex;
    //    public static String relativeid ;
    public void mouseReleased(MouseEvent e){
        int r =  SellForm2.table.rowAtPoint(e.getPoint());
        if(r>= 0 && r < SellForm2.table.getRowCount()){
            SellForm2.table.setRowSelectionInterval(r,r);
        }
        else{
            SellForm2.table.clearSelection();
        }
        rowindex = SellForm2.table.getSelectedRow();
        if(rowindex < 0 )
            return;
        if(e.isPopupTrigger()  && e.getComponent() instanceof JTable)
        {
            if ( ((JTable) e.getComponent()).getColumnCount() == 3) {
                ViewProfile(e);
            }
        }
    }

    public void ViewProfile(MouseEvent e){
        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem editItem = new JMenuItem("Edit prisoner");
        editItem.addActionListener(e1 -> {
            System.out.println("alo");
        });
        popupMenu.add(editItem);

        popupMenu.show(e.getComponent(), e.getX(), e.getY());
    }
}