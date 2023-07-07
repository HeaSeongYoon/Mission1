<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 추가</title>
    <style>

        .input-table th {
            background-color: #00ad6f;
            color: white;
        }

        .input-table {
            width: 50%;
        }
    </style>
</head>
<body>

<h1>북마크 그룹 추가</h1>

<div class="nav-links">
    <a href="index.jsp">홈</a> |
    <a href="history.jsp">위치 히스토리 목록</a> |
    <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a> |
    <a href="bookmark_view.jsp">북마크 보기</a> |
    <a href="bookmark_groups.jsp">북마크 그룹 관리</a>
</div>

<form action="bookmark_group_add_process.jsp" method="post">
    <table class="input-table">
        <tr>
            <th>북마크 이름</th>
            <td><input type="text" name="groupName" required></td>
        </tr>
        <tr>
            <th>순서</th>
            <td><input type="number" name="groupOrder" required></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: right;"><input type="submit" value="추가"></td>
        </tr>
    </table>
</form>

</body>
</html>
