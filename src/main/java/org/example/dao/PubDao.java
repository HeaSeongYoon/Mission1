package org.example.dao;

import org.example.common.Db;
import org.example.domain.PubWifi;
import org.example.domain.Bookmark;



import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PubDao {

    public void insert(PubWifi pubWifi) {

        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;

        try {

            conn = DriverManager.getConnection(Db.URL);

            String sql = "INSERT INTO PUB_WIFI  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            stat = conn.prepareStatement(sql);

            stat.setString(1, pubWifi.getMgrNo());
            stat.setString(2, pubWifi.getRegion());
            stat.setString(3, pubWifi.getMainNm());
            stat.setString(4, pubWifi.getAddress());
            stat.setString(5, pubWifi.getAddressDetail());
            stat.setString(6, pubWifi.getInstallFloor());
            stat.setString(7, pubWifi.getInstallTy());
            stat.setString(8, pubWifi.getInstallMby());
            stat.setString(9, pubWifi.getServiceSe());
            stat.setString(10, pubWifi.getNetworkTy());
            stat.setString(11, pubWifi.getInstallYear());
            stat.setString(12, pubWifi.getIsOutdoor());
            stat.setString(13, pubWifi.getConnectEnv());
            stat.setString(14, pubWifi.getLongitude());
            stat.setString(15, pubWifi.getLatitude());
            stat.setString(16, pubWifi.getWorkDate());

            int affected = stat.executeUpdate();

            if (affected < 0) {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }


    public List<PubWifi> selectList(String lnt, String lat) {
        List<PubWifi> wifiList = new ArrayList<>();

        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(Db.URL);

            String sql = "SELECT *, " +
                    "round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))), 6) " +
                    "AS DISTANCE " +
                    "FROM PUB_WIFI " +
                    "ORDER BY DISTANCE " +
                    "LIMIT 20;"; //지구 반지름 6371

            stat = conn.prepareStatement(sql);
            stat.setString(1, String.valueOf(lnt));
            stat.setString(2, String.valueOf(lat));
            stat.setString(3, String.valueOf(lnt));

            rs = stat.executeQuery();

            while (rs.next()) {

                String dist = rs.getString("DISTANCE");
                String mrgNo = rs.getString("MGR_NO");
                String region = rs.getString("WRDOFC");
                String mainNm = rs.getString("MAIN_NM");
                String address = rs.getString("ADRES1");
                String addressDetail = rs.getString("ADRES2");
                String installFloor = rs.getString("INSTL_FLOOR");
                String installTy = rs.getString("INSTL_TY");
                String installMby = rs.getString("INSTL_MBY");
                String serviceSe = rs.getString("SVC_SE");
                String networkTy = rs.getString("CMCWR");
                String installYear = rs.getString("CNSTC_YEAR");
                String isOutdoor = rs.getString("INOUT_DOOR");
                String connectEnv = rs.getString("REMARS3");
                String longitude = rs.getString("LNT");
                String latitude = rs.getString("LAT");
                String workDate = rs.getString("WORK_DTTM");

                PubWifi pubWifi = new PubWifi();

                pubWifi.setDist(dist);
                pubWifi.setMgrNo(mrgNo);
                pubWifi.setRegion(region);
                pubWifi.setMainNm(mainNm);
                pubWifi.setAddress(address);
                pubWifi.setAddressDetail(addressDetail);
                pubWifi.setInstallFloor(installFloor);
                pubWifi.setInstallTy(installTy);
                pubWifi.setInstallMby(installMby);
                pubWifi.setServiceSe(serviceSe);
                pubWifi.setNetworkTy(networkTy);
                pubWifi.setInstallYear(installYear);
                pubWifi.setIsOutdoor(isOutdoor);
                pubWifi.setConnectEnv(connectEnv);
                pubWifi.setLongitude(longitude);
                pubWifi.setLatitude(latitude);
                pubWifi.setWorkDate(workDate);


                wifiList.add(pubWifi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return wifiList;

    }

    public void deleteAll() {
        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;

        String sql = "DELETE FROM PUB_WIFI";

        try {

            conn = DriverManager.getConnection(Db.URL);
            stat = conn.prepareStatement(sql);

            int affected = stat.executeUpdate();

            if (affected < 0) {
                System.out.println("리셋 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            try {
                if (stat != null && stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                 conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }





    public void update(PubWifi pubWifi) {
        String sql = "UPDATE pub_wifi SET name=?, location=?, latitude=?, longitude=?, install_date=? WHERE id=?";

        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pubWifi.getName());
            pstmt.setString(2, pubWifi.getAddress());
            pstmt.setDouble(3, pubWifi.getLatitudeAsDouble());
            pstmt.setDouble(4, pubWifi.getLongitudeAsDouble());
            pstmt.setDate(5, Date.valueOf(pubWifi.getWorkDate()));
            pstmt.setInt(6, pubWifi.getId());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM pub_wifi WHERE id=?";

        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public PubWifi findById(int id) {
        String sql = "SELECT * FROM HIST WHERE HIST_NO = ?";
        PubWifi pubWifi = null;

        try (Connection conn = Db.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    pubWifi = new PubWifi();
                    pubWifi.setId(rs.getInt("HIST_NO"));
                    pubWifi.setName(rs.getString("name"));
                    pubWifi.setAddress(rs.getString("address"));
                    pubWifi.setLatitudeFromDouble(rs.getDouble("latitude"));
                    pubWifi.setLongitudeFromDouble(rs.getDouble("longitude"));
                    pubWifi.setWorkDate(rs.getDate("work_date").toString());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pubWifi;
    }

    public List<PubWifi> selectBookmarkByGroupId(int groupId) {
        if (groupId < 1) {
            return Collections.emptyList();
        }

        List<PubWifi> bookmarkList = new ArrayList<>();

        try {
            Class.forName(Db.CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;
        PreparedStatement stat = null;
        ResultSet rs = null;

        try {
            conn = DriverManager.getConnection(Db.URL);

            String sql = "SELECT * FROM PUB_WIFI WHERE EXISTS (SELECT * FROM bookmarks WHERE group_id = ? AND MGR_NO = PUB_WIFI.MGR_NO)";


            stat = conn.prepareStatement(sql);
            stat.setInt(1, groupId);

            rs = stat.executeQuery();

            while (rs.next()) {
                PubWifi pubWifi = new PubWifi();
                pubWifi.setDist(rs.getString("DISTANCE"));
                pubWifi.setMgrNo(rs.getString("MGR_NO"));
                pubWifi.setRegion(rs.getString("WRDOFC"));
                pubWifi.setMainNm(rs.getString("MAIN_NM"));
                pubWifi.setAddress(rs.getString("ADRES1"));
                pubWifi.setAddressDetail(rs.getString("ADRES2"));
                pubWifi.setInstallFloor(rs.getString("INSTL_FLOOR"));
                pubWifi.setInstallTy(rs.getString("INSTL_TY"));
                pubWifi.setInstallMby(rs.getString("INSTL_MBY"));
                pubWifi.setServiceSe(rs.getString("SVC_SE"));
                pubWifi.setNetworkTy(rs.getString("CMCWR"));
                pubWifi.setInstallYear(rs.getString("CNSTC_YEAR"));
                pubWifi.setIsOutdoor(rs.getString("INOUT_DOOR"));
                pubWifi.setConnectEnv(rs.getString("REMARS3"));
                pubWifi.setLongitude(rs.getString("LNT"));
                pubWifi.setLatitude(rs.getString("LAT"));
                pubWifi.setWorkDate(rs.getString("WORK_DTTM"));

                bookmarkList.add(pubWifi);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (stat != null && !stat.isClosed()) {
                    stat.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookmarkList;
    }



    public List<Bookmark> findAllBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection("jdbc:sqlite:C:/Users/STEALTH/DataGripProjects/M1/identifier.sqlite", "username", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM bookmark");

            while (resultSet.next()) {
                Bookmark bookmark = new Bookmark(
                        resultSet.getInt("HIST_NO"),
                        resultSet.getString("MAIN_NM"),
                        resultSet.getString("name"),
                        resultSet.getTimestamp("create_date")
                );
                bookmarks.add(bookmark);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bookmarks;
    }
}


