package kaye.productcodeservice;

import java.util.List;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ProductCode {
    private ProductCodesCategory category;
    private String code;
    private String data;
    private String note;
    private Date validTo;
    private User madeBy;
    
<<<<<<< HEAD
    public ProductCode(ProductCodesCategory category, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) {
=======
    public ProductCode(ProductCodesCategory category, String code, String note, User madeBy) {
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef
        this.category = category;
        this.code = code;
        this.data = data;
        this.note = note;
        this.madeBy = madeBy;
        
        Calendar calendar = new GregorianCalendar();
        calendar.add(Calendar.DATE, 30);
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
        validTo = new Date(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));
        
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
<<<<<<< HEAD
            if (infinite) return true;
            else {
                if (timesRemaining > 0) {
                    timesRemaining--;
                    return true;
                }
            }
=======
            return (validTo.after(new Date()));
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef
        }
        return false;
    }

    public String getData() {
        return data;
    }
    
    public String getNote() {
        return note;
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
