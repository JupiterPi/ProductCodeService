package kaye.productcodeservice;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductCodesDatabase {
    private List<ProductCodesCategory> categories = new ArrayList<ProductCodesCategory>();
    private List<User> users = new ArrayList<User>();

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

    public void addCategory(String name) {
        categories.add(new ProductCodesCategory(name));
    }

    public void addCode(String categoryName, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) throws CategoryDoesNotExistException{
        boolean categorySet = false;
        for (ProductCodesCategory category : categories) {
            if (category.getName().equals(categoryName)) {
                categorySet = true;
                category.addCode(code, data, note, timesRemaining, madeAt, madeBy);
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
    }
}
