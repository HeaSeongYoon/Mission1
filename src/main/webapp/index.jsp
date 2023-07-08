<%@ page import="org.example.service.PubService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.domain.PubWifi" %>
<%@ page import="org.example.domain.HistWifi" %>
<%@ page import="org.example.service.HistService" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
          font-family: Arial, sans-serif;
          display: flex;
          align-items: flex-start;
          justify-content: flex-start;
          height: 100vh;
          margin: 0;
          padding: 2em;
        }

        .container {
          text-align: left;
          max-width: 100%;
          margin: 0 auto;
        }

        .input-group {
          display: inline-block;
        }

        table {
          width: 100%;
          border-collapse: collapse;
        }

        th,
        td {
          border: 1px solid #ddd;
          padding: 8px;
          text-align: left;
        }

        th {
          background-color: #00ad6f;
          color: #ffffff;
          border: 1px solid #ccc;
        }

        a {
          text-decoration: none;
          color: #1a0dab;
        }

        a:hover {
          text-decoration: underline;
        }

        .instruction {
          margin-top: 0.5em;
          font-size: 14px;
          text-align: center;
          padding: 10px;
          display: table;
          margin: 0 auto;
          margin-bottom: 20px;
        }

        .instruction-container {
          border: 1px solid #ccc;
          padding: 0.5em;
          width: 100%;
        }

        .form-container {
                margin-top: 1.5em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>와이파이 정보 구하기</h1>
        <div class="navigation">
            <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
            <a href="bookmark_list.jsp">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
        </div>
        <div class="form-container">
            <form method="get" action="index.jsp">
                <label> LNT :
                    <input type="text" placeholder="0.0" name="lnt" id="lnt">
                </label>
                <label> LAT :
                    <input type="text" placeholder="0.0" name="lat" id="lat">
                </label>
                <button type="button" onclick="getLocation()">내 위치 가져오기</button>
                <button type="submit">
                    근처 WIFI 정보 보기
                </button>
            </form>
        <%
            String lnt = request.getParameter("lnt");
            String lat = request.getParameter("lat");
        %>
        <table>
            <thead>
                <tr>
                    <th>거리(km)</th>
                    <th>관리번호</th>
                    <th>자치구</th>
                    <th>와이파이명</th>
                    <th>도로명주소</th>
                    <th>상세주소</th>
                    <th>설치위치(층)</th>
                    <th>설치유형</th>
                    <th>설치기관</th>
                    <th>서비스구분</th>
                    <th>망종류</th>
                    <th>설치년도</th>
                    <th>실내외구분</th>
                    <th>WIFI접속환경</th>
                    <th>X좌표</th>
                    <th>Y좌표</th>
                    <th>작업일자</th>
                </tr>
            </thead>

            <tbody>
                <%
                    if (lnt == null || lat ==null || lnt.trim().equals("") || lat.trim().equals("")) {
                %>
                    <tr>
                        <td colspan="17" style="text-align : center; padding: 10px 0,">
                            위치 정보를 입력한 후에 조회해 주세요.
                        </td>
                    </tr>
                <%
                                    } else {
                                        HistWifi histWifi = new HistWifi();
                                        histWifi.setLongitude(lnt);
                                        histWifi.setLatitude(lat);

                                        HistService histService = new HistService();
                                        histService.save(histWifi);

                                        PubService pubService = new PubService();
                                        List<PubWifi> wifiList = pubService.list(lnt,lat);

                                        for (PubWifi pubWifi : wifiList) {
                %>
                <tr>
                    <td> <%= pubWifi.getDist() %> </td>
                    <td> <%= pubWifi.getMgrNo() %> </td>
                    <td> <%= pubWifi.getRegion() %> </td>
                    <td><a href="wifidetails.jsp?id=<%= pubWifi.getMgrNo() %>&lat=<%= lat %>&lon=<%= lnt %>"><%= pubWifi.getMainNm() %></a></td>
                    <td> <%= pubWifi.getAddress() %> </td>
                    <td> <%= pubWifi.getAddressDetail() %> </td>
                    <td> <%= pubWifi.getInstallFloor() %> </td>
                    <td> <%= pubWifi.getInstallTy() %> </td>
                    <td> <%= pubWifi.getInstallMby() %> </td>
                    <td> <%= pubWifi.getServiceSe() %> </td>
                    <td> <%= pubWifi.getNetworkTy() %> </td>
                    <td> <%= pubWifi.getInstallYear() %> </td>
                    <td> <%= pubWifi.getIsOutdoor() %> </td>
                    <td> <%= pubWifi.getConnectEnv() %> </td>
                    <td> <%= pubWifi.getLongitude() %> </td>
                    <td> <%= pubWifi.getLatitude() %> </td>
                    <td> <%= pubWifi.getWorkDate() %> </td>
                </tr>
                <%
                        }
                     }
                %>
            </tbody>
        </table>
        <script>

            function getLocation() {
                                        if (navigator.geolocation) {
                                            navigator.geolocation.getCurrentPosition(showPosition, showError);
                                        } else {
                                            alert("이 브라우저에서는 지리 위치 지원이 불가능합니다.");
                                        }
                                    }

                                    function showPosition(position) {
                                        const lat = position.coords.latitude;
                                        const lng = position.coords.longitude;
                                        document.getElementById("lnt").value = lng;
                                        document.getElementById("lat").value = lat;
                                    }

                                    function showError(error) {
                                      switch (error.code) {
                                        case error.PERMISSION_DENIED:
                                            alert("사용자가 위치 정보의 사용을 거부했습니다.");
                                            break;
                                        case error.POSITION_UNAVAILABLE:
                                            alert("사용자 위치 정보를 사용할 수 없습니다.");
                                            break;
                                        case error.TIMEOUT:
                                            alert("위치 정보를 가져오는 데 시간이 초과되었습니다.");
                                            break;
                                        default:
                                            alert("알 수 없는 오류 발생");
                                      }
                                    }

        </script>
    </body>
</html>
