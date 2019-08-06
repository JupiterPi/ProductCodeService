package kaye.productcodeservice;

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
<<<<<<< HEAD

    public void addUser(String username, String password) {
        users.add(new User(username, password));
    }

    public boolean proofUser(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username) && user.proofPassword(password)) return true;
        }
        return false;
    }

    public User getUser(String username, String password) throws UserDoesNotExistException {
        for (User user : users) {
            if (user.getName().equals(username) && user.proofPassword(password)) return user;
        }
        throw new UserDoesNotExistException();
    }
=======
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef

    public void addCategory(String name) {
        categories.add(new ProductCodesCategory(name));
    }

    public void addCode(String categoryName, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) throws CategoryDoesNotExistException{
        boolean categorySet = false;
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                categorySet = true;
<<<<<<< HEAD
                category.addCode(code, data, note, timesRemaining, madeAt, madeBy);
=======
                category.addCode(code, note, timesRemaining, madeBy);
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef
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
<<<<<<< HEAD

    public String getData(String categoryName, String code) throws CategoryDoesNotExistException {
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                return category.getData(code);
            }
        }
        throw new CategoryDoesNotExistException();
    }

    public String getWholeData() {
        String returning = "";
        for (ProductCodesCategory category : categories) {
            returning += category.getWholeData() + "\n";
        }
        return returning;
=======
    
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
>>>>>>> 5e710c20c9de5b7d9f80bfb1416403ac16c6e0ef
    }
}
