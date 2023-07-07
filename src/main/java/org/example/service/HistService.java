package org.example.service;

import org.example.dao.HistDao;
import org.example.domain.HistWifi;

import java.util.Collections;
import java.util.List;

public class HistService {

    public List<HistWifi> list() {
        HistDao histDao = new HistDao();
        List<HistWifi> result = histDao.selectAll();
        Collections.reverse(result);
        return result;
    }


    public void save(HistWifi histWifi) {
        HistDao histDao = new HistDao();
        histDao.insert(histWifi);
    }

    public void delete(int id) {
    }
}


