package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel container;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_finish_date;
    private JLabel fld_hotel_info;
    private JButton btn_season_add;
    private Season season;
    private Hotel hotel;
    private SeasonManager seasonManager;
    private HotelManager hotelManager;

    public SeasonView(Hotel selectedHotel) {
        this.hotelManager = new HotelManager();
        this.seasonManager = new SeasonManager();
        this.add(container);
        this.guiInitialize(300, 400);
        this.season = season;

        this.fld_hotel_info.setText("Seçilen Otel Adı : " + selectedHotel.getName());

        this.btn_season_add.addActionListener(e -> {
            if (Helper.isFieldListEmpty(new JTextField[]{this.fld_finish_date, this.fld_start_date})) {
                Helper.showMsg("fill");
            } else {
                Season season = new Season();
                season.setHotelId(selectedHotel.getId());
                season.setStartDate(LocalDate.parse(this.fld_start_date.getText(), DateTimeFormatter.ofPattern("y-M-d")));
                season.setFinishDate(LocalDate.parse(this.fld_finish_date.getText(), DateTimeFormatter.ofPattern("y-M-d")));
                if (this.seasonManager.save(season)) {
                    Helper.showMsg("done");
                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
    private void createUIComponents() throws ParseException {

        this.fld_start_date = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_start_date.setText("2024-01-01");

        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_finish_date.setText("2024-06-01");
    }
}
