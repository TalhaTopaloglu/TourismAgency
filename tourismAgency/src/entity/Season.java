package entity;

import core.ComboItem;

import java.time.LocalDate;
import java.util.Date;

public class Season {
    private int id;
    private int hotelId;
    private Hotel hotel;
    private LocalDate startDate;
    private LocalDate finishDate;

    public Season() {;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDate finishDate) {
        this.finishDate = finishDate;
    }

    public ComboItem getCombaItem(){
        return new ComboItem(this.getId(),this.getStartDate().toString() + " - " +this.getFinishDate().toString());
    }

}
