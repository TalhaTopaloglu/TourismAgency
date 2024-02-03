package dao;

import core.Db;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservationDao {
    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();
    private final RoomDao roomDao = new RoomDao();
    private final PensionDao pensionDao = new PensionDao();
    private final SeasonDao seasonDao = new SeasonDao();


    public ReservationDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY id ASC");
    }

    public ArrayList<Reservation> selectByQuery(String query) {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationList;
    }

    public boolean save(Reservation reservation) {

        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id," +
                "check_in_date," +
                "check_out_date," +
                "total_price," +
                "guest_count," +
                "guest_name," +
                "guest_mail," +
                "guest_phone," +
                "guest_idno" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, reservation.getRoomId());
            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
            pr.setInt(4, reservation.getTotalPrice());
            pr.setInt(5, reservation.getGuestCount());
            pr.setString(6,reservation.getGuestName());
            pr.setString(7,reservation.getGuestMail());
            pr.setString(8,reservation.getGuestPhone());
            pr.setString(9,reservation.getGuestIdNo());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;

    }



//    public boolean update(Reservation reservation) {
//        String query = "UPDATE public.reservation SET " +
//                "room_id = ? , " +
//                "check_in_date = ? , " +
//                "check_out_date = ? , " +
//                "total_price = ? , " +
//                "guest_count = ? , " +
//                "guest_name = ? , " +
//                "guest_mail = ? , " +
//                "guest_phone = ?, " +
//                "guest_idno = ?, ";
//
//        try {
//            PreparedStatement pr = this.con.prepareStatement(query);
//            pr.setInt(1, reservation.getId());
//            pr.setDate(2, Date.valueOf(reservation.getCheckInDate()));
//            pr.setDate(3, Date.valueOf(reservation.getCheckOutDate()));
//            pr.setInt(4, reservation.getTotalPrice());
//            pr.setInt(5, reservation.getGuestCount());
//            pr.setString(6,reservation.getGuestName());
//            pr.setString(7,reservation.getGuestMail());
//            pr.setString(8,reservation.getGuestPhone());
//            return pr.executeUpdate() != -1;
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//        return true;
//    }

    public boolean update(Reservation reservation) {
        String query = "UPDATE public.reservation SET " +
                "guest_name = ? , " +
                "guest_mail = ? , " +
                "guest_phone = ?, " +
                "guest_idno = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,reservation.getGuestName());
            pr.setString(2,reservation.getGuestMail());
            pr.setString(3,reservation.getGuestPhone());
            pr.setString(4, reservation.getGuestIdNo());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }




    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public Reservation match(ResultSet rs) throws SQLException {

        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setRoomId(rs.getInt("room_id"));
        obj.setCheckInDate(LocalDate.parse(rs.getString("check_in_date")));
        obj.setCheckOutDate(LocalDate.parse(rs.getString("check_out_date")));
        obj.setTotalPrice(rs.getInt("total_price"));
        obj.setGuestCount(rs.getInt("guest_count"));
        obj.setGuestName(rs.getString("guest_name"));
        obj.setGuestMail(rs.getString("guest_mail"));
        obj.setGuestPhone(rs.getString("guest_phone"));
        obj.setGuestIdNo(rs.getString("guest_idno"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("id")));
        obj.setRoom(this.roomDao.getById(rs.getInt("id")));
        obj.setSeason(this.seasonDao.getById(rs.getInt("id")));
        obj.setPension(this.pensionDao.getById(rs.getInt("id")));

        return obj;
    }

    public boolean delete(int id) {
        String query = "DELETE FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }
}
