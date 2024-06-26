package dao;

import core.Db;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

    private final Connection con;

    public UserDao() {
        this.con = Db.getInstance();
    }

    public ArrayList<User> findAll() {
        return this.selectByQuery("SELECT * FROM public.user ORDER BY user_id");
    }

    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User findByLogin(String username, String password){ // Değerlendirme Formu 9
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_password = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,username);
            pr.setString(2,password);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = this.match(rs);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return obj;
    }

    public boolean delete(int id){
        String query = "DELETE FROM public.user WHERE user_id = ?";
        try{
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            return pr.executeUpdate() != -1;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    public boolean save(User user) {
        String query = "INSERT INTO public.user " +
                "(" +
                "user_name," +
                "user_password," +
                "user_role" +
                ")" +
                "VALUES (?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());

            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean update(User user) {
        String query = "UPDATE public.user SET " +
                "user_name= ? , " +
                "user_password = ? , " +
                "user_role = ? " +
                "WHERE user_id = ?";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole().toString());
            pr.setInt(4, user.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public User getById(int id){
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
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

    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_password"));
        obj.setRole(User.Role.valueOf(rs.getString("user_role")));
        return obj;
    }
}
