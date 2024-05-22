package pl.pollub.ISbackend;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class co {
    @GetMapping("/dupa")
    public String dupa(){
        return "dupa";
    }
}
