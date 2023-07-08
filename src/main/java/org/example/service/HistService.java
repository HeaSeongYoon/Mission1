package org.example.service;

import org.example.dao.HistDao;
import org.example.domain.HistWifi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static org.example.common.Db.getConnection;

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
        try(Connection con = getConnection(); PreparedStatement pstmt = con.prepareStatement("DELETE FROM hist WHERE hist_no=?")) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


