package forms;

import utils.DBConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TableCustomer extends TableForm {
    public static String Name_customer="";
    public static int Age_Customer =0;
    public static String Phone_customer="";
    public static String Email_customer="";
    public static String Address_customer="";
    public static String City_customer = "";

    @Override
    void ActionEvent(ActionEvent e) {
        DBConnection db=new DBConnection();
        ////add su kien
        for(int i = 0 ; i<this.row ; i++)
        {
            if(this.btnEdit.get(i).equals(e.getSource()))
            {
                Name_customer = (((JLabel) pnlData.get(i).getComponent(0)).getText());
                Age_Customer = Integer.parseInt(((JLabel) pnlData.get(i).getComponent(1)).getText());
                Phone_customer = (((JLabel) pnlData.get(i).getComponent(2)).getText());
                Email_customer = (((JLabel) pnlData.get(i).getComponent(3)).getText());
                Address_customer = (((JLabel) pnlData.get(i).getComponent(4)).getText());
                City_customer = (((JLabel) pnlData.get(i).getComponent(5)).getText());

                /// i = so thu tu cua dong
                /// 1 tuong duong voi so thu tu cua cot (vd o day la name)
            }
        }
        EditCustomer editCustomer = new EditCustomer(Name_customer, Age_Customer, Phone_customer, Email_customer, Address_customer,City_customer);

    }
}
