<%@ page import="java.util.Date" %>
<%@ page import="org.example.service.BookmarkService" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>북마크 추가</title>
</head>
<body>
    <%
    String groupId = request.getParameter("groupId");
    String wifiId = request.getParameter("wifiId");

    if (groupId != null && wifiId != null && !groupId.isEmpty() && !wifiId.isEmpty()) {
        BookmarkService bookmarkService = new BookmarkService();
        if (bookmarkService.addBookmark(groupId, wifiId)) {
            response.sendRedirect("bookmark_list.jsp");
        } else {
            %>
            <p>북마크 추가에 실패했습니다.</p>
            <button onclick="window.history.back();">뒤로 가기</button>
            <%
        }
    } else {
        %>
        <p>잘못된 요청입니다.</p>
        <button onclick="window.history.back();">뒤로 가기</button>
        <%
    }
    %>
</body>
</html>
