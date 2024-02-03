package entity;

import core.ComboItem;


public class Pension {
    private int id;
    private int hotelId;
    private Hotel hotel;
    private String type;

    public Pension() {
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

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public ComboItem getCombaItem(){
        return new ComboItem(this.getId(),this.getType());
    }
}
