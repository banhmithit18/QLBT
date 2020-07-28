package forms;

import utils.ChangePosition;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

public class MainCustomer extends JDialog {
    int row;
    public static Dimension d;
    public static TableCustomer tp;
    public MainCustomer() {
        //setting form
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setBounds(350, 100, 824, 500);
        setModal(true);
        setResizable(false);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setTitle("Customer");
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


        JLabel lbl_managercustomer = new JLabel("Manager Customer");
        lbl_managercustomer.setForeground(new Color(0, 0, 0));
        lbl_managercustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_managercustomer.setBounds(320, 10, 198, 59);
        pnlHead.add(lbl_managercustomer);

        //add component to panel head

        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(270, 81, 200, 25);
        pnlHead.add(tfSearch);

        String[] boxColumn = {"All","Name", "Age", "Phone", "Email", "Address","City"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(140, 80, 120, 25);
        pnlHead.add(boxSearch);

        JButton btnSearch = new JButton("Search");
        btnSearch.setBounds(10, 80, 120, 25);

        JButton btnADD = new JButton("Add");
        btnADD.setBounds(600,80,80,25);
        btnADD.addActionListener(e -> {
            Addcustomer addcustomer=new Addcustomer();
        });
        pnlHead.add(btnADD);

        //add JPanel body
        JPanel pnlBody = new JPanel();
        pnlBody.setLayout(new GridLayout(1, 0));
        //add component to panel body
        String[] columnname = {"Name", "Age", "Phone", "Email", "Address","City"};
        String query ="select customername, customerage, customerphone, customeremail, customeraddress, cityname\n" +
                "from customer join city on customer.cityid=city.cityid\n";

        d = new Dimension(115, 20);
        tp = new TableCustomer();
        JScrollPane sp = tp.table("customer", columnname, query, d, true);
        //tao su kien search
        btnSearch.addActionListener(e -> {
            DBConnection db = new DBConnection();
            ChangePosition c = new ChangePosition();
            int position = 0;
            int index = boxSearch.getSelectedIndex();// lay vi tri index trong boxSearch
            String strSearch = tfSearch.getText(); // lay text trong text field
            row = db.getRowCount("customer"); // lay so hang
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


        //add 2 panel to box
        pnlBody.setPreferredSize(new Dimension(1300, 400));
        pnlHead.setPreferredSize(new Dimension(1300, 200));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainCustomer mainCustomer=new MainCustomer();
    }
}
