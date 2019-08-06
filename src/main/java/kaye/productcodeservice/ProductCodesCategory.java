package kaye.productcodeservice;

import kaye.productcodeservice.ProductCode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductCodesCategory {
    private String name;
    private List<ProductCode> codes = new ArrayList<ProductCode>();

    public ProductCodesCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

<<<<<<< HEAD
    public void addCode (String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) {
        codes.add(new ProductCode(this, code, data, note, timesRemaining, madeAt, madeBy));
=======
    public void addCode(String code, String note, int timesRemaining, User madeBy) {
        codes.add(new ProductCode(this, code, note, madeBy));
    }
    
    public void applyCode(ProductCode code) {
        codes.add(code);
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef
    }

    public ProductCode getCode (String code) throws CodeDoesNotExistException {
        for (ProductCode productCode : codes) {
            if (productCode.proofCode(code)) return productCode;
        }
        throw new CodeDoesNotExistException();
    }

    public boolean proofCode (String code) {
        for (ProductCode productCode : codes) {
            if (productCode.proofCode(code)) return true;
        }
        return false;
    }

    public String getData(String code) {
        for (ProductCode productCode : codes) {
            if (productCode.proofCode(code)) return productCode.getData();
        }
        return "404: Code not found (from category)" + code;
    }

    public String getWholeData() {
        String returning = "";
        for (ProductCode code : codes) {
            returning += code.toString() + "\n";
        }
        return returning;
    }
}
