package view;

import business.HotelManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;

import javax.swing.*;
import java.util.Arrays;

public class HotelView extends Layout {
    private JPanel container;
    private JTextField fld_hotel_name;
    private JTextField fld_hotel_address;
    private JTextField fld_hotel_mail;
    private JTextField fld_hotel_phone;
    private JCheckBox chck_car_park;
    private JCheckBox chck_wifi;
    private JCheckBox chck_pool;
    private JCheckBox chck_fitness;
    private JCheckBox chck_concierge;
    private JCheckBox chck_spa;
    private JCheckBox chck_room_service;
    private JButton btn_hotel_add;
    private JLabel lbl_hotel_add_info;
    private JComboBox cmb_star_number;
    private Hotel hotel;
    private HotelManager hotelManager;

    public HotelView(Hotel hotel){
        this.hotel = hotel;
        this.hotelManager = new HotelManager();
        this.add(container);

        guiInitialize(400,500);

        //lbl_hotel_add_info.setText("Seçilen Otel : " + this.hotel.getId());


        this.cmb_star_number.setModel(new DefaultComboBoxModel<>());

        for (String s : Arrays.asList("*", "* ", "* * *", "* * * * ", "* * * * *", "* * * * * *", "* * * * * * *")) {
            this.cmb_star_number.addItem(s);
        }


        if(this.hotel.getId() != 0){
            this.cmb_star_number.getModel().setSelectedItem(this.hotel.getStar());
        }

        btn_hotel_add.addActionListener(e -> {
            if(Helper.isFieldListEmpty(
            new JTextField[]{this.fld_hotel_address, this.fld_hotel_mail, this.fld_hotel_name, this.fld_hotel_phone})){
                Helper.showMsg("fill");
            } else {
                boolean result;
                this.hotel.setName(fld_hotel_name.getText());
                this.hotel.setAddress(fld_hotel_address.getText());
                this.hotel.setMail(fld_hotel_mail.getText());
                this.hotel.setPhoneNumber(fld_hotel_phone.getText());
                this.hotel.setStar(cmb_star_number.getSelectedItem().toString());
                this.hotel.setCarPark(chck_car_park.isSelected());
                this.hotel.setWifi(chck_wifi.isSelected());
                this.hotel.setPool(chck_pool.isSelected());
                this.hotel.setFitness(chck_fitness.isSelected());
                this.hotel.setConcierge(chck_concierge.isSelected());
                this.hotel.setRoomService(chck_room_service.isSelected());
                if(this.hotel.getId() != 0 ){
                    result = this.hotelManager.update(this.hotel);
                } else {
                    result = this.hotelManager.save(this.hotel);
                }
                if(result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }


}
