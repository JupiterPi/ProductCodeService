package main.java.kaye.productcodeservice;

import kaye.productcodeservice.filetool.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.IOException;

@Service
public class ProductCodesDatabase {
    private List<ProductCodesCategory> categories = new ArrayList<ProductCodesCategory>();
    private List<User> users = new ArrayList<User>();

    public void addCategory(String name) {
        categories.add(new ProductCodesCategory(name));
    }

    public void addCode(String categoryName, String code, String note, int timesRemaining, Date madeAt, User madeBy) throws CategoryDoesNotExistException{
        boolean categorySet = false;
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                categorySet = true;
                category.addCode(code, note, timesRemaining, madeBy);
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
    
    public void loadData() throws IOException, CategoryDoesNotExistException {
        FileToolForProductCodeService categoriesFile = new FileToolForProductCodeService("categories.txt");
        categories = new ArrayList<ProductCodesCategory>();
        for (String line : categoriesFile.getFile()) {
            categories.add(new ProductCodesCategory(line));
        }
        
        FileToolForProductCodeService codesFile = new FileToolForProductCodeService("codes.txt");
        for (String line : codesFile.getFile()) {
            ProductCode code = new ProductCode(categories, line);
            code.getCategory().applyCode(code);
        }
        
        FileToolForProductCodeService usersFile = new FileToolForProductCodeService("users.txt");
        for (String line : usersFile.getFile()) {
            users.add(new User(line));
        }
    }
}
