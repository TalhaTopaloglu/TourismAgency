package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;

public class ReservationManager {

    private final ReservationDao reservationDao;
    private final RoomManager roomManager;

    public ReservationManager(){
        this.reservationDao = new ReservationDao();
        this.roomManager = new RoomManager();
    }

    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }

    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Reservation> reservationList){

        ArrayList<Object[]> ReservationObject = new ArrayList<>();
        for(Reservation obj : reservationList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoomId();
            rowObject[i++] = obj.getCheckInDate();
            rowObject[i++] = obj.getCheckOutDate();
            rowObject[i++] = obj.getTotalPrice();
            rowObject[i++] = obj.getGuestCount();
            rowObject[i++] = obj.getGuestName();
            rowObject[i++] = obj.getGuestIdNo();
            rowObject[i++] = obj.getGuestMail();
            rowObject[i++] = obj.getGuestPhone();

            ReservationObject.add(rowObject);
        }
        return ReservationObject;
    }

    public boolean save (Reservation reservation) {
        if(this.getById(reservation.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }

    public boolean update(Reservation reservation) {
        if(this.getById(reservation.getId()) == null) {
            Helper.showMsg(reservation.getId() + " ID kayıtlı rezervasyon bulunamadı !");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

    public boolean delete (int id) {
        if(this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı rezervasyom bulunamadı");
            return false;
        }
        return this.reservationDao.delete(id);
    }



}
