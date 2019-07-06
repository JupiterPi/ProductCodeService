package main.java.kaye.productcodeservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@RequestMapping(path="")
@RestController
public class Controller {
    @Autowired ProductCodesDatabase database;

    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "Hello World!";
    }

    @PostMapping("/addCategory/{name}")
    public void addCategory(@PathVariable String name) {
        database.addCategory(name);
    }

    @PostMapping("/addCode/{category}/{code}")
    public void addCode(@PathVariable String category, @PathVariable String code) {
        try {
            database.addCode(category, code, "test", 1, new Date(), new User("anonymous;wasweisich"));
        } catch (CategoryDoesNotExistException x) {

        }
    }

    @GetMapping("/proofCode/{category}/{code}")
    public String proofCode(@PathVariable String category, @PathVariable String code) throws CategoryDoesNotExistException {
        try {
            if (database.proofCode(category, code)) {
                return "Right!";
            } else {
                return "Wrong!";
            }
        } catch (CategoryDoesNotExistException x) {
            return "Diese Kategorie gibt es nicht!";
        }
    }
}