package kaye.productcodeservice;

import main.java.kaye.productcodeservice.CategoryDoesNotExistException;
import main.java.kaye.productcodeservice.ProductCodesDatabase;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(path="")
@RestController
public class Controller {
    @Autowired ProductCodesDatabase database;

    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "Hello World!";
    }

    @GetMapping("/proofcode/{category}/{code}")
    public String proofCode(@PathVariable String category, @PathVariable String code) {
        try {
            if (database.proofCode(category, code)) {
                return "Right!";
            } else {
                return "Wrong!";
            }
        } catch (CategoryDoesNotExistException x) {
            return "Category doesn't exist!";
        }
    }
}