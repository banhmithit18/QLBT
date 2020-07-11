package forms;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class TableSupplier extends TableForm {
    public static String Name_supplier="";
    public static String Phone_supplier="";
    public static String Email_supplier="";
    public static String Address_supplier="";
    public static String Dept_supplier="";
    @Override
    void ActionEvent(ActionEvent e) {

            ////add su kien
            for (int i = 0; i < this.row; i++) {
                if (this.btnEdit.get(i).equals(e.getSource())) {

                    Name_supplier = (((JLabel) pnlData.get(i).getComponent(0)).getText());
                    Phone_supplier = (((JLabel) pnlData.get(i).getComponent(1)).getText());
                    Email_supplier = (((JLabel) pnlData.get(i).getComponent(2)).getText());
                    Address_supplier = (((JLabel) pnlData.get(i).getComponent(3)).getText());
                    Dept_supplier = (((JLabel) pnlData.get(i).getComponent(4)).getText());
                        /// i = so thu tu cua dong
                    /// 1 tuong duong voi so thu tu cua cot (vd o day la name)
                }

            }
        Editsupplier editsupplier=new Editsupplier(Name_supplier,Phone_supplier,Address_supplier,Email_supplier,Dept_supplier);
        }
    }

