package business;
import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;
    /*
    Giriş ekranı için kullanılan
    kullanıcı adı ve şifre aldığımız
    kodlar.
     */
    public UserManager(){
        this.userDao = new UserDao();
    }

    public User getById(int id) {
        return this.userDao.getById(id);
    }

    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<User> userList){

        ArrayList<Object[]> userObjList = new ArrayList<>();
        for(User obj : userList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            userObjList.add(rowObject);
        }
        return userObjList;
    }

    public boolean save (User user) {

        if(this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }

        for(User users : findAll()){
            if(users.getUsername().equals(user.getUsername())){
                Helper.showMsg("Kullanıcı adı zaten mevcut");
                return false;
            }
        }
        return this.userDao.save(user);
    }

    public boolean update(User user) {
        if(this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + " ID kayıtlı user bulunamadı !");
            return false;
        }
        return this.userDao.update(user);
    }

    public boolean delete (int id) {
        if(this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }


    public User findByLogin(String username, String password){
        // farklı işlemler yapabiliriz.
        return this.userDao.findByLogin(username, password);
    }

    public ArrayList<User> searchForTable(User.Role role){
        String select = "SELECT * FROM public.user";
        ArrayList<String> whereList = new ArrayList<>();

        if(role != null){
            whereList.add("user_role = '" + role.toString() + "'");
        }

        String whereStr = String.join(" AND ", whereList);
        String query = select;
        if(whereStr.length()> 0){
            query += " WHERE " + whereStr;
        }
        return this.userDao.selectByQuery(query) ;
    }

}