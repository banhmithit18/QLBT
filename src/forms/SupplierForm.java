package forms;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SupplierForm extends JPanel {
    int row;
    public static Dimension d;
    public static TableDepot tp;
    public SupplierForm(){
        setBounds(0,0,1098, 485);
        setLayout(null);
        setBorder(new LineBorder(new Color(0,0,0),1));
        JPanel pnlTable = new JPanel();
        pnlTable.setBounds(10, 285, 1078, 190);
        add(pnlTable);
        pnlTable.setLayout(new GridLayout(0, 1));

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBackground(Color.GREEN);
        pnlTitle.setBounds(1007, 1, 89, 20);
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblTitle = new JLabel("Supplier");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 10));
        lblTitle.setBounds(27, 0, 56, 18);
        pnlTitle.add(lblTitle);

        String[] columnname = {"suppliername", "supplierphonenumber", "supplieremail", "supplieraddress", "dept"};
        String query = "select suppliername , supplierphonenumber, supplieremail, supplieraddress, dept from supplier";

        d = new Dimension(164, 20);
        tp = new TableDepot();
        JScrollPane sp = tp.table("supplier", columnname, query, d, true);
        //tao su kien search
//        btnSearch.addActionListener(e -> {
//            ChangePosition c = new ChangePosition();
//            int position = 0;
//            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
//            String strSearch = tfSearch.getText(); // lay text trong text field
//            row = tp.row; // lay so hang
//            int column = 0;
//            if (index == 0 ) // tuong duong voi All trong boxSearch
//            {
//                for (JPanel item : tp.pnlData
//                ) {
//                    item.show();
//                }
//            } else {
//                for (int i = 0; i < row; i++) {
//                    if (tp.getLabels().get(index-1 + column).getText().matches(".*" + strSearch + ".*"))
//                    {
//                        tp.pnlData.get(i).show();
//                        for (int secondIndex = 0; secondIndex < tp.pnlAllData.getComponentCount(); secondIndex++) {
//                            if (tp.pnlAllData.getComponent(secondIndex) == (tp.pnlData.get(i))) {
//                                c.swap(tp.pnlAllData, position, secondIndex);
//                                position++;
//                            }
//                        }
//                    } else {
//                        tp.pnlData.get(i).hide();
//                    }
//                    column += tp.column;
//                }
//                //redraw lai panel
//                tp.pnlAllData.revalidate();
//                tp.pnlAllData.repaint();
//            }
//
//        });
        pnlTable.add(sp);
        setVisible(true);
    }
}
