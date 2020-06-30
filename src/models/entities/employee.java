package models.entities;

public class employee {
        public String employeename, employeephone, emplouyeeemail, employeeaddress,username,password;

    public employee(String employeename, String employeephone, String emplouyeeemail, String employeeaddress, String username, String password) {
        this.employeename = employeename;
        this.employeephone = employeephone;
        this.emplouyeeemail = emplouyeeemail;
        this.employeeaddress = employeeaddress;
        this.username = username;
        this.password = password;
    }
    public employee(){
        employeename = "";
        employeephone = "";
        emplouyeeemail = "";
        employeeaddress = "";
        username = "";
        password = "";

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

    public String getEmplouyeeemail() {
        return emplouyeeemail;
    }

    public void setEmplouyeeemail(String emplouyeeemail) {
        this.emplouyeeemail = emplouyeeemail;
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
}
