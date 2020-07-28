package models.entities;

import java.sql.Timestamp;
public class receipt {
    public String receipttype,productid;
    public float price;
    public int supplierid,customerid,employeeid;
    public Timestamp date;

    public receipt(String receipttype, float price, int supplierid, int employeeid, Timestamp date) {
        this.receipttype = receipttype;
        this.price = price;
        this.supplierid = supplierid;
        this.employeeid = employeeid;
        this.date = date;
        customerid = 0;
    }
}
