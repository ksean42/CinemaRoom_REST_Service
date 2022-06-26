package cinema;

import java.util.ArrayList;
import java.util.List;

class seatObj {
    int row;
    int column;
    private int price;
    boolean purchased;

    public seatObj() {
    }

    public seatObj(int row, int column) {
        this.row = row;
        this.column = column;
        price = row <= 4 ? 10 : 8;
        purchased = false;
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

    public void setColumn(int column) {
        this.column = column;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public boolean equals(seatObj seat) {
        return (seat.row == this.row && seat.column == this.column);
    }
}

public class Seats {
    private int total_rows;
    private int total_columns;
    List<seatObj> available_seats;

    public Seats() {
        total_rows = 9;
        total_columns = 9;
        available_seats = new ArrayList<seatObj>();
        for(int i = 1; i <= total_rows; i++) {
            for(int j = 1; j <= total_columns; j++) {
                available_seats.add(new seatObj(i, j));
            }
        }
    }
    public Seats(int rows, int columns) {
        total_rows = rows;
        total_columns = columns;
        available_seats = new ArrayList<seatObj>();
        for(int i = 1; i <= total_rows; i++) {
            for(int j = 1; j <= total_columns; j++) {
                available_seats.add(new seatObj(i, j));
            }
        }
    }

    public List<seatObj> getAvailable_seats() {
        return available_seats;
    }

    public int getTotal_columns() {
        return total_columns;
    }

    public int getTotal_rows() {
        return total_rows;
    }

    public void setAvailable_seats(List<seatObj> available_seats) {
        this.available_seats = available_seats;
    }

    public void setTotal_columns(int total_columns) {
        this.total_columns = total_columns;
    }

    public void setTotal_rows(int total_rows) {
        this.total_rows = total_rows;
    }

}
