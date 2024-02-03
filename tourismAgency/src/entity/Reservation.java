package entity;

import java.time.LocalDate;

public class Reservation {
    private int id;
    private int roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int totalPrice;
    private int guestCount;
    private int childGuestCount;
    private int adultGuestCount;
    private String guestName;
    private String guestMail;
    private String guestPhone;
    private String guestIdNo;
    private Hotel hotel;
    private Room room;
    private Season season;
    private Pension pension;

    public Reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getGuestCount() {
        return guestCount;
    }

    public void setGuestCount(int guestCount) {
        this.guestCount = guestCount;
    }

    public int getChildGuestCount() {
        return childGuestCount;
    }

    public void setChildGuestCount(int childGuestCount) {
        this.childGuestCount = childGuestCount;
    }

    public int getAdultGuestCount() {
        return adultGuestCount;
    }

    public void setAdultGuestCount(int adultGuestCount) {
        this.adultGuestCount = adultGuestCount;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestMail() {
        return guestMail;
    }

    public void setGuestMail(String guestMail) {
        this.guestMail = guestMail;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestIdNo() {
        return guestIdNo;
    }

    public void setGuestIdNo(String guestIdNo) {
        this.guestIdNo = guestIdNo;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public Pension getPension() {
        return pension;
    }

    public void setPension(Pension pension) {
        this.pension = pension;
    }
}
