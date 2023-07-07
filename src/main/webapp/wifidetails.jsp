<%@ page import="java.util.List" %>
<%@ page import="org.example.service.PubService" %>
<%@ page import="org.example.domain.PubWifi" %>
<%@ page import="org.example.service.BookmarkGroupService" %><!-- BookmarkGroupService 클래스 추가 -->
<%@ page import="org.example.domain.BookmarkGroup" %><!-- BookmarkGroup 클래스 추가 -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Wi-Fi Details</title>
    <style>
        body {
          font-family: Arial, sans-serif;
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

        .navigation {
            margin-bottom: 20px;
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

            .bookmark-group {
              margin: 10px 0;
            }

    </style>
    <script>
        function getLocation() {
          if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(function(position) {
              var lat = position.coords.latitude;
              var lon = position.coords.longitude;
              var url = window.location.href.split('?')[0];
              window.location.href = url + "?lat=" + lat + "&lon=" + lon;
            }, function() {
                // 위치 권한 거부 또는 기타 오류 시 처리
            });
          } else {
            alert("이 브라우저는 위치 정보 지원하지 않습니다.");
          }
        }

        if (!window.location.href.includes("?")) {
          getLocation();
        }
    </script>
</head>
<body>
    <div class="container">
        <h1>와이파이 정보 구하기</h1>
        <div class="navigation">
            <a href="index.jsp">홈</a> |
            <a href="history.jsp">위치 히스토리 목록</a> |
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
            <a href="">북마크 보기</a> |
            <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
        </div>
        <%
                    // BookmarkGroupService 클래스를 인스턴스화 하여 그룹 이름을 가져옵니다.
                    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
                    List<BookmarkGroup> bookmarkGroups = bookmarkGroupService.listAllGroups();
        %>
        <div class="bookmark-group">
                    <select name="bookmark-group-selector" id="bookmark-group-selector">
                        <option value="" disabled selected>북마크 그룹 이름 선택하기</option>
                        <% for (BookmarkGroup group : bookmarkGroups) { %> <!-- 북마크 그룹 리스트 추가 -->
                            <option value="<%= group.getId() %>"><%= group.getName() %></option>
                        <% } %>
            </select>
            <button type="button">북마크 추가하기</button>
        </div>
    </div>
    <%
        String id = request.getParameter("id");
        PubService service = new PubService();
        String lat = request.getParameter("lat");
        String lon = request.getParameter("lon");
        if (lat == null || lon == null || lat.isEmpty() || lon.isEmpty()) {


        }
        List<PubWifi> wifiList = service.list(lon, lat);
        PubWifi wifi = null;

        for (PubWifi p : wifiList) {
            if (p.getMgrNo().equals(id)) {
                wifi = p;
                break;
            }
        }
    %>
    <% if(wifi != null) { %>
    <table border="1">
        <tr>
            <th>거리(km)</th>
            <td><%= wifi.getDist() %></td>
        </tr>
        <tr>
            <th>관리번호</th>
            <td><%= wifi.getMgrNo() %></td>
        </tr>
        <tr>
            <th>와이파이 명칭</th>
            <td><%= wifi.getMainNm() %></td>
        </tr>
        <tr>
            <th>도로명주소</th>
            <td><%= wifi.getAddress() %></td>
        </tr>
        <tr>
            <th>상세주소</th>
            <td><%= wifi.getAddressDetail() %></td>
        </tr>
        <tr>
            <th>설치위치(층)</th>
            <td><%= wifi.getInstallFloor() %></td>
        </tr>
        <tr>
            <th>설치유형</th>
            <td><%= wifi.getInstallTy() %></td>
        </tr>
        <tr>
            <th>설치기관</th>
            <td><%= wifi.getInstallMby() %></td>
        </tr>
        <tr>
            <th>서비스구분</th>
            <td><%= wifi.getServiceSe() %></td>
        </tr>
        <tr>
            <th>망종류</th>
            <td><%= wifi.getNetworkTy() %></td>
        </tr>
        <tr>
            <th>설치년도</th>
            <td><%= wifi.getInstallYear() %></td>
        </tr>
        <tr>
            <th>실내외구분</th>
            <td><%= wifi.getIsOutdoor() %></td>
        </tr>
        <tr>
            <th>X좌표</th>
            <td><%= wifi.getLongitude() %></td>
        </tr>
        <tr>
            <th>Y좌표</th>
            <td><%= wifi.getLatitude() %></td>
        </tr>
        <tr>
            <th>작업일자</th>
            <td><%= wifi.getWorkDate() %></td>
        </tr>
    </table>
    <% } else { %>
        <p>해당 ID의 Wi-Fi 정보를 찾지 못했습니다.</p>
    <% } %>
</body>
</html>
