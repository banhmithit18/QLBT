package models.entities;

public class product {
    public String productid,productname,productcontent;
    public int supplierid;

    public product(String productid, String productname, String productcontent, int supplierid) {
        this.productid = productid;
        this.productname = productname;
        this.productcontent = productcontent;
        this.supplierid = supplierid;
    }
    public product(){
        productid = "";
        productname = "";
        productcontent = "";
        supplierid = 0;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductcontent() {
        return productcontent;
    }

    public void setProductcontent(String productcontent) {
        this.productcontent = productcontent;
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }
}
