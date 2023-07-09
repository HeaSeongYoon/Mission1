package org.example.service;

import org.example.common.Db;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;


public class BookmarkService {
    public boolean insert(String wifiId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = Db.getConnection();
            String sql = "INSERT INTO bookmark (HIST_NO, MAIN_NM, name, create_date) SELECT HIST.HIST_NO, PUB_WIFI.MAIN_NM, PUB_WIFI.MGR_NO, ? FROM HIST JOIN PUB_WIFI ON HIST.LNT = PUB_WIFI.LNT AND HIST.LAT = PUB_WIFI.LAT WHERE HIST.HIST_NO = ?;";

            statement = connection.prepareStatement(sql);
            statement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            statement.setString(2, wifiId);

            int result = statement.executeUpdate();

            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
        }
        return false;
    }

    public boolean addBookmark(String wifiId, String bookmarkGroupId) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Db.getConnection();
            String sql = "INSERT INTO bookmark (HIST_NO, MAIN_NM, name, create_date) SELECT HIST.HIST_NO, PUB_WIFI.MAIN_NM, PUB_WIFI.MGR_NO, ? FROM HIST JOIN PUB_WIFI ON HIST.LNT = PUB_WIFI.LNT AND HIST.LAT = PUB_WIFI.LAT WHERE HIST.HIST_NO = ?;";

            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            statement.setString(2, wifiId);

            int result = statement.executeUpdate();

            if (result > 0) {
                // 북마크의 생성된 ID 가져옴
                int bookmarkId = -1;
                resultSet = statement.getGeneratedKeys();
                if (resultSet.next()) {
                    bookmarkId = resultSet.getInt(1);
                }

                // 북마크 그룹과 연결하는 코드 추가
                if (bookmarkId != -1) {
                    sql = "INSERT INTO bookmark_groups (bookmark_id, name, sort_order, create_date, modified_date) VALUES (?, ?, ?, ?, ?)";

                    statement = connection.prepareStatement(sql);
                    statement.setInt(1, bookmarkId);
                    statement.setString(2, "zxc");
                    statement.setInt(3, 1); // 정렬 순서
                    statement.setString(4, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                    statement.setString(5, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

                    result = statement.executeUpdate();

                    return result > 0;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try { resultSet.close(); } catch (SQLException e) { }
            }
            if (statement != null) {
                try { statement.close(); } catch (SQLException e) { }
            }
            if (connection != null) {
                try { connection.close(); } catch (SQLException e) { }
            }
        }
        return false;
    }
}



