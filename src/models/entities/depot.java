package models.entities;

import java.sql.Timestamp;

public class depot {
    public String importid,productid,expirationdate;
    public int quantity;
    public float price;
    public Timestamp importdate;

    public depot(String importid, String productid, int quantity, float price, String expirationdate, Timestamp importdate) {
        this.importid = importid;
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
        this.expirationdate = expirationdate;
        this.importdate = importdate;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getExpirationdate() {
        return expirationdate;
    }

    public void setExpirationdate(String expirationdate) {
        this.expirationdate = expirationdate;
    }

    public Timestamp getImportdate() {
        return importdate;
    }

    public void setImportdate(Timestamp importdate) {
        this.importdate = importdate;
    }
}

