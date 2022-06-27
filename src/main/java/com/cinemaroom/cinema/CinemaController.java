package com.cinemaroom.cinema;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
public class CinemaController {
    private final Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema getSeatsInfo() {
        return cinema;
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody Seat seat) {
        int row = seat.getRow();
        int column = seat.getColumn();
        Map<String, Object> resp = new LinkedHashMap<>();
        if(row <= 0 || row > cinema.getTotal_rows()
                || column <= 0 || column > cinema.getTotal_columns()) {
            return new ResponseEntity<>(Map.of("error", Error.OUT_OF_BOUNDS.toString()),
                    HttpStatus.BAD_REQUEST);
        }

        for(Seat s : cinema.available_seats) {
            if(s.equals(seat)){
                if(s.purchased) {
                    return new ResponseEntity<>(Map.of("error", Error.NOT_AVAILABLE_TICKET.toString()), HttpStatus.BAD_REQUEST);
                }
                s.setAccessToken();
                resp.put("token", s.getAccessToken());
                resp.put("ticket", s);
                s.purchased = true;
                return new ResponseEntity<>(resp, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @PostMapping("/return")
    public ResponseEntity<?> getTicket(@RequestBody Map<String, String> request) {
        UUID token;

        try {
            token = UUID.fromString(request.get("token"));
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(Map.of("error", Error.WRONG_TOKEN.toString()), HttpStatus.BAD_REQUEST);
        }

        for(Seat s : cinema.available_seats) {
            if(token.equals(s.getAccessToken()) && !s.tokenExpired()) {
                s.purchased = false;
                s.resetAccessToken();
                return new ResponseEntity<>(Map.of("returned_ticket", s), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(Map.of("error", Error.WRONG_TOKEN.toString()), HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> getStats(@RequestParam(required = false) String password) {
        if(password != null && password.equals("super_secret")) {
            return new ResponseEntity<>(new Statistics(cinema), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("error", Error.WRONG_PASSWORD.toString()), HttpStatus.UNAUTHORIZED);
        }


    }
}
/*TODO: Use threadsafe objects, e.g.
    import java.util.concurrent.ConcurrentHashMap;
    import java.util.concurrent.ConcurrentMap;

    new entity for ticket?
    available_seats
*/
