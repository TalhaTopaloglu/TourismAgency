package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;

public class HotelManager {
    private final HotelDao hotelDao;

    public HotelManager(){
        this.hotelDao = new HotelDao();
    }

    public Hotel getById(int id) {
        return this.hotelDao.getById(id);
    }

    public ArrayList<Hotel> findAll(){
        return this.hotelDao.findAll();
    }


    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel> hotelList){

        ArrayList<Object[]> hotelObject = new ArrayList<>();
        for(Hotel obj : hotelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhoneNumber();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCarPark();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoomService();

            hotelObject.add(rowObject);
        }
        return hotelObject;
    }

    public boolean save (Hotel hotel) {
        if(this.getById(hotel.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.hotelDao.save(hotel);
    }

    public boolean update(Hotel hotel) {
        if(this.getById(hotel.getId()) == null) {
            Helper.showMsg(hotel.getId() + " ID kayıtlı otel bulunamadı !");
            return false;
        }
        return this.hotelDao.update(hotel);
    }

}
