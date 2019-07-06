package main.java.kaye.productcodeservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductCodesDatabase {
    private List<ProductCodesCategory> categories = new ArrayList<ProductCodesCategory>();

    public void addCategory(String name) {
        categories.add(new ProductCodesCategory(name));
    }

    public void addCode(String categoryName, String code, String note, int timesRemaining, Date madeAt, User madeBy) throws CategoryDoesNotExistException{
        boolean categorySet = false;
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                categorySet = true;
                category.addCode(code, note, timesRemaining, madeAt, madeBy);
            }
        }
        if (!categorySet) throw new CategoryDoesNotExistException();
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
