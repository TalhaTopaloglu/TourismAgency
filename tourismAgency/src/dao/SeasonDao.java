package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SeasonDao {

    private final Connection con;

    private  final HotelDao hotelDao = new HotelDao();

    public SeasonDao(){
        this.con = Db.getInstance();
    }

    public ArrayList<Season> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotel_season ORDER BY id");
    }

    public Season getById(int id){
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ?";
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

    public boolean save(Season season) {

        String query = "INSERT INTO public.hotel_season " +
                "(" +
                "hotel_id," +
                "start_date," +
                "finish_date" +
                ")" +

                "VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, season.getHotelId());
            pr.setDate(2, Date.valueOf(season.getStartDate()));
            pr.setDate(3, Date.valueOf(season.getFinishDate()));

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Season> selectByQuery(String query) {

        ArrayList<Season> seasonList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                seasonList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return seasonList;
    }
    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setHotel(this.hotelDao.getById(rs.getInt("id")));
        obj.setStartDate(LocalDate.parse(rs.getString("start_date")));
        obj.setFinishDate(LocalDate.parse(rs.getString("finish_date")));
        return obj;
    }



}
