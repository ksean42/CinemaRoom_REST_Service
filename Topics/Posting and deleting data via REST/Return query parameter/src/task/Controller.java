package task;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @PostMapping("/test")
    String getValue(@RequestParam String param){
        return param;
    }
}
