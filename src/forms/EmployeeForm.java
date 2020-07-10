package forms;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmployeeForm extends JPanel {
    int row;
    public static Dimension d;
    public static TableDepot tp;
    private JTextField tfEmployeeID,tfEmployeePhone,tfEmployeeMail;
    public EmployeeForm(){
        setBounds(0,0,1110, 643);
        setBackground(new Color(238,238,238));
        setLayout(null);

        JPanel pnlTitle = new JPanel();
        pnlTitle.setBounds(0, 0, 1110, 80);
        pnlTitle.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlTitle.setBackground(new Color(52, 235, 58));
        add(pnlTitle);
        pnlTitle.setLayout(null);

        JLabel lblMC = new JLabel("Employee");
        lblMC.setFont(new Font("Times New Roman", Font.BOLD, 25));
        lblMC.setBounds(491, 10, 257, 47);
        pnlTitle.add(lblMC);

        JPanel pnlContent = new JPanel();
        pnlContent.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlContent.setBounds(0, 68, 1110, 575);
        add(pnlContent);
        pnlContent.setLayout(null);

        JPanel pnlID_Search = new JPanel();
        pnlID_Search.setBounds(39, 48, 186, 75);
        pnlID_Search.setBackground(new Color(230, 108, 108));
        pnlContent.add(pnlID_Search);
        pnlID_Search.setLayout(null);

        JLabel lblEmployeeID = new JLabel("Employee ID:");
        lblEmployeeID.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEmployeeID.setBounds(10, 10, 115, 13);
        pnlID_Search.add(lblEmployeeID);

        tfEmployeeID = new JTextField();
        tfEmployeeID.setBounds(10, 45, 166, 19);
        tfEmployeeID.setOpaque(false);
        tfEmployeeID.setBorder(BorderFactory.createMatteBorder(0,0, 1,0,Color.black));
        pnlID_Search.add(tfEmployeeID);
        tfEmployeeID.setColumns(10);

        JPanel pnlPhone_Search = new JPanel();
        pnlPhone_Search.setBackground(new Color(230, 108, 108));
        pnlPhone_Search.setBounds(39, 155, 186, 75);
        pnlContent.add(pnlPhone_Search);
        pnlPhone_Search.setLayout(null);

        JLabel lblEmployeePhone = new JLabel("Employee Phone:");
        lblEmployeePhone.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEmployeePhone.setBounds(10, 10, 115, 13);
        pnlPhone_Search.add(lblEmployeePhone);

        tfEmployeePhone = new JTextField();
        tfEmployeePhone.setOpaque(false);
        tfEmployeePhone.setColumns(10);
        tfEmployeePhone.setBorder(BorderFactory.createMatteBorder(0,0, 1,0,Color.black));
        tfEmployeePhone.setBounds(10, 46, 166, 19);
        pnlPhone_Search.add(tfEmployeePhone);

        JPanel pnlMail_Search = new JPanel();
        pnlMail_Search.setBackground(new Color(230, 108, 108));
        pnlMail_Search.setBounds(39, 264, 186, 75);
        pnlContent.add(pnlMail_Search);
        pnlMail_Search.setLayout(null);

        JLabel lblEmployeeMail = new JLabel("Employee Mail:");
        lblEmployeeMail.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblEmployeeMail.setBounds(10, 10, 115, 13);
        pnlMail_Search.add(lblEmployeeMail);

        tfEmployeeMail = new JTextField();
        tfEmployeeMail.setOpaque(false);
        tfEmployeeMail.setColumns(10);
        tfEmployeeMail.setBorder(BorderFactory.createMatteBorder(0,0, 1,0,Color.black));
        tfEmployeeMail.setBounds(10, 47, 166, 19);
        pnlMail_Search.add(tfEmployeeMail);

        JPanel pnlTable_Employee = new JPanel();
        pnlTable_Employee.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnlTable_Employee.setBounds(260, 50, 850, 437);
        pnlTable_Employee.setLayout(new GridLayout(0,1));
        pnlContent.add(pnlTable_Employee);
        String[] columnname = {"employeename", "employeeage", "employeephone", "employeeemail", "employeeaddress", "city"};
        String query = "select employeename , employeeage, employeephone, employeeemail, employeeaddress, city.cityname from employee inner join city on employee.city = city.cityid";
        d = new Dimension(164, 20);
        tp = new TableDepot();
        JScrollPane sp = tp.table("supplier", columnname, query, d, true);
        pnlTable_Employee.add(sp);

        JPanel pnl_BtnSearch = new JPanel();
        pnl_BtnSearch.setBounds(65, 373, 132, 46);
        pnl_BtnSearch.setBackground(new Color(52, 235, 58));
        pnlContent.add(pnl_BtnSearch);
        pnl_BtnSearch.setBorder(new LineBorder(new Color(0,0,0)));
        pnl_BtnSearch.setLayout(null);

        JButton btnSearch = new JButton("Search");
        btnSearch.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnSearch.setBounds(0, 0, 132, 46);
        btnSearch.setFocusPainted(false);
        btnSearch.setContentAreaFilled(false);
        btnSearch.setBorderPainted(false);
        pnl_BtnSearch.add(btnSearch);
        btnSearch.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_BtnSearch.setBackground(new Color(0, 99, 25));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_BtnSearch.setBackground(new Color(52, 235, 58));
            }
        });

        JPanel pnl_BtnAdd = new JPanel();
        pnl_BtnAdd.setBounds(954, 18, 117, 28);
        pnl_BtnAdd.setBackground(new Color(230, 108, 108));
        pnl_BtnAdd.setBorder(new LineBorder(new Color(0,0,0),1));
        pnlContent.add(pnl_BtnAdd);
        pnl_BtnAdd.setLayout(null);

        JButton btnAddEmployee = new JButton("Add Employee");
        btnAddEmployee.setBounds(0, 0, 117, 28);
        btnAddEmployee.setFocusPainted(false);
        btnAddEmployee.setContentAreaFilled(false);
        btnAddEmployee.setBorderPainted(false);
        pnl_BtnAdd.add(btnAddEmployee);
        btnAddEmployee.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                AddEmployeeForm aef=new AddEmployeeForm();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                pnl_BtnAdd.setBackground(new Color(143, 67, 67));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                pnl_BtnAdd.setBackground(new Color(230, 108, 108));
            }
        });

        JPanel pnl_lblEmployee = new JPanel();
        pnl_lblEmployee.setBorder(new LineBorder(new Color(0, 0, 0)));
        pnl_lblEmployee.setBounds(260, 26, 95, 22);
        pnl_lblEmployee.setBackground(new Color(52, 235, 58));
        pnlContent.add(pnl_lblEmployee);
        pnl_lblEmployee.setLayout(null);

        JLabel llblEmployee = new JLabel("        Employee");
        llblEmployee.setBounds(0, 0, 95, 19);
        pnl_lblEmployee.add(llblEmployee);

    }
}
