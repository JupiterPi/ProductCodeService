package main.java.kaye.productcodeservice;

import java.util.Date;

public class ProductCode {
    private ProductCodesCategory category;
    private String code;
    private String note;
    private Date madeAt;
    private User madeBy;
    
    public ProductCode(ProductCodesCategory category, String code, String note, Date madeAt, User madeBy) {
        this.category = category;
        this.code = code;
        this.note = note;
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
        return (code.equals(this.code));
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
