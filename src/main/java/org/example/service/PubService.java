
package org.example.service;


import org.example.common.Api;
import org.example.dao.PubDao;
import org.example.domain.PubWifi;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PubService {

    public int getTotal() throws IOException {

        StringBuilder urlBuilder = new StringBuilder(Api.BASE_URL);
        urlBuilder.append("/" + URLEncoder.encode(Api.KEY, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Api.TYPE, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode(Api.SERVICE, "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();

        String total = sb.substring(40, 45);

        return Integer.parseInt(total);
    }

    public void insertData() {


        try {
            int intTotal = getTotal();

            BufferedReader rd;
            StringBuilder sb;
            String line;

            int idx = 1;

            for (int i = 0; i < intTotal / 1000 + 1; i++) {

                StringBuilder urlBuilder = new StringBuilder(Api.BASE_URL);
                urlBuilder.append("/" + URLEncoder.encode(Api.KEY, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(Api.TYPE, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(Api.SERVICE, "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(idx), "UTF-8"));
                urlBuilder.append("/" + URLEncoder.encode(String.valueOf(idx + 999), "UTF-8"));

                URL url = new URL(urlBuilder.toString());
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");


                if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                }

                sb = new StringBuilder();

                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }



                //JSONObject result = (JSONObject) new JSONObject().parse(sb.toString());
                JSONParser parser = new JSONParser();
                JSONObject result = (JSONObject) parser.parse(sb.toString());
                JSONObject data = (JSONObject) result.get("TbPubWifiInfo");
                JSONObject array = (JSONObject) data.get("row");


                JSONObject tmp;

                for (int j = 0; j < array.size(); j++) {

                    PubWifi pubWifi = new PubWifi();

                    tmp = (JSONObject) array.get(j);

                    pubWifi.setMgrNo(String.valueOf(tmp.get("X_SWIFI_MGR_NO")));
                    pubWifi.setRegion(String.valueOf(tmp.get("X_SWIFI_WRDOFC")));
                    pubWifi.setMainNm(String.valueOf(tmp.get("X_SWIFI_MAIN_NM")));
                    pubWifi.setAddress(String.valueOf(tmp.get("X_SWIFI_ADRES1")));
                    pubWifi.setAddressDetail(String.valueOf(tmp.get("X_SWIFI_ADRES2")));
                    pubWifi.setInstallFloor(String.valueOf(tmp.get("X_SWIFI_INSTL_FLOOR")));
                    pubWifi.setInstallTy(String.valueOf(tmp.get("X_SWIFI_INSTL_TY")));
                    pubWifi.setInstallMby(String.valueOf(tmp.get("X_SWIFI_INSTL_MBY")));
                    pubWifi.setServiceSe(String.valueOf(tmp.get("X_SWIFI_SVC_SE")));
                    pubWifi.setNetworkTy(String.valueOf(tmp.get("X_SWIFI_CMCWR")));
                    pubWifi.setInstallYear(String.valueOf(tmp.get("X_SWIFI_CNSTC_YEAR")));
                    pubWifi.setIsOutdoor(String.valueOf(tmp.get("X_SWIFI_INOUT_DOOR")));
                    pubWifi.setConnectEnv(String.valueOf(tmp.get("X_SWIFI_REMARS3")));
                    pubWifi.setLongitude(String.valueOf(tmp.get("LAT").toString()));
                    pubWifi.setLatitude(String.valueOf(tmp.get("LNT").toString()));
                    pubWifi.setWorkDate(String.valueOf(tmp.get("WORK_DTTM")));

                    PubDao pubDao = new PubDao();
                    pubDao.insert(pubWifi);
                }

                rd.close();
                conn.disconnect();

                idx += 1000;
            }

        } catch (Exception e) {
            System.out.println(e.getClass());
            System.out.println(e.getMessage());
        }
    }

    public List<PubWifi> list(String lnt, String lat) {
        PubDao pubDao = new PubDao();
        return pubDao.selectList(lnt, lat);
    }

    public void reset() {
        PubDao pubDao = new PubDao();
        pubDao.deleteAll();
    }

    public List<PubWifi> findBookmarkListByGroupId(int groupId) {
        PubDao pubDao = new PubDao();
        return pubDao.selectBookmarkByGroupId(groupId);
    }

    public PubWifi findById(int id) {
        PubDao pubDao = new PubDao();
        return pubDao.findById(id);
    }

    public static PubWifi getPubWifi(int id) {
        PubDao pubDao = new PubDao();
        return pubDao.findById(id);
    }


}



