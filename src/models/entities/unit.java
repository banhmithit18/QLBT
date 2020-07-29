package models.entities;
public class unit {
    public float value, unitconvertvalue;
    public String unitname,unitconvertname;

    public unit(float value, float unitconvertvalue, String unitname, String unitconvertname) {
        this.value = value;
        this.unitconvertvalue = unitconvertvalue;
        this.unitname = unitname;
        this.unitconvertname = unitconvertname;
    }
}
