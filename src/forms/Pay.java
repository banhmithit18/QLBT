package forms;

import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        setBounds(100, 100, 382, 485);
        setLayout(null);

        JLabel lblcheckbill = new JLabel("Check Bill");
        lblcheckbill.setFont(new Font("Georgia", Font.BOLD, 30));
        lblcheckbill.setBounds(104, 20, 163, 42);
        add(lblcheckbill);

        area = new JTextArea();
        area.setText("");
        area.setFont(new Font("Tahoma", Font.BOLD, 6));
        area.setBounds(41, 72, 287, 293);
        add(area);
        area.setEnabled(false);

        BT_receipt = new JButton("Receipt");
        BT_receipt.setBounds(41, 386, 95, 30);
        add(BT_receipt);
        BT_receipt.addActionListener(e -> {
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            }
            String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
            try {
                Connection conn = DriverManager.getConnection(connectionUrl);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            String total =SellForm2.lblTotal_Money.getText();
            String pay=SellForm2.tfCusPey.getText();
            String balance=SellForm2.lblExcessCase_Money.getText();
            Date obj = new Date();
            String date=obj.toString();
            String employee=SellForm2.lblEmployeeName.getText();
            DefaultTableModel model = new DefaultTableModel();
            model =(DefaultTableModel)SellForm2.table.getModel();
            area.setText(area.getText()+ "*****************************************************************\n");
            area.setText(area.getText()+ "                     APTECH DRUGSTORE                             \n");
            area.setText(area.getText()+ "        212-214_NguyenDinhChieu_Q.3_HCM                             \n");
            area.setText(area.getText()+ "                     Hotaline:0123.456.789                             \n");
            area.setText(area.getText()+ "*****************************************************************\n") ;
            area.setText(area.getText()+ "             Date : " + date + "\n\n") ;
            area.setText(area.getText()+ "Employee : " + employee + "\n\n") ;


           //heading
            area.setText(area.getText() + "quantity" + "\t" + "price" + "\t" + "amount" + "\n");
            area.setText(area.getText() + "--------------------------------------------------------------\n");

            for(int i=0; i < model.getRowCount(); i++) {
                String productname=(String)model.getValueAt(i, 1);
                String quantity=(String)model.getValueAt(i, 2);
                String productprice=(String)model.getValueAt(i, 3);
                String productAmount=(String)model.getValueAt(i, 5);


                area.setText(area.getText() + "productname: " + productname +" "+"vnd"+ "\n" +  quantity + "\t" + productprice +" "+"vnd"+ "\t" + productAmount +" "+"vnd"+ "\n\n");
                area.setText(area.getText() + "--------------------------------------------------------------\n");

            }

//            area.setText(area.getText()+ "_____________________________________________\n");
            area.setText(area.getText()+"\n");
            area.setText(area.getText()  + "Total : " + total +" "+"vnd"+ "\n\n");
            area.setText(area.getText()  + "Pay : " +pay+" "+"vnd"+"\n\n");
            area.setText(area.getText()  + "Balance : " +balance+" "+"vnd"+ "\n\n");
            area.setText(area.getText()+"\n");
            area.setText(area.getText()+ "*****************************************************************\n");
            area.setText(area.getText()+ "                  THANK YOU SO MUCH !                             \n");
            area.setText(area.getText()+ "*****************************************************************\n\n\n") ;
            area.setText(area.getText()+"\n");
            area.setText(area.getText()+"\n");
            area.setText(area.getText()+"\n");



        });

        JButton BT_print = new JButton("Print");
        BT_print.setBounds(233, 386, 95, 30);
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
