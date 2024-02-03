package view;

import business.*;
import core.Helper;
import entity.Reservation;
import entity.Room;

import javax.swing.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ReservationAddView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_star;
    private JCheckBox chck_spa;
    private JCheckBox chck_room_service;
    private JCheckBox chck_concierge;
    private JCheckBox chck_fitness;
    private JCheckBox chck_pool;
    private JCheckBox chck_wifi;
    private JCheckBox chck_car_park;
    private JTextField fld_room_type;
    private JTextField fld_pension_type;
    private JTextField fld_check_in_date;
    private JTextField fld_check_out_date;
    private JTextField fld_bed_capacity;
    private JTextField fld_square;
    private JCheckBox chck_tv;
    private JCheckBox chck_game_console;
    private JCheckBox chck_minibar;
    private JCheckBox chck_projection;
    private JCheckBox chck_cash_box;
    private JTextField fld_total_guest;
    private JTextField fld_guest_mail;
    private JTextField fld_guest_name;
    private JTextField fld_guest_phone;
    private JButton btn_add_reservation;
    private JTextField fld_guest_idno;
    private JTextField fld_total_price;
    private Room room;
    private ReservationManager reservationManager;
    private RoomView roomView;
    private EmployeeView employeeView;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;


    public ReservationAddView(Room room, JFormattedTextField startDate, JFormattedTextField finishDate, JTextField childNumber, JTextField adultNumber){

        this.room = room;
        this.reservationManager = new ReservationManager();
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        guiInitialize(900,800);

        int totalGuestNumber = Integer.parseInt(childNumber.getText()) + Integer.parseInt(adultNumber.getText());
        String strGuestNumber = String.valueOf(totalGuestNumber);
        int childPrice = Integer.parseInt(this.room.getChildPrice());
        int adultPrice = Integer.parseInt(this.room.getAdultPrice());

        LocalDate from = LocalDate.parse(startDate.getText(), DateTimeFormatter.ofPattern("y-M-d"));
        LocalDate to = LocalDate.parse(finishDate.getText(), DateTimeFormatter.ofPattern("y-M-d"));

        long daysBetween = ChronoUnit.DAYS.between(from, to);

        long totalPrice = daysBetween * ((childPrice * Integer.parseInt(childNumber.getText())) + (adultPrice * Integer.parseInt(adultNumber.getText())));
        System.out.println(totalPrice);

        String strTotalPrice = String.valueOf(totalPrice);


        // rooma ait özellikler
        this.fld_hotel_name.setText(this.room.getHotel().getName());
        this.fld_hotel_address.setText(this.room.getHotel().getAddress());
        this.fld_hotel_star.setText(this.room.getHotel().getStar());
        this.chck_car_park.setSelected(this.room.getHotel().isCarPark());
        this.chck_wifi.setSelected(this.room.getHotel().isWifi());
        this.chck_pool.setSelected(this.room.getHotel().isPool());
        this.chck_fitness.setSelected(this.room.getHotel().isFitness());
        this.chck_concierge.setSelected(this.room.getHotel().isConcierge());
        this.chck_spa.setSelected(this.room.getHotel().isSpa());
        this.chck_room_service.setSelected(this.room.getHotel().isRoomService());
        this.fld_room_type.setText(this.room.getType());
        this.fld_pension_type.setText(this.room.getPension().getType());
        this.fld_bed_capacity.setText(this.room.getBedCapacity());
        this.fld_square.setText(this.room.getSquare());
        this.chck_tv.setSelected(this.room.isTelevision());
        this.chck_game_console.setSelected(this.room.isGameConsole());
        this.chck_minibar.setSelected(this.room.isMinibar());
        this.chck_cash_box.setSelected(this.room.isCashBox());
        this.chck_projection.setSelected(this.room.isProjection());
        this.fld_check_in_date.setText(startDate.getText());
        this.fld_check_out_date.setText(finishDate.getText());
        this.fld_total_guest.setText(strGuestNumber);
        this.fld_total_price.setText(strTotalPrice);

        btn_add_reservation.addActionListener(e -> {
            if (Helper.isFieldListEmpty(
                    new JTextField[]{this.fld_check_in_date, this.fld_check_out_date, this.fld_total_guest, this.fld_guest_idno, this.fld_guest_mail,this.fld_guest_name,fld_guest_phone})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                Reservation reservation = new Reservation();
                reservation.setRoomId(this.room.getId());
                reservation.setGuestName(this.fld_guest_name.getText());
                reservation.setGuestMail(this.fld_guest_mail.getText());
                reservation.setGuestPhone(this.fld_guest_phone.getText());
                reservation.setGuestIdNo(this.fld_guest_idno.getText());
                reservation.setCheckInDate(LocalDate.parse(startDate.getText(), DateTimeFormatter.ofPattern("y-M-d")));
                reservation.setCheckOutDate(LocalDate.parse(finishDate.getText(), DateTimeFormatter.ofPattern("y-M-d")));
                reservation.setTotalPrice((int) totalPrice);
                reservation.setGuestCount(totalGuestNumber);
                if(this.room.getStock() <= 0){
                    Helper.showMsg("Rezervasyon yaptığınız odanın stoğu bulunmamaktadır !");
                    result = false;
                }else{
                result = this.reservationManager.save(reservation);
                }
//                if(reservation.getId() != 0) {
//                    result = this.reservationManager.update(reservation);
//                }
                if(result) {
                    Helper.showMsg("done");
                    dispose();
                }else{
                    Helper.showMsg("error");
                }
            }
        });
    }
}
