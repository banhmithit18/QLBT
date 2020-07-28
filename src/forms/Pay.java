package forms;

import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Date;

public class Pay extends JDialog  {
    public static JTextArea area;
    public static JButton BT_receipt;
    public Pay() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Pay");
        setUIFont.Font(new FontUIResource("Arial",Font.PLAIN, 12));
        setBounds(600, 90, 382, 485);
        setLayout(null);

        area = new JTextArea();
        area.setText("");
        area.setBounds(41, 32, 287, 293);
        add(area);
        area.setEnabled(false);

        BT_receipt = new JButton("Receipt");
        BT_receipt.setBounds(41, 361, 95, 30);
        add(BT_receipt);
        BT_receipt.addActionListener(e -> {
            String total =SellForm2.lblTotal_Money.getText();
            String pay=SellForm2.tfCusPey.getText();
            String balance=SellForm2.lblExcessCase_Money.getText();
            Date obj = new Date();
            String date=obj.toString();
            String employee=SellForm2.lblEmployeeName.getText();
            DefaultTableModel model = new DefaultTableModel();
            model =(DefaultTableModel)SellForm2.table.getModel();
            area.setText(area.getText()+ "*****************************************************************\n");
            area.setText(area.getText()+ "                         APTECH DRUGSTORE                             \n");
            area.setText(area.getText()+ "            212-214_NguyenDinhChieu_Q.3_HCM                             \n");
            area.setText(area.getText()+ "                         Hotaline:0123.456.789                             \n");
            area.setText(area.getText()+ "*****************************************************************\n") ;
            area.setText(area.getText()+ "                            Today : " + date + "\n\n") ;
            area.setText(area.getText()+ "Employee : " + employee + "\n\n") ;


            //heading
            area.setText(area.getText() + "productname" + "\t" + "quantity" + "\t" + "price" + "\t" + "expirationdate" + "\t" + "amount" + "\n\n");

            for(int i=0; i < model.getRowCount(); i++) {
                String productname=(String)model.getValueAt(i, 1);
                String qantity=(String)model.getValueAt(i, 2);
                String productprice=(String)model.getValueAt(i, 3);
                String expirationdate=(String)model.getValueAt(i, 4);
                String productAmount=(String)model.getValueAt(i, 5);

                area.setText(area.getText() + productname + "\t" +  qantity + "\t" + productprice + "\t" + expirationdate + "\t" + productAmount + "\n\n");

            }

            area.setText(area.getText()+ "_____________________________________________\n");
            area.setText(area.getText()+"\n");
            area.setText(area.getText()  + "Total : " + total + "\n\n");
            area.setText(area.getText()  + "Pay : " +pay+"\n\n");
            area.setText(area.getText()  + "Balance : " +balance+ "\n\n");
            area.setText(area.getText()+"\n");
            area.setText(area.getText()+ "*****************************************************************\n");
            area.setText(area.getText()+ "                        THANKS YOU SO MUCH !                             \n");
            area.setText(area.getText()+ "*****************************************************************\n") ;


        });

        JButton BT_print = new JButton("Print");
        BT_print.setBounds(233, 361, 95, 30);
        add(BT_print);
        BT_print.addActionListener(e -> {
            try{
                area.print();
            }catch (Exception exception){

            }
        });


        setVisible(true);
    }

    public static void main(String[] args) {
        Pay pay=new Pay();
    }
}
