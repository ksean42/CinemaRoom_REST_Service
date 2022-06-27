package cinema;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.sql.Timestamp;
import java.util.*;

class Seat {
    private int row;
    private int column;
    private int price;
    @JsonIgnore
    boolean purchased;
    @JsonIgnore
    private UUID accessToken;
    private Timestamp tokenCreationTime;
    public Seat() {
    }

    public Seat(int row, int column) {
        this.row = row;
        this.column = column;
        price = row <= 4 ? 10 : 8;
        purchased = false;
        this.accessToken = null;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public int getPrice() {
        return price;
    }

    @JsonIgnore
    public boolean tokenExpired() {
        Timestamp currTime = new Timestamp(System.currentTimeMillis());
        long time = currTime.getTime() - this.tokenCreationTime.getTime();
        time = time/1000;
        if(time > 3600)
            return true;
        else
            return false;
    }
    public UUID getAccessToken() {
            return accessToken;
    }

    public void setAccessToken() {
        if (this.accessToken == null) {
            this.accessToken = UUID.randomUUID();
            this.tokenCreationTime = new Timestamp(System.currentTimeMillis());
        }
    }
    public void resetAccessToken() {
        this.accessToken = null;
    }

    public boolean equals(Seat seat) {
        return (seat.row == this.row && seat.column == this.column);
    }
}

public class Cinema {
    private int total_rows;
    private int total_columns;
    List<Seat> available_seats;

    public Cinema() {
        total_rows = 9;
        total_columns = 9;
        available_seats = new ArrayList<>();
        initSeats();
    }
    public Cinema(int rows, int columns) {
        total_rows = rows;
        total_columns = columns;
        available_seats = new ArrayList<>();
        initSeats();
    }
    public void initSeats() {
        for(int i = 1; i <= total_rows; i++) {
            for(int j = 1; j <= total_columns; j++) {
                available_seats.add(new Seat(i, j));
            }
        }
    }

    public List<Seat> getAvailable_seats() {
        return available_seats;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public int getTotal_rows() {
        return total_rows;
    }

}
