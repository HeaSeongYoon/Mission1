<%@ page import="org.example.service.BookmarkGroupService" %>
<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 추가</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        .container {
            text-align: left;
            max-width: 100%;
            margin: 0 auto;
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

        .button-container {
            display: flex;
            justify-content: center;
            margin-top: 1em;
        }

        .button-container input[type="submit"] {
            margin: 0 0.5em;
        }
    </style>
</head>
<body>
    <h1>북마크 그룹 추가</h1>
    <div class="navigation">
        <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
        <a href="bookmark_list.jsp">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
    </div>
    <form action="addBookmarkGroup.jsp" method="post">
        <table>
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" name="group_name" required></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="number" name="order" required></td>
            </tr>
        </table>
        <div class="button-container">
            <input type="submit" value="추가">
        </div>
    </form>

    <%
    if (request.getMethod().equalsIgnoreCase("post")) {
        BookmarkGroupService service = new BookmarkGroupService();
        BookmarkGroup newGroup = new BookmarkGroup();
        newGroup.setName(request.getParameter("group_name"));
        newGroup.setOrder(Integer.parseInt(request.getParameter("order")));

        boolean success = service.createGroup(newGroup);

        if (success) {
    %>
            <script>
                alert("북마크 그룹이 추가되었습니다!");
                location.href = "bookmark_groups.jsp";
            </script>
    <%   } else { %>
            <script>
                alert("북마크 그룹 추가에 실패했습니다.");
            </script>
    <%   }
    }
    %>
</body>
</html>
