package view;

import business.HotelManager;
import business.PensionManager;
import business.RoomManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Room;
import entity.Season;

import javax.swing.*;
import java.util.Arrays;

public class RoomView extends Layout {
    private JPanel container;
    private JComboBox cmb_hotel;
    private JComboBox cmb_pension;
    private JComboBox cmb_season;
    private JComboBox cmb_room_type;
    private JTextField fld_stock;
    private JTextField fld_adult_child;
    private JTextField fld_child_price;
    private JTextField fld_bed_capacity;
    private JCheckBox chck_tv;
    private JCheckBox chck_minibar;
    private JCheckBox chck_game_console;
    private JCheckBox chck_cash_box;
    private JCheckBox chck_projection;
    private JTextField fld_square;
    private JButton btn_add_room;
    private Room room;
    private RoomManager roomManager;
    private HotelManager hotelManager;
    private SeasonManager seasonManager;
    private PensionManager pensionManager;

    public RoomView(Room room) {
        this.room = room;
        this.roomManager = new RoomManager();
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.pensionManager = new PensionManager();
        this.add(container);

        guiInitialize(500, 500);

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel.addItem(hotel.getCombaItem());
        }


        // Oda ekleme kısmında Otele göre dinamik değişen comboBox listener'ı

        this.cmb_hotel.addActionListener(e -> {
            this.cmb_pension.removeAllItems();
            this.cmb_season.removeAllItems();

            int selectedHotelId = ((ComboItem) this.cmb_hotel.getSelectedItem()).getKey();

            for (Season season : this.seasonManager.findAll()) {
                if(season.getHotelId() == selectedHotelId){
                    this.cmb_season.addItem(season.getCombaItem());
                }
            }
            for (Pension pension : this.pensionManager.findAll()) {
                if(pension.getHotelId() == selectedHotelId){
                    this.cmb_pension.addItem(pension.getCombaItem());
                }
            }
        });


        for (String s : Arrays.asList(
                //"",
                "Tek Kişilik",
                "Çift Kişilik",
                "Junior Suit",
                "Suit")) {
            this.cmb_room_type.addItem(s);
        }


        btn_add_room.addActionListener(e -> {
            if (Helper.isFieldListEmpty(
                    new JTextField[]{this.fld_adult_child, this.fld_child_price, this.fld_square, this.fld_stock, this.fld_bed_capacity})) {
                Helper.showMsg("fill");
            } else {
                boolean result;
                ComboItem selectedHotel = (ComboItem) this.cmb_hotel.getSelectedItem();
                ComboItem selectedSeason = (ComboItem) this.cmb_season.getSelectedItem();
                ComboItem selectedPension = (ComboItem) this.cmb_pension.getSelectedItem();
                this.room.setHotelId(selectedHotel.getKey());
                this.room.setPensionId(selectedPension.getKey());
                this.room.setSeasonId(selectedSeason.getKey());
                this.room.setType(cmb_room_type.getSelectedItem().toString());
                this.room.setStock(Integer.parseInt(fld_stock.getText()));
                this.room.setAdultPrice(fld_adult_child.getText());
                this.room.setChildPrice(fld_child_price.getText());
                this.room.setBedCapacity(fld_bed_capacity.getText());
                this.room.setSquare(fld_square.getText());
                this.room.setTelevision(chck_tv.isSelected());
                this.room.setMinibar(chck_minibar.isSelected());
                this.room.setGameConsole(chck_game_console.isSelected());
                this.room.setCashBox(chck_cash_box.isSelected());
                this.room.setProjection(chck_projection.isSelected());
                if (this.room.getId() != 0) {
                    result = this.roomManager.update(this.room);
                } else {
                    result = this.roomManager.save(this.room);
                }
                if (result) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }

}
