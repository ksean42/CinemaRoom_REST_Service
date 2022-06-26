package task;

import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@RestController
public class Controller {
    private List<String> users = new ArrayList<>();

    @GetMapping("/users")
    public List<String> getUsers() {
        return users;
    }
    @PostMapping("/users")
    public void addUser(@RequestParam String name){
        users.add(name);
    }

}
