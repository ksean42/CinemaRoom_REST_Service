package task;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.*;

@RestController
public class Controller {
    @GetMapping("/test/{status}")
    public ResponseEntity<String> getStatus(@PathVariable int status) {
        return new ResponseEntity<>(HttpStatus.valueOf(status));
    }
}
