package calculator;

import org.springframework.web.bind.annotation.*;

@RestController
class ArithmeticRestController {
    public String calculate(String operation, int a, int b) {
        if(operation.equals("add")) {
            return String.valueOf(a + b);
        } else if (operation.equals("subtract")) {
            return String.valueOf(a - b);
        } else if (operation.equals("mult")) {
            return String.valueOf(a * b);
        }
        return "Unknown operation";
    }

    @GetMapping("/{operation}")
    public String add(@PathVariable String operation, @RequestParam int a, @RequestParam int b) {
        return calculate(operation, a , b);
    }
}