<%@ page import="org.example.domain.BookmarkGroup" %>
<%@ page import="org.example.service.BookmarkGroupService" %>
<%
int groupId = Integer.parseInt(request.getParameter("groupId"));
String groupName = request.getParameter("groupName");
if (groupName != null && !groupName.isEmpty()) {
    BookmarkGroup group = new BookmarkGroup();
    group.setId(groupId);
    group.setName(groupName);

    BookmarkGroupService service = new BookmarkGroupService();
    boolean updateResult = service.updateGroup(group);

    if (updateResult) {
        response.sendRedirect("bookmark_groups.jsp");
    } else {
        // 에러 처리
    }
}
%>
