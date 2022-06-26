package cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class Controller {
    private final Seats seats = new Seats(9, 9);

    @GetMapping("/seats")
    public Seats getSeatsInfo() {
        return seats;
    }

    @PostMapping("/purchase")
    public ResponseEntity purchase(@RequestBody seatObj seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        Map<String, Integer> resp = new LinkedHashMap<>();
        if(row <= 0 || row > seats.getTotal_rows()
                || column <= 0 || column > seats.getTotal_columns()) {
            return new ResponseEntity<>(Map.of("error", "The number of a row or a column is out of bounds!"),
                    HttpStatus.valueOf(400));
        }
        for(seatObj s : seats.available_seats) {
            if(s.equals(seat)){
                if(s.purchased) {
                    return new ResponseEntity<>(Map.of("error", "The ticket has been already purchased!"), HttpStatus.BAD_REQUEST);
                }
                resp.put("row", row);
                resp.put("column", column);
                resp.put("price", s.getPrice());
                s.purchased = true;
                return new ResponseEntity(resp, HttpStatus.OK);
            }
        }
        return new ResponseEntity(resp, HttpStatus.OK);

    }
}
