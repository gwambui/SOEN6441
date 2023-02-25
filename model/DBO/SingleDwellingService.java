package model.DBO;

import model.SingleDwelling;

public class SingleDwellingService {
    private SingleDwellingDao singleDwellingDao;

    public SingleDwellingService(SingleDwellingDao dao){
        singleDwellingDao = dao;
    }
    public SingleDwelling findById (long id){
        return singleDwellingDao.findById(id);
    }
}
