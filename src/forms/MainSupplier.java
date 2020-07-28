package forms;

import utils.ChangePosition;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class MainSupplier extends JDialog{
    int row;
    public static Dimension d;
    public static TableSupplier ts;
    public MainSupplier() {
        //setting form
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(300, 100, 707, 500);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Supplier");
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

        JLabel lbl_managersupplier = new JLabel("Manager Supplier");
        lbl_managersupplier.setForeground(new Color(0, 0, 0));
        lbl_managersupplier.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_managersupplier.setBounds(265, 5, 198, 59);
        pnlHead.add(lbl_managersupplier);


        //add component to panel head

        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(280, 81, 200, 25);
        pnlHead.add(tfSearch);

        JButton btnADD = new JButton("Add");
        btnADD.setBounds(550,80,80,25);
        btnADD.addActionListener(e -> {

        });
        pnlHead.add(btnADD);


        String[] boxColumn = {"All","Name_supplier", "Phone_supplier", "Email_supplier", "Address_supplier","Dept"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(120, 80, 150, 25);
        pnlHead.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(10, 80, 100, 25);

        //add JPanel body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new GridLayout(1, 0));
        //add component to panel body
        String[] columnname = {"Name_supplier", "Phone_supplier", "Email_supplier", "Address_supplier","Dept"};
        String query ="select suppliername, supplierphonenumber, supplieremail, supplieraddress, dept from supplier";
        d = new Dimension(115, 20);
        ts = new TableSupplier();
        JScrollPane sp = ts.table("supplier", columnname, query, d, true);
        //tao su kien search
        btnSearch.addActionListener(e -> {
            DBConnection db = new DBConnection();
            ChangePosition c = new ChangePosition();
            int position = 0;
            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
            String strSearch = tfSearch.getText(); // lay text trong text field
            row = db.getRowCount("supplier"); // lay so hang
            int column = 0;
            if (index == 0 ) // tuong duong voi All trong boxSearch
            {
                for (JPanel item : ts.pnlData
                ) {
                    item.show();
                }
            } else {
                for (int i = 0; i < row; i++) {
                    if (ts.getLabels().get(index-1 + column).getText().matches(".*" + strSearch + ".*"))
                    {
                        ts.pnlData.get(i).show();
                        for (int secondIndex = 0; secondIndex < ts.pnlAllData.getComponentCount(); secondIndex++) {
                            if (ts.pnlAllData.getComponent(secondIndex) == (ts.pnlData.get(i))) {
                                c.swap(ts.pnlAllData, position, secondIndex);
                                position++;
                            }
                        }
                    } else {
                        ts.pnlData.get(i).hide();
                    }
                    column += ts.column;
                }
                //redraw lai panel
                ts.pnlAllData.revalidate();
                ts.pnlAllData.repaint();
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

    public static void main(String[] args) {
      MainSupplier mainSupplier=new MainSupplier();
    }
}
