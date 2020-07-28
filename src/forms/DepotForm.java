package forms;

import utils.ChangePosition;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DepotForm extends JDialog {
    int row;
    public static Dimension d;
    public static TableDepot tp;
    public DepotForm() {
        //setting form
        setBounds(100, 100, 1000, 500);
        setUIFont f = new setUIFont();
        f.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        //add root panel
        JPanel contentPane = (JPanel) getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        //create box
        Box[] boxes = new Box[2];
        boxes[0] = Box.createHorizontalBox();
        boxes[1] = Box.createHorizontalBox();

        boxes[0].createGlue();
        boxes[1].createGlue();

        contentPane.add(boxes[0]);
        contentPane.add(boxes[1]);

        //add JPanel head
        JPanel pnlHead = new JPanel();
        pnlHead.setLayout(null);

        //add component to panel head



        String[] boxColumn = {"All","ImportID","Name", "Content", "Quantity", "Price", "Supplier","Import Date","Expiration Date"};
        JComboBox boxSearch = new JComboBox(boxColumn);
        boxSearch.setBounds(230, 40, 120, 25);
        pnlHead.add(boxSearch);
//        boxSearch.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if(boxSearch.getSelectedIndex() == 0){
//                    tfSearch.setEditable(false);
//                }else {
//                    tfSearch.setEditable(true);
//                }
//            }
//        });

//        JButton btnSearch = new JButton("Search");
//        btnSearch.setBounds(120, 80, 120, 25);

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
        JTextField tfSearch = new JTextField();
        tfSearch.setBounds(10, 40, 200, 25);
        tfSearch.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
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
            }

            @Override
            public void keyReleased(KeyEvent e) {
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
            }
        });
        pnlHead.add(tfSearch);
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

        JButton btnChkExp = new JButton("Check Expiration");
        btnChkExp.setFont(new Font("Arial",Font.PLAIN,10));
        btnChkExp.setBounds(10,90,120,25);
        pnlHead.add(btnChkExp);
        btnChkExp.addActionListener(e -> {
            String outOfDateProduct = "";
            String nearOutOfDateProduct = "";
            long currentDay = System.currentTimeMillis();
            long dayInMil = 604800000 ;
            DBConnection db = new DBConnection();
            for ( int i = 0 ;i<db.getRowCount("depot");i++)
            {
                String expDayInString  = ((JLabel)tp.pnlData.get(i).getComponent(7)).getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = null;
                try {
                    date = sdf.parse(expDayInString);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                long result = date.getTime()-currentDay;

                if(  result <= dayInMil &&  result >= 0)
                {
                    String imp = ((JLabel)tp.pnlData.get(i).getComponent(0)).getText();
                    String proName = ((JLabel)tp.pnlData.get(i).getComponent(1)).getText();
                    nearOutOfDateProduct = nearOutOfDateProduct + imp +" - "+proName+"\n";
                }
                else if ( result < 0)
                {
                    System.out.println(date.getTime() - currentDay);
                    String imp = ((JLabel)tp.pnlData.get(i).getComponent(0)).getText();
                    String proName = ((JLabel)tp.pnlData.get(i).getComponent(1)).getText();
                    outOfDateProduct = outOfDateProduct + imp +" - "+proName +"\n";
                }
            }
            ExpForm ep = new ExpForm(nearOutOfDateProduct,"Near Out of Date Product");
            ExpForm epx = new ExpForm(outOfDateProduct,"Out of Date Product");







        });

        //add 2 panel to box
        pnlBody.setPreferredSize(new Dimension(1300, 400));
        pnlHead.setPreferredSize(new Dimension(1300, 200));
        boxes[0].add(pnlHead);
        boxes[1].add(pnlBody);


        setVisible(true);
    }

}
