package task;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class Controller {
    private List<String> messages = new ArrayList<>();
    @PostMapping("/message")
    public ResponseEntity<String> storeMessage(@RequestBody String message) {
        messages.add(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/message")
    @ResponseStatus(HttpStatus.OK)
    public String getMessage() {
        return messages.get(messages.size() - 1);
    }
}
