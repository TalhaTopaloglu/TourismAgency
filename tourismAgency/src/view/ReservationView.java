package view;

import business.ReservationManager;
import core.Helper;
import entity.Reservation;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReservationView extends Layout {

    private JPanel container;
    private JLabel lbl_info;
    private JTextField fld_guest_name;
    private JTextField fld_guest_phone;
    private JTextField fld_guest_idno;
    private JTextField fld_guest_mail;
    private JTextField fld_check_in_date;
    private JTextField fld_chcek_out_date;
    private JTextField fld_total_guest;
    private JTextField fld_total_price;
    private JButton btn_save;
    private Reservation reservation;
    private ReservationManager reservationManager;

    ReservationView(Reservation reservation) {
        this.reservation = reservation;
        this.reservationManager = new ReservationManager();
        this.add(container);
        guiInitialize(500, 500);

        this.fld_guest_name.setText(this.reservation.getGuestName());
        this.fld_guest_phone.setText(this.reservation.getGuestPhone());
        this.fld_guest_idno.setText(this.reservation.getGuestIdNo());
        this.fld_guest_mail.setText(this.reservation.getGuestMail());
        this.fld_total_guest.setText(String.valueOf(this.reservation.getGuestCount()));
        this.fld_check_in_date.setText(String.valueOf((this.reservation.getCheckInDate())));
        this.fld_chcek_out_date.setText(String.valueOf((this.reservation.getCheckOutDate())));
        this.fld_total_price.setText(String.valueOf(this.reservation.getTotalPrice()));


        btn_save.addActionListener(e -> {
                JTextField[] checkFieldList = {
                        this.fld_guest_name,
                        this.fld_guest_idno,
                        this.fld_guest_phone,
                        this.fld_guest_mail
                };
            if(Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");
            }else{
                boolean result;
                reservation.setGuestName(this.fld_guest_name.getText());
                reservation.setGuestPhone(this.fld_guest_phone.getText());
                reservation.setGuestIdNo(this.fld_guest_idno.getText());
                reservation.setGuestMail(this.fld_guest_mail.getText());
                if(this.reservation.getId() != 0){
                    result = this.reservationManager.update(this.reservation);
                    Helper.showMsg("done");
                    dispose();
                }else{
                    result = false;
                }
            }
        });
    }
}
