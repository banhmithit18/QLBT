package models.entities;

import java.sql.Timestamp;

public class export {
    public String importid,productid,expirationdate;
    public int storeid,quantity,employeeid;
    public Timestamp date;
    public float price;

    public export(String importid, String productid, String expirationdate, int storeid, int quantity, int employeeid, Timestamp date, float price) {
        this.importid = importid;
        this.productid = productid;
        this.expirationdate = expirationdate;
        this.storeid = storeid;
        this.quantity = quantity;
        this.employeeid = employeeid;
        this.date = date;
        this.price = price;
    }
    public export(){
        importid = null;
        productid = null;
        expirationdate = null;
        storeid = 0 ;
        quantity = 0 ;
        employeeid = 0;
        date = null;
        price = 0;
    }

    public String getImportid() {
        return importid;
    }

    public void setImportid(String importid) {
        this.importid = importid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public int getStoreid() {
        return storeid;
    }

    public void setStoreid(int storeid) {
        this.storeid = storeid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
