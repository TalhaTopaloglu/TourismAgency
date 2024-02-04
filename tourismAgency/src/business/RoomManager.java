package business;

import core.Helper;
import dao.HotelDao;
import dao.RoomDao;
import entity.Room;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {  // Değerlendirme Formu 13
    private final RoomDao roomDao;
    private final HotelDao hotelDao;

    public RoomManager(){
        this.roomDao = new RoomDao();
        this.hotelDao = new HotelDao();
    }

    public Room getById(int id) {
        return this.roomDao.getById(id);
    }

    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }

    public boolean save (Room room) {
        if(this.getById(room.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.roomDao.save(room);
    }

    public boolean update(Room room) {
        if(this.getById(room.getId()) == null) {
            Helper.showMsg(room.getId() + " ID kayıtlı oda bulunamadı !");
            return false;
        }
        return this.roomDao.update(room);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> roomList){

        ArrayList<Object[]> roomObject = new ArrayList<>();
        for(Room obj : roomList){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel().getName();
            rowObject[i++] = obj.getPension().getType();
            rowObject[i++] = obj.getType();
            rowObject[i++] = obj.getStock();
            rowObject[i++] = obj.getAdultPrice();
            rowObject[i++] = obj.getChildPrice();
            rowObject[i++] = obj.getBedCapacity();
            rowObject[i++] = obj.getSquare();
            rowObject[i++] = obj.isTelevision();
            rowObject[i++] = obj.isMinibar();
            rowObject[i++] = obj.isGameConsole();
            rowObject[i++] = obj.isCashBox();
            rowObject[i++] = obj.isProjection();

            roomObject.add(rowObject);
        }
        return roomObject;
    }

    public ArrayList<Room> filterRoom(String start_date, String finish_date,String hotel_name, String address, int adult_number, int child_number){ // Değerlendirme Formu 15
        // roomlar season ile değişiyor
        String startQuery = "SELECT * FROM public.room as r INNER JOIN public.hotel as h ON h.id = r.hotel_id";
        String query = startQuery + " INNER JOIN public.hotel_season as s";

        ArrayList<String> where = new ArrayList<>();
        ArrayList<String> joinWhere = new ArrayList<>();
        ArrayList<String> roomOrWhere = new ArrayList<>();

        joinWhere.add(" r.hotel_id = s.hotel_id");

        start_date = LocalDate.parse(start_date, DateTimeFormatter.ofPattern("y-M-d")).toString();
        finish_date = LocalDate.parse(finish_date, DateTimeFormatter.ofPattern("y-M-d")).toString();
        int sumHostNumber = (adult_number + child_number);


        if(hotel_name.length() > 0)where.add("h.hotel_name = '" + hotel_name + "'");
        if(address.length() > 0)where.add("h.hotel_address = '" + address + "'");
        if(sumHostNumber > 0)where.add("r.bed_capacity >= '" + sumHostNumber + "'");

        String whereStr = String.join(" AND ",where);
        String joinStr = String.join(" AND ",joinWhere);

        if(joinStr.length() > 0) {
            query += " ON " + joinStr;
        }

        if(whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        System.out.println(query);

        ArrayList<Room> searchedRoomList = this.roomDao.selectByQuery(query);

        roomOrWhere.add("('" + start_date +"' BETWEEN start_date AND finish_date)");
        roomOrWhere.add("('" + finish_date +"' BETWEEN start_date AND finish_date)");


        String roomOrWhereStr = String.join(" AND ",roomOrWhere);
        String roomQuery = query + " AND " + roomOrWhereStr;

        System.out.println(roomQuery);

        ArrayList<Room> roomList = this.roomDao.selectByQuery(roomQuery);
        System.out.println(roomList);
        ArrayList<Integer> busyRoomId = new ArrayList<>();
        for(Room room : roomList){
            busyRoomId.add(room.getId());
        }

        searchedRoomList.removeIf(room -> busyRoomId.contains(room.getId()));

        System.out.println("search --> " + searchedRoomList);
        System.out.println("room --> " + roomList);
        return roomList;
    }

    public boolean decreaseStock(int id){ // Değerlendirme Formu 19
        if(this.getById(id) == null){
            Helper.showMsg(id + "Id kayıtlı oda bulunamadı !");
            return false;
        }
        return this.roomDao.decreaseStock(id);
    }

    public boolean increaseStock(int id){ // Değerlendirme Formu 23
        if(this.getById(id) == null){
            Helper.showMsg(id + "Id kayıtlı oda bulunamadı !");
            return false;
        }
        return this.roomDao.increaseStock(id);
    }
}
