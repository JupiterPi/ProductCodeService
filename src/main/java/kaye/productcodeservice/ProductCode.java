package kaye.productcodeservice;

import java.util.Date;

public class ProductCode {
    private ProductCodesCategory category;
    private String code;
    private String data;
    private String note;
    private boolean infinite;
    private int timesRemaining;
    private Date madeAt;
    private User madeBy;
    
    public ProductCode(ProductCodesCategory category, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) {
        this.category = category;
        this.code = code;
        this.data = data;
        this.note = note;
        if (timesRemaining == 0) infinite = true;
        else this.timesRemaining = timesRemaining;
        this.madeAt = madeAt;
        this.madeBy = madeBy;
    }
    
    public ProductCodesCategory getCategory() {
        return category;
    }
    
    public String getCode() {
        return code;
    }
    
    public boolean proofCode(String code) {
        if (code.equals(this.code)) {
            if (infinite) return true;
            else {
                if (timesRemaining > 0) {
                    timesRemaining--;
                    return true;
                }
            }
        }
        return false;
    }

    public String getData() {
        return data;
    }
    
    public String getNote() {
        return note;
    }
    
    public Date getMadeAt() {
        return madeAt;
    }
    
    public User getMadeBy() {
        return madeBy;
    }

    public String toString() {
        String returning = "";
        returning += category.getName() + ";" + code + ";" + data + ";" + note  + ";";
        if (infinite) returning += "infinite;";
        else returning += timesRemaining + ";";
        returning += madeAt.toString() + ";" + madeBy.getName();
        return returning;
    }
}
