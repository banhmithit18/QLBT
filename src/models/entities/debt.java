package models.entities;

import java.sql.Timestamp;
public class debt {
    public int supplierid;
    public float value;
    public String status,paymentperiod;

    public debt(int supplierid, float value, String paymentperiod,String status) {
        this.supplierid = supplierid;
        this.value = value;
        this.paymentperiod = paymentperiod;
        this.status = status;
    }
    public debt(){
        supplierid = 0;
        value = 0;
        paymentperiod = null;
        status ="";
    }

    public int getSupplierid() {
        return supplierid;
    }

    public void setSupplierid(int supplierid) {
        this.supplierid = supplierid;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public String getPaymentperiod() {
        return paymentperiod;
    }

    public void setPaymentperiod(String paymentperiod) {
        this.paymentperiod = paymentperiod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
