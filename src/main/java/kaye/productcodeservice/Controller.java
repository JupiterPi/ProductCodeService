package kaye.productcodeservice;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RequestMapping(path="")
@RestController
public class Controller {
    @GetMapping("/helloworld")
    public String getHelloWorld() {
        return "Hello World!";
    }
}