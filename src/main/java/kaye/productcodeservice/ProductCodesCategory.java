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

    public void addCode (String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) {
        codes.add(new ProductCode(this, code, data, note, timesRemaining, madeAt, madeBy));
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
