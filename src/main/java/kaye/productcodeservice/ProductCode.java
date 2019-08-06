package kaye.productcodeservice;

import java.util.Date;

public class ProductCode {
    private Category category;
    private String code;
    private String data;
    private String note;
    private boolean infinite = false;
    private int timesRemaining;
    private Date madeAt;
    private User madeBy;
    
    public ProductCode(Category category, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) {
        this.category = category;
        this.code = code;
        this.data = data;
        this.note = note;
        if (timesRemaining == 0) infinite = true;
        else this.timesRemaining = timesRemaining;
        this.madeAt = madeAt;
        this.madeBy = madeBy;
    }

    public ProductCode(Database database, String line) throws CategoryDoesNotExistException, UserDoesNotExistException {
        String[] fields = line.split(";");
        this.category = database.getCategory(fields[0]);
        this.code = fields[1];
        this.data = fields[2];
        this.note = fields[3];
        if (fields[4].equals("infinite")) infinite = true;
        else timesRemaining = Integer.parseInt(fields[4]);
        madeAt = new Date(Long.parseLong(fields[5]));
        madeBy = database.getUser(fields[6]);
    }
    
    public Category getCategory() {
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

    public String toStringForFile() {
        String returning = "";
        returning += category.getName() + ";" + code + ";" + data + ";" + note  + ";";
        if (infinite) returning += "infinite;";
        else returning += timesRemaining + ";";
        returning += madeAt.getTime() + ";" + madeBy.getName();
        return returning;
    }
}
