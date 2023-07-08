<%@ page import="org.example.service.BookmarkGroupService" %>
<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    int id = Integer.parseInt(request.getParameter("id"));
    String name = request.getParameter("name");
    int order = Integer.parseInt(request.getParameter("order"));

    BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    BookmarkGroup group = new BookmarkGroup(id, name, order);
    boolean isUpdated = bookmarkGroupService.updateGroup(group);

    if (isUpdated) {
        response.sendRedirect("bookmark_groups.jsp");
    } else {
        response.sendRedirect("editBookmarkGroup.jsp?id=" + id + "&name=" + name + "&order=" + order);
    }
%>
