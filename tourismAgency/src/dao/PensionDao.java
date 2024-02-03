package dao;

import core.Db;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.sql.*;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;
    private final HotelDao hotelDao = new HotelDao();
    public PensionDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<Pension> findAll() {
        return this.selectByQuery("SELECT * FROM public.hotel_pension ORDER BY id");
    }

    public boolean save(Pension pension) {

        String query = "INSERT INTO public.hotel_pension " +
                "(" +
                "hotel_id," +
                "pension_type" +
                ")" +

                "VALUES (?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getHotelId());
            pr.setString(2, pension.getType());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public ArrayList<Pension> selectByQuery(String query) {

        ArrayList<Pension> pensionList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                pensionList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return pensionList;
    }

    public Pension getById(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.hotel_pension WHERE id = ?";
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

    public Pension match(ResultSet rs) throws SQLException {
        Pension obj = new Pension();
        obj.setId(rs.getInt("id"));
        obj.setHotelId(rs.getInt("hotel_id"));
        obj.setType(rs.getString("pension_type"));
        return obj;
    }

}
