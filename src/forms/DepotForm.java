package forms;

import utils.ChangePosition;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class DepotForm extends JDialog {
    int row;
    public static Dimension d;
    public static TableDepot tp;
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

        String[] boxColumn = {"All","ImportID","Name", "Content", "Quantity", "Price", "Supplier","Import Date","Expiration Date"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(230, 40, 120, 25);
        pnlHead.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(120, 80, 120, 25);

        //add JPanel body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new GridLayout(1, 0));
        //add component to panel body
        String[] columnname = {"ImportID","Name", "Content", "Quantity", "Price", "Supplier","Import Date","Expiration Date"};
        String query = "select importid,productname , productcontent, depot.quantity, depot.price, suppliername,importdate,expirationdate\n" +
                "from depot join product on  depot.productid = product.productid\n" +
                "join supplier on product.supplierid = supplier.supplierid";
        d = new Dimension(115, 20);
        tp = new TableDepot();
        JScrollPane sp = tp.table("depot", columnname, query, d, true);
        //tao su kien search
        btnSearch.addActionListener(e -> {
            DBConnection db = new DBConnection();
            ChangePosition c = new ChangePosition();
            int position = 0;
            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
            String strSearch = tfSearch.getText(); // lay text trong text field
            row = db.getRowCount("depot"); // lay so hang
            int column = 0;
            if (index == 0 ) // tuong duong voi All trong boxSearch
            {
                for (JPanel item : tp.pnlData
                ) {
                    item.show();
                }
            } else {
                for (int i = 0; i < row; i++) {
                    if (tp.getLabels().get(index-1 + column).getText().matches(".*" + strSearch + ".*"))
                    {
                        tp.pnlData.get(i).show();
                        for (int secondIndex = 0; secondIndex < tp.pnlAllData.getComponentCount(); secondIndex++) {
                            if (tp.pnlAllData.getComponent(secondIndex) == (tp.pnlData.get(i))) {
                                c.swap(tp.pnlAllData, position, secondIndex);
                                position++;
                            }
                        }
                    } else {
                        tp.pnlData.get(i).hide();
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

        JButton btnImport = new JButton("Import");
        btnImport.setBounds(500,40,80,25);
        btnImport.addActionListener(e -> {
            ImportForm im = new ImportForm();

        });
        pnlHead.add(btnImport);
        JButton btnExport = new JButton("Export");
        btnExport.setBounds(600,40,80,25);
        btnExport.addActionListener(e -> {
            ExportForm ex = new ExportForm();
        });
        pnlHead.add(btnExport);

        //add 2 panel to box
        pnlBody.setPreferredSize(new Dimension(1300, 400));
        pnlHead.setPreferredSize(new Dimension(1300, 200));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);


        setVisible(true);
    }
}
