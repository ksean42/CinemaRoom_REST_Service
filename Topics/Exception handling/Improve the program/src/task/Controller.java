package task;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String cause) {
        super(cause);
    }
}

@RestController
public class Controller {
    final ConcurrentMap<Long, String> items = new ConcurrentHashMap<>(Map.of(
            535L, "Chair",
            99534533L, "Table",
            343455L, "Vase"
    ));

    @GetMapping("/items/{id}")
    String getItem(@PathVariable long id) {
        if(!items.getOrDefault(id, "-1").equals("-1")) {
            return items.get(id);
        }
        throw new ItemNotFoundException("Item not found!");
    }
}
