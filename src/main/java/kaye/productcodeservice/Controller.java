package kaye.testor;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(path="")
@RestController
public class Controller {
    @Autowired
    kaye.testor.ProductCodesDatabase database;

    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "Hello World!";
    }

    @GetMapping("/proofcode/{category}/{code}")
    public String proofCode(@PathVariable String category, @PathVariable String code) throws CategoryDoesNotExistException {
        if (database.proofCode(category, code)) {
            return "Right!";
        } else {
            return "Wrong!";
        }
    }
}