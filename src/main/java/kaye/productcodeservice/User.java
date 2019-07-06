package kaye.testor;

public class User {
    private String name;
    private String password;
    
    public User (String name, String password) {
        this.name = name;
        this.password = password;
    }
    
    public User (String line) {
        String[] fields = line.split(";");
        name = fields[0];
        password = fields[1];
    }
    
    public String getName() {
        return name;
    }
    
    public boolean proofPassword(String password) {
        return (password.equals(this.password));
    }
}
