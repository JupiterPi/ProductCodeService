package main.java.kaye.productcodeservice;

import java.util.Date;

public class ProductCode {
    private ProductCodesCategory category;
    private String code;
    private String note;
    private Date validTo;
    private User madeBy;
    
    public ProductCode(ProductCodesCategory category, String code, String note, User madeBy) {
        this.category = category;
        this.code = code;
        this.note = note;
        this.madeBy = madeBy;
        
        Calendar calendar = new GregorianCalendar();
        calender.add(Calendar.DATE, 30);
        validTo = calendar.getTime();
    }
    
    public ProductCode(List<ProductCodesCategory> categories, String line) throws CategoryDoesNotExistException {
        String[] fields = line.split(";");
        
        this.code = fields[1];
        this.note = fields[2];
        boolean categorySet = false;
        
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(fields[0])) this.category = category;
        }
        if (!categorySet) throw new CategoryDoesNotExistException();
        
        String[] s = fields[3].split(".");
        validTo = new Date(Integer.toInt(s[0]), Integer.toInt(s[1]), Integer.toInt(s[2]));
        
        this.madeBy = new User (fields[4]);
    }
    
    public ProductCodesCategory getCategory() {
        return category;
    }
    
    public String getCode() {
        return code;
    }
    
    public boolean proofCode(String code) {
        if (code.equals(this.code)) {
            return (validTo.after(new Date()));
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
