package kaye.productcodeservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;

@RequestMapping(path="")
@RestController
public class Controller {
    @Autowired
    Database database;

    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "<a href='https://github.com/JupiterPi/ProductCodeService'>Hello World</a>";
    }

    @PostMapping("/addUser/{username}/{password}")
    public void addUser(@PathVariable String username, @PathVariable String password) {
        database.addUser(username, password);
    }

    @GetMapping("/proofUser/{username}/{password}")
    public String proofUser(@PathVariable String username, @PathVariable String password) {
        if (database.proofUser(username, password)) return "Right!";
        else return "Wrong!";
    }

    @PostMapping("/addCategory/{username}/{password}/{name}")
    public String addCategory(@PathVariable String username, @PathVariable String password, @PathVariable String name) {
        if (database.proofUser(username, password)) {
            database.addCategory(name);
            return "OK";
        } else return "Wrong login!";
    }

    // TEST-DATA: timesRemaining
    @PostMapping("/addCode/{username}/{password}/{category}/{code}/{data}/{note}")
    public String addCode(@PathVariable String username, @PathVariable String password, @PathVariable String category, @PathVariable String code, @PathVariable String data, @PathVariable String note) {
        if (database.proofUser(username, password)) {
            try {
                database.addCode(category, code, data, note, 1, new Date(), database.getUser(username));
                return "OK";
            } catch (CategoryDoesNotExistException x) {
                return "Diese Kategorie gibt es nicht!";
            } catch (UserDoesNotExistException x) {
                return "Wrong login!";
            }
        } else return "Wrong login!";
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

    @GetMapping("/getData/{category}/{code}")
    public String getDate (@PathVariable String category, @PathVariable String code) {
        try {
            return database.getData(category, code);
        } catch (CategoryDoesNotExistException x) {
            return "404: Category not found from controller";
        }
    }

    @GetMapping("/getWholeData/{username}/{password}")
    public String getWholeData(@PathVariable String username, @PathVariable String password) {
        if (database.proofUser(username, password)) {
            return database.getWholeData();
        } else return "Wrong login!";
    }

    @PostMapping("/load")
    public void load() throws UserDoesNotExistException, CategoryDoesNotExistException, IOException {
        database.load();
    }

    @PostMapping("/write")
    public void write() throws IOException {
        database.write();
    }
}