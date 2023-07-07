package org.example.dao;

import org.example.common.Db;
import org.example.domain.PubWifi;



import java.sql.*;
import java.util.ArrayList;
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
}


