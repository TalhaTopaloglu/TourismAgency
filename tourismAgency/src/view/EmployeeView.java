package view;

import business.*;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private final HotelManager hotelManager;
    private final PensionManager pensionManager;
    private final SeasonManager seasonManager;
    private final RoomManager roomManager;
    private final ReservationManager reservationManager;
    private UserManager userManager;
    private DefaultTableModel tmbl_hotel = new DefaultTableModel();
    private DefaultTableModel tmbl_pension = new DefaultTableModel();
    private DefaultTableModel tmbl_season = new DefaultTableModel();
    private DefaultTableModel tmbl_room = new DefaultTableModel();
    private DefaultTableModel tmbl_reservation = new DefaultTableModel();
    private JPopupMenu hotel_menu;
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private JPanel container;
    private JTable tbl_hotel;
    private JLabel lbl_welcome;
    private JPanel pnl_top;
    private JTabbedPane tab_menu;
    private JButton btn_logout;
    private JPanel pnl_hotel;
    private JScrollPane scrl_hotel;
    private JTable tbl_pension;
    private JTable tbl_season;
    private JButton btn_add_hotel;
    private JTable tbl_room;
    private JTextField fld_hotel_name;
    private JButton btn_room_filter;
    private JButton btn_room_add;
    private JButton resetButton;
    private JFormattedTextField fld_start_date;
    private JFormattedTextField fld_finish_date;
    private JTextField fld_address;
    private JTextField fld_adult_number;
    private JTextField fld_child_number;
    private JTable tbl_reservations;
    private User user;
    private Object[] col_hotel;
    private Object[] col_season;
    private Object[] col_pension;
    private Object[] col_room;
    private Object[] col_reservation;

    public EmployeeView(User user) {
        this.userManager = new UserManager();
        this.hotelManager = new HotelManager();
        this.pensionManager = new PensionManager();
        this.seasonManager = new SeasonManager();
        this.roomManager = new RoomManager();
        this.reservationManager = new ReservationManager();
        this.add(container);
        this.guiInitialize(1200, 600);
        this.user = user;


        this.lbl_welcome.setText("Hoşgeldiniz : " + this.user.getUsername() + " || Employee olarak giriş yaptınız.");

        loadReservationTable(null);
        loadReservationComponent();

        loadHotelTable(null);
        loadHotelComponent();

        loadRoomTable(null);
        loadRoomComponent();


        tbl_hotel.getSelectionModel().addListSelectionListener(e -> {
            try {
                int id;
                String selectHotelId = tbl_hotel.getValueAt(tbl_hotel.getSelectedRow(), 0).toString();
                id = Integer.parseInt(selectHotelId);
                loadPensionTable(null, id);
                loadSeasonTable(null, id);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }

        });

        btn_room_add.addActionListener(e -> {
            RoomView roomView = new RoomView(new Room());
            roomView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                }
            });
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadRoomTable(null);
            }
        });

        btn_logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }


    public void loadRoomTable(ArrayList<Object[]> roomList) {
        this.col_room = new Object[]{"ID", "Otel Adı", "Pansiyon", "Oda Tipi", "Stok", "Yetişkin Fiyatı", "Çocuk Fiyatı", "Yatak Kapasitesi", "m2", "Tv", "Minibar", "Konsol", "Kasa", "Projeksiyon"};
        if (roomList == null) {
            roomList = this.roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        this.createTable(this.tmbl_room, this.tbl_room, col_room, roomList);
    }

    public void loadHotelTable(ArrayList<Object[]> hotelList) {

        this.col_hotel = new Object[]{"ID", "Name", "Address", "Mail", "Phone", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Service"};

        if (hotelList == null) {
            hotelList = this.hotelManager.getForTable(col_hotel.length, this.hotelManager.findAll());
        }
        this.createTable(this.tmbl_hotel, this.tbl_hotel, col_hotel, hotelList);
    }

    public void loadSeasonTable(ArrayList<Object[]> seasonList, int id) {

        this.col_season = new Object[]{"ID", "Otel Id", "Başlangıç Tarihi", "Bitiş Tarihi"};
        ArrayList<Season> seasonGetId = new ArrayList<>();
        for (Season obj : this.seasonManager.findAll()) {
            if (obj.getHotelId() == id) {
                seasonGetId.add(obj);
            }
        }

        if (seasonList == null) {
            seasonList = this.seasonManager.getForTable(col_season.length, seasonGetId);
        }


        System.out.println("122. satır -> " + id);
        this.createTable(this.tmbl_season, this.tbl_season, col_season, seasonList);
    }

    public void loadPensionTable(ArrayList<Object[]> pensionList, int id) {

        this.col_pension = new Object[]{"Id", "Otel Id", "Pansiyon Tipi"};

        ArrayList<Pension> pensionGetId = new ArrayList<>();
        for (Pension obj : this.pensionManager.findAll()) {
            if (obj.getHotelId() == id) {
                pensionGetId.add(obj);
            }
        }

        if (pensionList == null) {
            pensionList = this.pensionManager.getForTable(col_pension.length, pensionGetId);
        }

        this.createTable(this.tmbl_pension, this.tbl_pension, col_pension, pensionList);
    }

    public void loadHotelComponent() {
        tableRowSelect(this.tbl_hotel);
        this.hotel_menu = new JPopupMenu();
        this.hotel_menu.add("Sezon Ekle").addActionListener(e -> {
            int selectedHotel = this.getTableSelectedRow(tbl_hotel, 0);
            SeasonView seasonView = new SeasonView(this.hotelManager.getById(selectedHotel));
            seasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable(null, selectedHotel);
                    loadRoomTable(null);
                }
            });
        });
        this.hotel_menu.add("Pansiyon Ekle").addActionListener(e -> {
            int selectedHotel = this.getTableSelectedRow(tbl_hotel, 0);
            PensionView pensionView = new PensionView(this.hotelManager.getById(selectedHotel));
            pensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadPensionTable(null, selectedHotel);
                    loadRoomTable(null);
                }
            });

        });
        btn_add_hotel.addActionListener(e -> {
            HotelView hotelView = new HotelView(new Hotel());
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable(null);
                    loadRoomTable(null);
                }
            });
        });


        this.tbl_hotel.setComponentPopupMenu(hotel_menu);
    }

    public void loadRoomComponent() {
        tableRowSelect(this.tbl_room);
        this.room_menu = new JPopupMenu();
        this.room_menu.add("Rezervasyon Ekle").addActionListener(e -> {
            int selectedRoom = this.getTableSelectedRow(tbl_room, 0);
            ReservationAddView reservationAddView = new ReservationAddView(this.roomManager.getById(selectedRoom), this.fld_start_date, this.fld_finish_date, this.fld_child_number, this.fld_adult_number);
                this.roomManager.decreaseStock(selectedRoom);
            reservationAddView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadReservationTable(null);
                    loadRoomTable(null);
                }
            });
        });
        this.btn_room_filter.addActionListener(e -> {
            ArrayList<Room> roomList = this.roomManager.filterRoom(fld_start_date.getText(), fld_finish_date.getText(), fld_hotel_name.getText(), fld_address.getText(), Integer.parseInt(fld_adult_number.getText()), Integer.parseInt(fld_child_number.getText()));
            ArrayList<Object[]> roomFilterRow = this.roomManager.getForTable(this.col_room.length, roomList);
            loadRoomTable(roomFilterRow);
        });

        this.tbl_room.setComponentPopupMenu(room_menu);

    }

    public void loadReservationComponent() {
        tableRowSelect(this.tbl_reservations);
        this.reservation_menu = new JPopupMenu();
        this.reservation_menu.add("Rezervasyon İptal Et").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectedReservation = this.getTableSelectedRow(tbl_reservations, 0);
                this.roomManager.increaseStock(this.reservationManager.getById(selectedReservation).getRoomId());
                if (this.reservationManager.delete(selectedReservation)) {
                    Helper.showMsg("done");
                    loadReservationTable(null);
                    loadRoomTable(null);
                }
            }
        });
        this.reservation_menu.add("Rezervasyonu Düzenle").addActionListener(e -> {
            int selectedReservation = this.getTableSelectedRow(tbl_reservations, 0);
            ReservationView reservationView = new ReservationView(this.reservationManager.getById(selectedReservation));
            reservationView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadRoomTable(null);
                    loadReservationTable(null);
                }
            });
        });

        this.tbl_reservations.setComponentPopupMenu(reservation_menu);
    }

    public void loadReservationTable(ArrayList<Object[]> reservationList) {
        this.col_reservation = new Object[]{"ID", "Oda ID", "Giriş Tarihi", "Çıkış Tarihi", "Toplam Tutar", "Misafir Sayısı", "Misafir Adı", "Misafir Kimlik No", "Mail", "Telefon"};
        if (reservationList == null) {
            reservationList = this.reservationManager.getForTable(col_reservation.length, this.reservationManager.findAll());
        }
        this.createTable(this.tmbl_reservation, this.tbl_reservations, col_reservation, reservationList);
    }


    private void createUIComponents() throws ParseException {

        this.fld_start_date = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_start_date.setText("2024-01-01");

        this.fld_finish_date = new JFormattedTextField(new MaskFormatter("####-##-##"));
        this.fld_finish_date.setText("2024-06-01");
    }

}
