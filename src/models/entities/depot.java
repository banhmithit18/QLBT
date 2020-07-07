package models.entities;

public class depot {
    public String productid;
    public int quantity;
    public float price;

    public depot(String productid, int quantity, float price) {
        this.productid = productid;
        this.quantity = quantity;
        this.price = price;
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
}

