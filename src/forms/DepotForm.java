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

        JPanel pnlSearch = new JPanel();
        pnlSearch.setToolTipText("Search");
        pnlSearch.setLayout(null);
        pnlSearch.setBounds(10, 10, 400, 130);
        pnlHead.add(pnlSearch);

        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(10, 40, 200, 25);
        pnlSearch.add(tfSearch);

        String[] boxColumn = {"All", "Name", "Content", "Quantity", "Price", "Supplier"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(230, 40, 120, 25);
        pnlSearch.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(120, 80, 120, 25);
        btnSearch.addActionListener(e -> {
            int position = 0;
            int index = boxSearch.getSelectedIndex();
            String strSearch = tfSearch.getText();
            int row = CreateTable.row;
            int column = 0;
            if (index == 0) {
                for (JPanel item : CreateTable.pnlData
                ) {
                    item.show();
                }
            } else {

                for (int i = 1; i <= row; i++) {
                    if (!CreateTable.labels[index + column].getText().matches(".*" + strSearch + ".*")) {
                        CreateTable.pnlData[i - 1].hide();
                    } else {
                        CreateTable.pnlData[i - 1].show();
                        ChangePosition c = new ChangePosition();
                        c.swap(CreateTable.pnlAllData, position, i - 1);
                        position++;
                    }

                    column += CreateTable.column;
                }
                CreateTable.pnlAllData.validate();
                CreateTable.pnlAllData.repaint();
            }

        });
        pnlSearch.add(btnSearch);
        //add JPanel body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new GridLayout(1, 0));
        //add component to panel body
        String[] columnname = {"Name", "Content", "Quantity", "Price", "Supplier"};
        String query = "select productname , productcontent, depot.quantity, depot.price, suppliername\n" +
                "from depot join product on  depot.productid = product.productid\n" +
                "join supplier on product.supplierid = supplier.supplierid";
        Dimension d = new Dimension(164, 20);
        JScrollPane sp = new CreateTable().table("Depot", columnname, query, d, true);
        pnlBody.add(sp);

        //add 2 panel to box
        pnlBody.setPreferredSize(new Dimension(1300, 400));
        pnlHead.setPreferredSize(new Dimension(1300, 200));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);


        setVisible(true);
    }
}
