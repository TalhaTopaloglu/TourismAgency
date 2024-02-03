package view;

import business.HotelManager;
import business.PensionManager;
import core.Helper;
import entity.Hotel;
import entity.Pension;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class PensionView extends Layout {
    private JPanel container;
    private JComboBox cmb_pension_type;
    private JButton btn_add_pension;
    private JLabel lbl_info_pension;
    private JLabel lbl_header;
    private Pension pension;
    private Hotel hotel;
    private HotelManager hotelManager;
    private PensionManager pensionManager;

    public PensionView(Hotel selectedHotel) {
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.add(container);
        this.guiInitialize(300, 400);
        this.pension = pension;

        this.lbl_info_pension.setText("Seçilen otel idsi : " + selectedHotel.getId());
        this.cmb_pension_type.setModel(new DefaultComboBoxModel<>());

        for (String s : Arrays.asList(
                "Ultra Her Şey Dahil",
                "Her Şey Dahil",
                "Oda Kahvaltı",
                "Tam Pansiyon",
                "Yarım Pansiyon",
                "Sadece Yemek",
                "Alkol Hariç Full Credit")) {
            this.cmb_pension_type.addItem(s);
        }


        this.btn_add_pension.addActionListener(e -> {
            Pension pension = new Pension();
            pension.setHotelId(selectedHotel.getId());
            pension.setType( this.cmb_pension_type.getSelectedItem().toString());
            if (this.pensionManager.save(pension)) {
                Helper.showMsg("done");
                dispose();
            } else {
                Helper.showMsg("error");
            }
        });
    }

}
