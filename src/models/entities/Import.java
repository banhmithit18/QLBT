package models.entities;

import java.sql.Timestamp;

public class Import {
    public String importid, productid;
    public int quantity, depotid, supplierid, unit, employeeid;
    public float price;
    public Timestamp date;

    public Import(String importid, String productid, int quantity, int depotid, int supplierid, int unit, int employeeid, float price, Timestamp date) {
        this.importid = importid;
        this.productid = productid;
        this.quantity = quantity;
        this.depotid = depotid;
        this.supplierid = supplierid;
        this.unit = unit;
        this.employeeid = employeeid;
        this.price = price;
        this.date = date;
    }

    public Import() {
        importid = null;
        productid = null;
        quantity = 0;
        depotid = 0;
        supplierid = 0;
        unit = 0;
        employeeid = 0;
        price = 0;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getDepotid() {
        return depotid;
    }

    public void setDepotid(int depotid) {
        this.depotid = depotid;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(int employeeid) {
        this.employeeid = employeeid;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
