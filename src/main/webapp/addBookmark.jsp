<%@ page import="java.net.URLEncoder" %>
<%@ page import="org.example.domain.PubWifi" %>
<%@ page import="org.example.service.PubService" %>
<%@ page import="org.example.service.BookmarkService" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String groupId = request.getParameter("groupId");
    String apiKey = request.getParameter("apiKey");
    String mgrNo = request.getParameter("mgrNo");
    String detailUrl = request.getParameter("detailUrl");

    PubWifi wifi = null;
    BookmarkService bookmarkService = new BookmarkService();

    if (groupId != null && apiKey != null && mgrNo != null && detailUrl != null) {
        PubService pubService = new PubService();
        wifi = pubService.getWifiDetail(apiKey, detailUrl);

        if (wifi != null) {
            boolean success = bookmarkService.addBookmark(groupId, wifi);
            if (!success) {
                response.setStatus(500);
                return;
            }
            response.setStatus(200);
            return;
        }
    }

    groupId = request.getParameter("groupId");
    int pubWifiId = Integer.parseInt(request.getParameter("pubWifiId"));

    BookmarkGroupService groupService = new BookmarkGroupService();
    PubService pubService = new PubService();

    boolean success = groupService.addBookmarkToGroup(groupId, pubWifiId);

    if(success) {
        response.sendRedirect("bookmark_list.jsp");
    } else {
        // Error handling
        out.print("<script>alert('북마크 추가에 실패하였습니다.'); window.history.back();</script>");
    }
%>
