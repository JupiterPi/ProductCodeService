package kaye.productcodeservice;

import kaye.productcodeservice.filetool.FileTool;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class Database {
    private List<Category> categories = new ArrayList<Category>();
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

    public User getUser(String username) throws UserDoesNotExistException {
        for (User user : users) if (user.getName().equals(username)) return user;
        throw new UserDoesNotExistException();
    }

    public void addCategory(String name) {
        categories.add(new Category(name));
    }

    public Category getCategory(String name) throws CategoryDoesNotExistException {
        for (Category category : categories) {
            if (category.getName().equals(name)) return category;
        }
        System.out.println("Category " + name + " does not exist, it's not found");
        throw new CategoryDoesNotExistException();
    }

    public void addCode(String categoryName, String code, String data, String note, int timesRemaining, Date madeAt, User madeBy) throws CategoryDoesNotExistException{
        boolean categorySet = false;
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                categorySet = true;
                category.addCode(code, data, note, timesRemaining, madeAt, madeBy);
            }
        }
        if (!categorySet) throw new CategoryDoesNotExistException();
    }

    public boolean proofCode(String categoryName, String code) throws CategoryDoesNotExistException {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category.proofCode(code);
            }
        }
        throw new CategoryDoesNotExistException();
    }

    public String getData(String categoryName, String code) throws CategoryDoesNotExistException {
        for (Category category : categories) {
            if (category.getName().equals(categoryName)) {
                return category.getData(code);
            }
        }
        throw new CategoryDoesNotExistException();
    }

    public String getWholeData() {
        String returning = "";

        returning += "USERS\n";
        for (User user : users) returning += user.toString() + "\n";

        returning += "CATEGORIES\n";
        for (Category category : categories) returning += category.toString() + "\n";

        returning += "CODES\n";
        for (Category category : categories) returning += category.getWholeData() + "\n";
        return returning;
    }

    public void load() throws IOException, CategoryDoesNotExistException, UserDoesNotExistException {
        loadData("usersData.txt", "categoriesData.txt", "codesData.txt");
    }

    public void write() throws IOException {
        writeData("usersData.txt", "categoriesData.txt", "codesData.txt");
    }

    public void loadData(String usersFileName, String categoriesFileName, String codesFileName) throws IOException, CategoryDoesNotExistException, UserDoesNotExistException {
        FileTool usersFile = new FileTool(usersFileName);
        FileTool categoriesFile = new FileTool(categoriesFileName);
        FileTool codesFile = new FileTool(codesFileName);

        categories = new ArrayList<Category>();
        users = new ArrayList<User>();

        for (String line : usersFile.getFile()) {
            users.add(new User(line));
        }
        for (String line : categoriesFile.getFile()) {
            categories.add(new Category(line));
        }
        for (String line : codesFile.getFile()) {
            ProductCode code = new ProductCode(this, line);
            code.getCategory().addCode(code);
        }
    }

    public void writeData(String usersFileName, String categoriesFileName, String codesFileName) throws IOException {
        FileTool usersFile = new FileTool(usersFileName);
        FileTool categoriesFile = new FileTool(categoriesFileName);
        FileTool codesFile = new FileTool(codesFileName);

        List<String> usersFileContent = new ArrayList<String>();
        for (User user : users) {
            usersFileContent.add(user.toString());
        };
        usersFile.setFile(usersFileContent);

        List<String> categoriesFileContent = new ArrayList<String>();
        for (Category category : categories) {
            categoriesFileContent.add(category.getName());
        }
        categoriesFile.setFile(categoriesFileContent);

        List<String> codesFileContent = new ArrayList<String>();
        for (Category category : categories) {
            codesFileContent.add(category.toString());
        }
        codesFile.setFile(codesFileContent);

        usersFile.saveFile();
        categoriesFile.saveFile();
        codesFile.saveFile();
    }
}
