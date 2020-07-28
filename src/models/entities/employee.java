package models.entities;

public class employee {
        public String employeename, employeephone, employeeemail, employeeaddress,username,password;
        public int storeid,checkacc;

    public employee(String employeename, String employeephone, String employeeemail, String employeeaddress, String username, String password,int storeid) {
        this.employeename = employeename;
        this.employeephone = employeephone;
        this.employeeemail = employeeemail;
        this.employeeaddress = employeeaddress;
        this.username = username;
        this.password = password;
        this.storeid = storeid;
        checkacc = 0;
    }
    public employee(){
        employeename = "";
        employeephone = "";
        employeeemail = "";
        employeeaddress = "";
        username = "";
        password = "";
        storeid = 0;
        checkacc = 0;

    }

    public String getEmployeename() {
        return employeename;
    }

    public void setEmployeename(String employeename) {
        this.employeename = employeename;
    }

    public String getEmployeephone() {
        return employeephone;
    }

    public void setEmployeephone(String employeephone) {
        this.employeephone = employeephone;
    }
    public String getEmployeeaddress() {
        return employeeaddress;
    }

    public void setEmployeeaddress(String employeeaddress) {
        this.employeeaddress = employeeaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmployeeemail() {
        return employeeemail;
    }

    public void setEmployeeemail(String employeeemail) {
        this.employeeemail = employeeemail;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getCheckacc() {
        return checkacc;
    }

    public void setCheckacc(int checkacc) {
        this.checkacc = checkacc;
    }
}
