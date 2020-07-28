package forms;

import models.entities.store;
import utils.DBConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.Vector;

public class FormStore  extends JFrame {
    private JPanel panel;
    private String header[] = {"Store Id","Store Name", "Store Address", "City"};
    private DefaultTableModel tableModel = new DefaultTableModel(header, 0);


    public FormStore() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        panel = (JPanel) getContentPane();
        panel.setLayout(null);
        JLabel lb_stmini = new JLabel("Store Mini");
        lb_stmini.setFont(new Font("Tahoma", Font.ITALIC, 20));
        lb_stmini.setBounds(364, 5, 101, 46);
        panel.add(lb_stmini);

        JLabel lb_id = new JLabel("Id");
        lb_id.setFont(new Font("Tahoma", Font.BOLD, 13));
        lb_id.setBounds(40, 54, 21, 20);
        panel.add(lb_id);

        JTextField tf_id = new JTextField();
        tf_id.setBounds(102, 59, 33, 19);
        panel.add(tf_id);
        tf_id.setColumns(10);
        tf_id.setEditable(false);

        JLabel lb_stname = new JLabel("Store Name");
        lb_stname.setBounds(10, 84, 82, 27);
        panel.add(lb_stname);

        JLabel lb_staddress = new JLabel("Store Address");
        lb_staddress.setBounds(10, 139, 82, 27);
        panel.add(lb_staddress);

        JLabel lb_stcity = new JLabel("City");
        lb_stcity.setBounds(10, 189, 82, 27);
        panel.add(lb_stcity);

        JTextField tf_stname = new JTextField();
        tf_stname.setBounds(102, 88, 170, 19);
        panel.add(tf_stname);
        tf_stname.setColumns(10);

        JTextField tf_staddress = new JTextField();
        tf_staddress.setBounds(102, 143, 170, 19);
        panel.add(tf_staddress);
        tf_staddress.setColumns(10);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(311, 84, 551, 206);
        panel.add(scrollPane);

        DBConnection db = new DBConnection();
        String strcity = db.getAllName("city");
        String[] arrCity = strcity.split(",");
        JComboBox boxCity = new JComboBox(arrCity);
        boxCity.setBounds(102, 193, 170, 19);
        add(boxCity);


        JTable table_store = new JTable();
        table_store.setModel(new DefaultTableModel(
                new Object[][]{
                },
                new String[]{
                        "Store Id","Store Name", "Store Address", "City"
                }
        ));
        scrollPane.setViewportView(table_store);
        table_store.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //String date="yyyy-MM-dd";
                int i = table_store.getSelectedRow();
                TableModel model1 = table_store.getModel();
                tf_id.setText(model1.getValueAt(i, 0).toString());
                tf_stname.setText(model1.getValueAt(i, 1).toString());
                tf_staddress.setText(model1.getValueAt(i, 2).toString());
                boxCity.setSelectedItem(model1.getValueAt(i, 3).toString());
            }
        });


        JButton bt_addst = new JButton("Add");
        bt_addst.setBounds(106, 263, 71, 27);
        panel.add(bt_addst);
        bt_addst.addActionListener(e -> {
            String stname = tf_stname.getText();
            String staddress = tf_staddress.getText();
            int boxcitys = db.getColumnID("city", boxCity.getSelectedItem().toString());
            store st = new store();
            st.setStorename(stname);
            st.setStoreaddress(staddress);
            st.setCity(boxcitys);
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "insert into store(storename, storeaddress, cityid)  values(?,?,?)";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, stname);
                preparedStatement.setString(2, staddress);
                preparedStatement.setString(3, String.valueOf(boxcitys));
                if (!stname.equals("")) {
                    if (!staddress.equals("")) {
                        if (STname(stname)){
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(rootPane, "Success");
                            tf_stname.setText("");
                            tf_staddress.setText("");
                            boxCity.setSelectedItem("");
                        }else {
                            JOptionPane.showMessageDialog(rootPane, "Store Name available");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Please enter Store Address");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please enter Store Name");
                }

            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });

        JButton bt_editst = new JButton("Edit");
        bt_editst.setBounds(201, 263, 71, 27);
        panel.add(bt_editst);
        bt_editst.addActionListener(e -> {
            String stname = tf_stname.getText();
            String tfid=tf_id.getText();
            String staddress = tf_staddress.getText();
            int boxcitys = db.getColumnID("city", boxCity.getSelectedItem().toString());
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
                Connection conn = DriverManager.getConnection(connectionUrl);
                String sql = "update store set storename = ?, storeaddress = ?, cityid = ? where storeid= ? ";
                PreparedStatement preparedStatement = conn.prepareStatement(sql);
                preparedStatement.setString(1, stname);
                preparedStatement.setString(2, staddress);
                preparedStatement.setString(3, String.valueOf(boxcitys));
                preparedStatement.setString(4, tfid);
                if (!stname.equals("")) {
                    if (!staddress.equals("")) {
                        if (STname(stname)) {
                            preparedStatement.execute();
                            JOptionPane.showMessageDialog(rootPane, "Success");
                            tf_stname.setText("");
                            tf_staddress.setText("");
                            boxCity.setSelectedItem("");
                            tf_id.setText("");
                        }else {
                            JOptionPane.showMessageDialog(rootPane,"Store Name available");
                        }
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Please enter Store Address");
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Please enter Store Name");
                }

            } catch (ClassNotFoundException classNotFoundException) {
                classNotFoundException.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        });


        JTextField tf_searchst = new JTextField();
        tf_searchst.setBounds(416, 51, 132, 19);
        panel.add(tf_searchst);
        tf_searchst.setColumns(10);

        JButton bt_searchst = new JButton("Search");
        bt_searchst.setBounds(311, 50, 85, 21);
        panel.add(bt_searchst);
        bt_searchst.addActionListener(e -> {
            String Search = tf_searchst.getText();
            Connection c = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                c = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456");
                // command watch data
                String sql = "select storeid, storename, storeaddress, cityname " + " from store  join city on city.cityid=store.cityid";
                //if search by title
                if (Search.length() > 0) {
                    sql = sql + " where storename like '%" + Search + "%'";
                }
                //create object thu thi command select
                st = c.createStatement();
                //thu thi
                rs = st.executeQuery(sql);
                Vector data = null;
                tableModel.setRowCount(0);
                // if relatives aren't available
                if (rs.isBeforeFirst() == false) {
                    JOptionPane.showMessageDialog(this, "Relatives aren't available");
                    return;
                }
                //while not get data
                while (rs.next()) {
                    data = new Vector();
                    data.add(rs.getString("storeid"));
                    data.add(rs.getString("storename"));
                    data.add(rs.getString("storeaddress"));
                    data.add(rs.getString("cityname"));
                    // add one row in table model
                    tableModel.addRow(data);
                }
                //add data in JTable
                table_store.setModel(tableModel);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    if (c != null) {
                        c.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }

        });
        setVisible(true);
        setBounds(100, 100, 886, 337);
    }
    public boolean STname(String stname) {
        String connecUrl = "jdbc:sqlserver://localhost:1433;databaseName=QLBT;user=sa;password=123456";
        try (Connection conn = DriverManager.getConnection(connecUrl)) {
            Statement stmt = conn.createStatement();
            String query = "SELECT storename FROM store WHERE storename ='" + stname + "'";
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
        FormStore formStore=new FormStore();
    }
}




