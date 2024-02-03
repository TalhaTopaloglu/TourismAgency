package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {

    private final Connection con;

    private final HotelDao hotelDao;
    private final PensionDao pensionDao;
    private final SeasonDao seasonDao;

    public RoomDao(){
        this.con = Db.getInstance();
        this.hotelDao = new HotelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
    }

    public ArrayList<Room> findAll(){
        return this.selectByQuery("SELECT * FROM public.room ORDER BY id");
    }

    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try{
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while(rs.next()){
                roomList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return roomList;
    }

    public Room getById(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setHotel(this.hotelDao.getById(obj.getHotelId()));
        obj.setPensionId(rs.getInt("pension_id"));
        obj.setPension(this.pensionDao.getById(obj.getPensionId()));
        obj.setSeasonId(rs.getInt("season_id"));
        obj.setSeason(this.seasonDao.getById(obj.getSeasonId()));
        obj.setType(rs.getString("type"));
        obj.setStock(rs.getInt("stock"));
        obj.setAdultPrice(rs.getString("adult_price"));
        obj.setChildPrice(rs.getString("child_price"));
        obj.setBedCapacity(rs.getString("bed_capacity"));
        obj.setSquare(rs.getString("square"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGameConsole(rs.getBoolean("game_console"));
        obj.setCashBox(rs.getBoolean("cash_box"));
        obj.setProjection(rs.getBoolean("projection"));

        return obj;
    }

    public boolean save(Room room) {

        String query = "INSERT INTO public.room " +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_box," +
                "projection"+
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3,room.getSeasonId());
            pr.setString(4,room.getType());
            pr.setInt(5,room.getStock());
            pr.setString(6,room.getAdultPrice());
            pr.setString(7,room.getChildPrice());
            pr.setString(8,room.getBedCapacity());
            pr.setString(9,room.getSquare());
            pr.setBoolean(10,room.isTelevision());
            pr.setBoolean(11,room.isMinibar());
            pr.setBoolean(12,room.isGameConsole());
            pr.setBoolean(13,room.isCashBox());
            pr.setBoolean(14,room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(Room room) {
        String query = "UPDATE public.room SET " +
                "hotel_id = ? , " +
                "pension_id = ? , " +
                "season_id = ? , " +
                "type = ? , " +
                "stock = ? , " +
                "adult_price = ? , " +
                "child_price = ? , " +
                "bed_capacity = ? , " +
                "square = ? , " +
                "television = ? , " +
                "minibar = ? , " +
                "game_console = ? , " +
                "cash_box = ? , " +
                "projection = ?  ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getHotelId());
            pr.setInt(2, room.getPensionId());
            pr.setInt(3,room.getSeasonId());
            pr.setString(4,room.getType());
            pr.setInt(5,room.getStock());
            pr.setString(6,room.getAdultPrice());
            pr.setString(7,room.getChildPrice());
            pr.setString(8,room.getBedCapacity());
            pr.setString(9,room.getSquare());
            pr.setBoolean(10,room.isTelevision());
            pr.setBoolean(11,room.isMinibar());
            pr.setBoolean(12,room.isGameConsole());
            pr.setBoolean(13,room.isCashBox());
            pr.setBoolean(14,room.isProjection());
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    public boolean decreaseStock(int id) {
        int stock = getById(id).getStock();
        int newStock = stock - 1;
        if(stock == 0 ){
            return false;
        }
        String query = "UPDATE public.room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, newStock);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean increaseStock(int id) {
        int stock = getById(id).getStock();
        int newStock = stock + 1;
        String query = "UPDATE public.room SET stock = ? WHERE id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, newStock);
            pr.setInt(2, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
