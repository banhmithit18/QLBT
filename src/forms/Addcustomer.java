package forms;

import models.entities.customer;
import utils.DBConnection;
import utils.setUIFont;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.sql.*;

public class Addcustomer extends JDialog {
    private JTextField textField_name;
    private JTextField textField_age;
    private JTextField textField_phone;
    private JTextField textField_email;
    private JTextField textField_address;
    public Addcustomer() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setTitle("Add Custolmer");
        setUIFont.Font(new FontUIResource("Arial", Font.PLAIN, 12));
        setBounds(500, 100, 537, 574);
        setLayout(null);

        JLabel lbl_addcustomer = new JLabel("Add Customer");
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

        textField_name = new JTextField();
        textField_name.setBounds(166, 93, 256, 19);
        add(textField_name);
        textField_name.setColumns(10);

        textField_age = new JTextField();
        textField_age.setBounds(166, 173, 256, 19);
        add(textField_age);
        textField_age.setColumns(10);

        textField_phone = new JTextField();
        textField_phone.setBounds(166, 253, 256, 19);
        add(textField_phone);
        textField_phone.setColumns(10);

        textField_email = new JTextField();
        textField_email.setBounds(166, 323, 256, 19);
        add(textField_email);
        textField_email.setColumns(10);

        textField_address = new JTextField();
        textField_address.setBounds(166, 403, 256, 19);
        add(textField_address);
        textField_address.setColumns(10);

        DBConnection db = new DBConnection();
        String strcity = db.getAllName("city");
        String [] arrCity = strcity.split(",");
        JComboBox boxCity = new JComboBox(arrCity);
        boxCity.setBounds(166, 459, 256, 21);
        add(boxCity);

        JButton BT_save = new JButton("Save");
        BT_save.setFont(new Font("Tahoma", Font.PLAIN, 15));
        BT_save.setBounds(253, 500, 85, 21);
        add(BT_save);
        BT_save.addActionListener(e -> {
            String tfname=textField_name.getText();
            Integer tfage=0;
            try {
                tfage = Integer.parseInt(textField_age.getText());
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
                JOptionPane.showMessageDialog(rootPane,"Please enter your age by number");
            }
            String tfphone=textField_phone.getText();
            String tfemail=textField_email.getText();
            String tfaddress=textField_address.getText();
            int boxcitys=db.getColumnID("city",boxCity.getSelectedItem().toString());
            customer ct=new customer();
            ct.setCustomername(tfname);
            ct.setPcustomerage(tfage);
            ct.setCustomerphone(tfphone);
            ct.setCustomeremail(tfemail);
            ct.setCustomeraddress(tfaddress);
            ct.setCityid(boxcitys);
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into customer(customername,customerage,customerphone,customeremail,customeraddress,cityid)  values(?,?,?,?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, tfname);
                preparedStatement.setInt(2, tfage);
                preparedStatement.setString(3, tfphone);
                preparedStatement.setString(4, tfemail);
                preparedStatement.setString(5, tfaddress);
                preparedStatement.setString(6, String.valueOf(boxcitys));
                if (!tfname.equals("")) {
                    if (!tfphone.equals("")) {
                        if (!tfemail.equals("")) {
                            if (!tfaddress.equals("")) {
                                if (CheckPhone(tfphone)) {
                                    preparedStatement.execute();
                                    JOptionPane.showMessageDialog(rootPane, "Add Success");
                                    textField_name.setText("");
                                    textField_age.setText("");
                                    textField_phone.setText("");
                                    textField_email.setText("");
                                    textField_address.setText("");
                                    boxCity.setSelectedItem("");
                                } else {
                                    JOptionPane.showMessageDialog(rootPane, "PhoneNumber was available");
                                }
                            } else {
                                JOptionPane.showMessageDialog(rootPane, "Please enter your address");
                            }
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Please enter your email");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Please enter your phone");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please enter your name");
                }
            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        setVisible(true);
    }
    public boolean CheckPhone(String tfphone) {
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
        try (Connection con = DriverManager.getConnection(connectionUrl)) {
            Statement stmt = con.createStatement();
            String query = "SELECT customerphone FROM customer WHERE customerphone ='" + tfphone + "'";
            ResultSet rs = stmt.executeQuery(query);
            if (!rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public static void main(String[] args) {
        Addcustomer addcustomer=new Addcustomer();
    }
}
