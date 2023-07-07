<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.example.service.HistService" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.domain.HistWifi" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>위치 히스토리 목록</title>
    <style>
        table {
                    border-collapse: collapse;
                    width: 100%;
                }
                th, td {
                    border: 1px solid black;
                    padding: 8px;
                    text-align: left;
                    font-size: 16px;
                    text-align: center
                }
                th {
                    font-weight: bold;
                    background-color: #00ad6f;
                    color: #ffffff;
                    text-align : center;
                }
    </style>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <p><a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
                   <a href="">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
        </thead>
    <tbody>
      <%
        HistService histService = new HistService();
        List<HistWifi> list = histService.list();
        for (HistWifi hist : list) {
      %>
        <tr>
          <td><%= hist.getHistNo() %></td>
          <td><%= hist.getLongitude() %></td>
          <td><%= hist.getLatitude() %></td>
          <td>
            <% SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
               String formattedDate = format.format(hist.getSearchDate()); %>
            <%= formattedDate %>
          </td>
          <td>
            <form action="deletehist" method="POST">
              <input type="hidden" name="id" value="<%= hist.getHistNo() %>">
              <input type="submit" value="삭제">
            </form>
          </td>
        </tr>
      <% } %>
    </tbody>
  </table>
</body>
</html>
