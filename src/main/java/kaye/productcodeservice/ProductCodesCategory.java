package main.java.kaye.productcodeservice;

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

    public void addCode(String code, String note, int timesRemaining, User madeBy) {
        codes.add(new ProductCode(this, code, note, madeBy));
    }
    
    public void applyCode(ProductCode code) {
        codes.add(code);
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
}
