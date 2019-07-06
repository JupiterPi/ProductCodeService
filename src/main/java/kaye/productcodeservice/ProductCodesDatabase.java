package kaye.testor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductCodesDatabase {
    private List<ProductCodesCategory> categories = new ArrayList<ProductCodesCategory>();

    public void addCode(ProductCodesCategory category, String code, String note, int timesRemaining, Date madeAt, User madeBy) {
        category.addCode(code, note, timesRemaining, madeAt, madeBy);
    }

    public boolean proofCode(String categoryName, String code) throws CategoryDoesNotExistException {
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                return category.proofCode(code);
            }
        }
        throw new CategoryDoesNotExistException();
    }
}
