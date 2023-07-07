<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page import="org.example.service.BookmarkGroupService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="kr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>북마크 그룹 삭제</title>
</head>
<body>
    <%
        int groupId = Integer.parseInt(request.getParameter("id"));
        BookmarkGroupService service = new BookmarkGroupService();
        BookmarkGroup group = new BookmarkGroup();
        group.setId(groupId);
        if (service.deleteGroup(group)) {
            response.sendRedirect("bookmark_groups.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    %>
</body>
</html>
