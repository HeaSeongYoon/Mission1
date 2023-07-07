<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page import="org.example.service.BookmarkGroupService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 수정</title>
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

        .button-container button {
            margin: 0 0.5em;
        }
    </style>
</head>
<body>
    <h1>북마크 그룹 수정</h1>
    <div class="navigation">
        <a href="index.jsp">홈</a> | <a href="history.jsp">위치 히스토리 목록</a> | <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> | <a href="">북마크 보기</a> | <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
    </div>
    <table>
        <%
            int groupId = Integer.parseInt(request.getParameter("id"));
            BookmarkGroupService service = new BookmarkGroupService();
            BookmarkGroup group = service.getGroupById(groupId);
        %>
        <tr>
            <th>북마크 이름</th>
            <td>
                <input type="text" id="name" value="<%= group.getName() %>">
            </td>
        </tr>
        <tr>
            <th>순서</th>
            <td>
                <input type="number" id="order" value="<%= group.getOrder() %>">
            </td>
        </tr>
    </table>
    <div class="button-container">
        <a href="bookmark_groups.jsp">돌아가기</a>
        <button id="submitBtn">수정</button>
    </div>
    <script>
        document.getElementById("submitBtn").onclick = function() {
            var formData = new FormData();
            formData.append("id", '<%= groupId %>');
            formData.append("name", document.getElementById("name").value);
            formData.append("order", document.getElementById("order").value);

            var request = new XMLHttpRequest();
            request.open("POST", "updateBookmarkGroup.jsp");
            request.onreadystatechange = function() {
                if (request.readyState === 4 && request.status === 200) {
                    window.location.href = "bookmark_groups.jsp";
                }
            };
            request.send(formData);
        }
    </script>
</body>
</html>

