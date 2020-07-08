package models.entities;

import java.sql.Timestamp;

public class inventory {
    public String exportid,importid,productid,expirationdate;
    public int storeid,quantity;
    public float price;
    public Timestamp exportdate;

    public inventory(String exportid, String importid, String productid, String expirationdate, int storeid, int quantity, float price, Timestamp exportdate) {
        this.exportid = exportid;
        this.importid = importid;
        this.productid = productid;
        this.expirationdate = expirationdate;
        this.storeid = storeid;
        this.quantity = quantity;
        this.price = price;
        this.exportdate = exportdate;
    }
    public inventory()
    {
        exportid = null;
        importid = null;
        productid = null;
        expirationdate = null;
        storeid = 0 ;
        quantity = 0;
        price = 0;
        exportdate = null;
    }

    public String getExportid() {
        return exportid;
    }

    public void setExportid(String exportid) {
        this.exportid = exportid;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Timestamp getExportdate() {
        return exportdate;
    }

    public void setExportdate(Timestamp exportdate) {
        this.exportdate = exportdate;
    }
}
