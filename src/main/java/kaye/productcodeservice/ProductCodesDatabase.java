package main.java.kaye.productcodeservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductCodesDatabase {
    private List<ProductCodesCategory> categories = new ArrayList<ProductCodesCategory>();

    public boolean proofCode(String categoryName, String code) throws CategoryDoesNotExistException {
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                return category.proofCode(code);
            }
        }
        throw new CategoryDoesNotExistException();
    }
}
