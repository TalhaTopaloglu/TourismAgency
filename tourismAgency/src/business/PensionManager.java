package business;

import core.Helper;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;

import java.util.ArrayList;

public class PensionManager {

    private final PensionDao pensionDao;

    public PensionManager(){
        this.pensionDao = new PensionDao();
    }

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }

    public boolean save (Pension pension) {
        if(this.getById(pension.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }
    public ArrayList<Object[]> getForTable(int size,ArrayList<Pension> pensionList){

        ArrayList<Object[]> pensionObject = new ArrayList<>();

        for(Pension obj : pensionList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getType();

            pensionObject.add(rowObject);
        }
        return pensionObject;
    }
}
