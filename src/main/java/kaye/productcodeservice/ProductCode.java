package kaye.productcodeservice;

import java.util.Date;

public class ProductCode {
    private ProductCodesCategory category;
    private String code;
    private String note;
    private int timesRemaining;
    private Date madeAt;
    private User madeBy;
    
    public ProductCode(ProductCodesCategory category, String code, String note, int timesRemaining, Date madeAt, User madeBy) {
        this.category = category;
        this.code = code;
        this.note = note;
        this.timesRemaining = timesRemaining;
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
            if (timesRemaining > 0) {
                timesRemaining--;
                return true;
            }
        }
        return false;
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
}
