<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            height: 100vh;
            margin: 0;
            padding: 0;
        }

        .container {
            text-align: center;
        }

        h1 {
            margin-bottom: 1em;
        }
    </style>
<title>와이파이 정보 가져오기</title>
</head>
<body>

<%

    String dbURL = "jdbc:sqlite:C:/Users/STEALTH/DataGripProjects/M1/identifier.sqlite";


    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;


    int wifiCount = 0;

    try {

        Class.forName("org.sqlite.JDBC");


        conn = DriverManager.getConnection(dbURL);


        String sql = "SELECT COUNT(*) FROM PUB_WIFI";


        pstmt = conn.prepareStatement(sql);

        // 쿼리 실행
        rs = pstmt.executeQuery();

        // 결과 데이터 처리
        if (rs.next()) {
            wifiCount = rs.getInt(1);
        }

    } catch (Exception e) {
        e.printStackTrace();

    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstmt != null) {
            try {
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
%>
<div class="container">
<h1><%= wifiCount %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
<a href="index.jsp">홈으로 돌아가기</a>
</div>
</body>
</html>
