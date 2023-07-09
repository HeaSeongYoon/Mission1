package org.example;

import org.example.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookmarkController extends HttpServlet {
    private BookmarkService service = new BookmarkService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addBookmark".equals(action)) {
            String wifiId = req.getParameter("wifiId");
            String bookmarkGroupId = req.getParameter("bookmarkGroupId");

            boolean result = service.addBookmark(wifiId, bookmarkGroupId);
            if (result) {
                resp.getWriter().write("success");
            } else {
                resp.getWriter().write("failed");
            }
        }
    }
}
