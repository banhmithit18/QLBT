package forms;

import utils.ChangePosition;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class DepotForm extends JDialog {
    public DepotForm() {
        //setting form
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 500);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Depot");
        //add root panel
        JPanel rootPnl = (JPanel) getContentPane();
        rootPnl.setLayout(new BoxLayout(rootPnl, BoxLayout.Y_AXIS));
        //create box
        Box[] boxes = new Box[2];
        boxes[0] = Box.createHorizontalBox();
        boxes[1] = Box.createHorizontalBox();

        boxes[0].createGlue();
        boxes[1].createGlue();

        rootPnl.add(boxes[0]);
        rootPnl.add(boxes[1]);

        //add JPanel head
        JPanel pnlHead = new JPanel();
        pnlHead.setLayout(null);

        //add component to panel head

        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(10, 40, 200, 25);
        pnlHead.add(tfSearch);

        String[] boxColumn = {"All", "Name", "Content", "Quantity", "Price", "Supplier"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(230, 40, 120, 25);
        pnlHead.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(120, 80, 120, 25);

        JButton btnImport = new JButton("Import");
        btnImport.setBounds(500,40,80,25);
        btnImport.addActionListener(e -> {});
        pnlHead.add(btnImport);

        //add JPanel body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new GridLayout(1, 0));
        //add component to panel body
        String[] columnname = {"Name", "Content", "Quantity", "Price", "Supplier"};
        String query = "select productname , productcontent, depot.quantity, depot.price, suppliername\n" +
                "from depot join product on  depot.productid = product.productid\n" +
                "join supplier on product.supplierid = supplier.supplierid";
        Dimension d = new Dimension(164, 20);
        TableDepot tp = new TableDepot();
        JScrollPane sp = tp.table("depot", columnname, query, d, true);
        ;
        //tao su kien search
        btnSearch.addActionListener(e -> {
            ChangePosition c = new ChangePosition();
            int position = 0;
            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
            String strSearch = tfSearch.getText(); // lay text trong text field
            int row = tp.row; // lay so hang
            int column = 0;
            if (index == 0 ) // tuong duong voi All trong boxSearch
            {
                for (JPanel item : tp.pnlData
                ) {
                    item.show();
                }
            } else {
                for (int i = 1; i <= row; i++) {
                    if (tp.labels[index + column].getText().matches(".*" + strSearch + ".*"))
                    {
                        tp.pnlData[i - 1].show();
                        for (int secondIndex = 0; secondIndex < tp.pnlAllData.getComponentCount(); secondIndex++) {
                            if (tp.pnlAllData.getComponent(secondIndex) == (tp.pnlData[i-1])) {
                                c.swap(tp.pnlAllData, position, secondIndex);
                                position++;
                            }
                        }
                    } else {
                        tp.pnlData[i - 1].hide();
                    }
                    column += tp.column;
                }
                //redraw lai panel
                tp.pnlAllData.revalidate();
                tp.pnlAllData.repaint();
            }

        });
        pnlHead.add(btnSearch);
        pnlBody.add(sp);

        //add 2 panel to box
        pnlBody.setPreferredSize(new Dimension(1300, 400));
        pnlHead.setPreferredSize(new Dimension(1300, 200));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);


        setVisible(true);
    }
}
