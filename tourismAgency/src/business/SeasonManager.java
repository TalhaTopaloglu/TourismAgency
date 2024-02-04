package business;

import core.Helper;
import dao.SeasonDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {// Değerlendirme Formu 11

    private final SeasonDao seasonDao;

    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }


    public boolean save (Season season) {
        if(this.getById(season.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.seasonDao.save(season);
    }

    public ArrayList<Object[]> getForTable(int size,ArrayList<Season> seasonList){

        ArrayList<Object[]> seasonObject = new ArrayList<>();

        for(Season obj : seasonList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getStartDate().toString();
            rowObject[i++] = obj.getFinishDate().toString();

            seasonObject.add(rowObject);
        }
        return seasonObject;
    }
}
