<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.example.service.BookmarkGroupService" %>
<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 관리</title>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }
        th, td {
            border: 1px solid #ccc;
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

        .add-button-container {
                margin-top: 1.5em;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>북마크 그룹</h1>
        <div class="navigation">
            <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
            <a href="">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
        </div>
        <div class="add-button-container">
            <button onclick="location.href = 'addBookmarkGroup.jsp'">북마크 그룹 이름 추가</button>
        </div>

    <table>
        <tr>
            <th>ID</th>
            <th>북마크 이름</th>
            <th>순서</th>
            <th>등록일자</th>
            <th>수정일자</th>
            <th>비고</th>
        </tr>
        <%
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            BookmarkGroupService service = new BookmarkGroupService();
            List<BookmarkGroup> bookmarkGroups = service.listAllGroups();
            for (BookmarkGroup group : bookmarkGroups) {
        %>
        <tr>
                <td><%= group.getId() %></td>
                <td><%= group.getName() %></td>
                <td><%= group.getOrder() %></td>
                <td><%= dateFormatter.format(group.getCreateDate()) %></td>
                <td><%= dateFormatter.format(group.getModifiedDate()) %></td>
                <td>
                    <a href="editBookmarkGroup.jsp?id=<%= group.getId() %>">수정</a>
                    <a href="deleteBookmarkGroup.jsp?id=<%= group.getId() %>" onclick="return confirm('삭제하시겠습니까?');">삭제</a>
                </td>
            </tr>
            <% } %>
    </table>
</body>
</html>
