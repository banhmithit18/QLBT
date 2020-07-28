package forms;

import models.entities.customer;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.*;

public class EditCustomer extends JDialog {

    public EditCustomer(String name_ct, int age_ct, String phone_ct, String  email_ct, String address_ct, String city_ct) {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Edit Custolmer");
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(500, 100, 537, 574);
        setLayout(null);

        JLabel lbl_addcustomer = new JLabel("Edit Customer");
        lbl_addcustomer.setFont(new Font("Tahoma", Font.BOLD, 20));
        lbl_addcustomer.setBounds(223, 10, 148, 55);
        add(lbl_addcustomer);

        JLabel lbl_name = new JLabel("Name");
        lbl_name.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_name.setBounds(86, 90, 70, 21);
        add(lbl_name);

        JLabel lbl_age = new JLabel("Age");
        lbl_age.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_age.setBounds(86, 170, 70, 21);
        add(lbl_age);

        JLabel lbl_phone = new JLabel("Phone");
        lbl_phone.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_phone.setBounds(86, 250, 70, 21);
        add(lbl_phone);

        JLabel lbl_email = new JLabel("Email\r\n");
        lbl_email.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_email.setBounds(86, 320, 70, 21);
        add(lbl_email);

        JLabel lbl_Address = new JLabel("Address");
        lbl_Address.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_Address.setBounds(86, 400, 70, 21);
        add(lbl_Address);

        JLabel lbl_city = new JLabel("City");
        lbl_city.setFont(new Font("Tahoma", Font.PLAIN, 15));
        lbl_city.setBounds(86, 457, 70, 21);
        add(lbl_city);

        JTextField textField_name = new JTextField();
        textField_name.setBounds(166, 93, 256, 19);
        add(textField_name);
        textField_name.setColumns(10);
        textField_name.setText(name_ct);
        textField_name.setEditable(false);

        JTextField textField_age = new JTextField();
        textField_age.setBounds(166, 173, 256, 19);
        add(textField_age);
        textField_age.setColumns(10);
        textField_age.setText(String.valueOf(age_ct));

        JTextField textField_phone = new JTextField();
        textField_phone.setBounds(166, 253, 256, 19);
        add(textField_phone);
        textField_phone.setColumns(10);
        textField_phone.setText(phone_ct);
        textField_phone.setEditable(false);

        JTextField textField_email = new JTextField();
        textField_email.setBounds(166, 323, 256, 19);
        add(textField_email);
        textField_email.setColumns(10);
        textField_email.setText(email_ct);

        JTextField textField_address = new JTextField();
        textField_address.setBounds(166, 403, 256, 19);
        add(textField_address);
        textField_address.setColumns(10);
        textField_address.setText(address_ct);

        DBConnection db = new DBConnection();
        String strcity = db.getAllName("city");
        String[] arrCity = strcity.split(",");
        JComboBox boxCity = new JComboBox(arrCity);
        boxCity.setBounds(166, 459, 256, 21);
        add(boxCity);
        boxCity.setSelectedItem(city_ct);

        JButton BT_save = new JButton("Edit");
        BT_save.setFont(new Font("Tahoma", Font.PLAIN, 15));
        BT_save.setBounds(253, 500, 85, 21);
        add(BT_save);
        BT_save.addActionListener(e -> {
            String namecustomer=textField_name.getText();
            Integer agecustomer=0;
            try {
                agecustomer = Integer.parseInt(textField_age.getText());
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,"Please enter your age by number");
            }
            String phonecustomer=textField_phone.getText();
            String emailcustomer=textField_email.getText();
            String addresscustomer=textField_address.getText();
            int cbcity=db.getColumnID("city", boxCity.getSelectedItem().toString());

            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "update customer set customerage = '"+agecustomer+"' , customerphone ='"+phonecustomer+"' , customeremail ='"+emailcustomer+"' , customeraddress = '"+addresscustomer+"' , cityid = '"+cbcity+"'where customername ='"+namecustomer+"'";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                    if (!phonecustomer.equals("")) {
                        if (!emailcustomer.equals("")) {
                            if (!addresscustomer.equals("")) {
                                if (CheckPhone(phonecustomer)){
                                    preparedStatement.executeUpdate();
                                    JOptionPane.showMessageDialog(rootPane, "Update Success");
                                    int row = MainCustomer.tp.row;
                                    int column = 0 ;
                                    for ( int i = 0 ; i<row ;i++)
                                    {
                                        if(MainCustomer.tp.labels.get(0+column).getText().equals(name_ct))
                                        {
                                            MainCustomer.tp.labels.get(1+column).setText(textField_age.getText());
                                            MainCustomer.tp.labels.get(2+column).setText(textField_phone.getText());
                                            MainCustomer.tp.labels.get(3+column).setText(textField_email.getText());
                                            MainCustomer.tp.labels.get(4+column).setText(textField_address.getText());
                                            MainCustomer.tp.labels.get(5+column).setText(boxCity.getSelectedItem().toString());
                                        }
                                        column += MainCustomer.tp.column;
                                    }
                                    textField_name.setText("");
                                    textField_age.setText("");
                                    textField_phone.setText("");
                                    textField_email.setText("");
                                    textField_address.setText("");
                                    boxCity.setSelectedItem("");
                                }else {
                                    JOptionPane.showMessageDialog(rootPane,"Phone was available");
                                }
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Please enter your address customer");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Please enter your email customer");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Please enter your phone customer");
                    }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        setVisible(true);
    }

    public boolean CheckPhone(String phonecustomer) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT supplierphonenumber FROM supplier WHERE supplierphonenumber ='" + phonecustomer + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }
}
