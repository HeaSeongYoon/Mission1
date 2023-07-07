<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>

<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 관리</title>

    <style>

        .nav-links {
            margin-bottom: 30px;
        }

        form {
            margin-bottom: 20px;
        }

        table {
            border-collapse: collapse;
            width: 100%;
        }

        th {
            background-color: #00ad6f;
            color: white;

            padding: 10px;
            text-align: left;
        }

        td {

            padding: 10px;
            text-align: left;
        }

        table, th, td {
            border: 1px solid gray;
        }
    </style>

</head>
<body>
    <h1>북마크 그룹</h1>
    <div class="navigation">
        <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="bookmarks.jsp">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
    </div>

    <div>
        <button onclick="location.href='bookmark_group_add.jsp'">북마크 그룹 이름 추가</button>
    </div>

    <table border="1" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>북마크 이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </tr>
        </thead>
        <tbody>
            <%

            // 예시
            List<Map<String, Object>> bookmarkGroupList = new ArrayList<>();
            Map<String, Object> exampleGroup = new HashMap<>();
            exampleGroup.put("id", "123");
            exampleGroup.put("name", "Study Group");
            exampleGroup.put("order", 1);
            exampleGroup.put("createdDate", new Date());
            exampleGroup.put("modifiedDate", new Date());
            bookmarkGroupList.add(exampleGroup);


            for (Map<String, Object> group : bookmarkGroupList) {
            %>
                <tr>
                    <td><%= group.get("id") %></td>
                    <td><%= group.get("name") %></td>
                    <td><%= group.get("order") %></td>
                    <td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(group.get("createdDate")) %></td>
                    <td><%= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(group.get("modifiedDate")) %></td>
                    <td>
                        <a href="bookmark_group_edit.jsp?id=<%= group.get("id") %>">수정</a>
                        <a href="bookmark_group_delete.jsp?id=<%= group.get("id") %>">삭제</a>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
