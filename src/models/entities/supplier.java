package models.entities;

public class supplier {
    public int supplierid;
    public String suppliername, supplierphonenumber, supplieremail, supplieraddress;
    public float dept;

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public String getSuppliername() {
        return suppliername;
    }

    public void setSuppliername(String suppliername) {
        this.suppliername = suppliername;
    }

    public String getSupplierphonenumber() {
        return supplierphonenumber;
    }

    public void setSupplierphonenumber(String supplierphonenumber) {
        this.supplierphonenumber = supplierphonenumber;
    }

    public String getSupplieremail() {
        return supplieremail;
    }

    public void setSupplieremail(String supplieremail) {
        this.supplieremail = supplieremail;
    }

    public String getSupplieraddress() {
        return supplieraddress;
    }

    public void setSupplieraddress(String supplieraddress) {
        this.supplieraddress = supplieraddress;
    }

    public float getDept() {
        return dept;
    }

    public void setDept(float dept) {
        this.dept = dept;
    }
}
