package com.cinemaroom.cinema;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public class Statistics {
    @JsonProperty("current_income")
    int currentIncome;
    @JsonProperty("number_of_available_seats")
    int numberOfAvailableSeats;
    @JsonProperty("number_of_purchased_tickets")
    int numberOfPurchasedTickets;

    public Statistics(Cinema cinema) {
        currentIncome = getCurrentIncome(cinema.getAvailable_seats());
        numberOfAvailableSeats = getNumberOfAvailableSeats(cinema.getAvailable_seats());
        numberOfPurchasedTickets = cinema.getAvailable_seats().size() - numberOfAvailableSeats;
    }

    private int getCurrentIncome(List<Seat> seatList) {
        int income = 0;
        for(Seat s : seatList) {
                if(s.purchased) {
                    income += s.getPrice();
                }
            }
        return income;
        }

        private int getNumberOfAvailableSeats(List<Seat> seatList) {
        int numberOfSeats = 0;
        for(Seat s : seatList) {
            if(!s.purchased) {
                numberOfSeats += 1;
            }
        }
        return numberOfSeats;
    }
}
