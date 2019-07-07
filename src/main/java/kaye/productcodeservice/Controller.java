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
        return "<a href='https://github.com/JupiterPi/ProductCodeService'>Hello World</a>";
    }

    @PostMapping("/addCategory/{name}")
    public String addCategory(@PathVariable String name) {
        database.addCategory(name);
        return "OK";
    }

    @PostMapping("/addCode/{category}/{code}")
    public String addCode(@PathVariable String category, @PathVariable String code) {
        try {
            database.addCode(category, code, "test", 1, new Date(), new User("anonymous;wasweisich"));
            return "OK";
        } catch (CategoryDoesNotExistException x) {
            return "Diese Kategorie gibt es nicht!";
        }
    }

    @GetMapping("/proofCode/{category}/{code}")
    public String proofCode(@PathVariable String category, @PathVariable String code) {
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